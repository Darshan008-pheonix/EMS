package com.ems.amz.serviceimpln;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ems.amz.dto.LeaveDto;
import com.ems.amz.entity.EmpIdGenerator;
import com.ems.amz.entity.Employee;
import com.ems.amz.entity.LeaveApplication;
import com.ems.amz.exception.EmployeeNotFoundException;
import com.ems.amz.exception.InvalidLeaveApllicationExcpetion;
import com.ems.amz.repo.EmployeeIdGeneratorRepo;
import com.ems.amz.repo.EmployeeRepo;
import com.ems.amz.repo.LeaveRepo;
import com.ems.amz.service.IEmployeeService;
@Service
public class EmployeeServiceImplemention implements IEmployeeService{
	@Autowired
	EmployeeRepo repo;
	
	@Autowired
	EmployeeIdGeneratorRepo idRepo;
	
	@Autowired
	LeaveRepo leaveRepo;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Override
	public Employee addEmployee(Employee e) {
		EmpIdGenerator id=idRepo.save(new EmpIdGenerator());
		int n=id.getId();
		if(n<10) {
		e.setEid("amz00"+n);
		}
		else if(n<100) {
			e.setEid("amz0"+n);
		}
		else {
			e.setEid("amz"+n);
		}
		return repo.save(e);
	}
	
	public void sendLeaveNotificationEmail(String to,String from,String ename,String reason) {
		SimpleMailMessage mail=new SimpleMailMessage();
		
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject("Leave Apllication By "+ename);
		mail.setText("Please find Leave Apllication by "+ename +"for below reason\n"+reason+"\n please visit ems webisite to approve");
		javaMailSender.send(mail);
	}
	
	public void sendLeaveApprovalEmail(String to,String from,String ename,String status) {
		SimpleMailMessage mail=new SimpleMailMessage();
		
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject("Regarding Your Leave Application");
		mail.setText("Your application had been:"+status);
		javaMailSender.send(mail);
	}

	@Override
	public String applyLeave(LeaveDto l) {
		Employee e=repo.findById(l.getEid()).orElseThrow(()->new EmployeeNotFoundException());
	    int n=(int) ChronoUnit.DAYS.between(l.getFromdate(),l.getToDate());
	    if(n<e.getLeaves()) {
	    	LeaveApplication a1=new LeaveApplication();
	    	a1.setEid(l.getEid());
	    	a1.setFromdate(l.getFromdate());
	    	a1.setToDate(l.getToDate());
	    	a1.setReason(l.getReason());
	    	a1.setHid(e.getHid());
	    	a1.setMid(e.getMid());
	    	a1.setDays(n);
	    	leaveRepo.save(a1);
	    	Optional<Employee> m=repo.findById(e.getMid());
	    	Optional<Employee> h=repo.findById(e.getHid());
	    	sendLeaveNotificationEmail(m.get().getEmail(),"darshanclassic8@gmail.com",e.getEname(),a1.getReason());
	    	sendLeaveNotificationEmail(h.get().getEmail(),"darshanclassic8@gmail.com",e.getEname(),a1.getReason());
	    	return "Leave Applied";
	    	
	    }
	    else {
	    	throw new InvalidLeaveApllicationExcpetion();
	    }
	    
	}

	@Override
	public String approveLeave(String eid, String status) {
		LeaveApplication a1=leaveRepo.findByEid(eid);
		Employee e=repo.findById(eid).orElseThrow(()->new EmployeeNotFoundException());
		a1.setStatus(status);
		leaveRepo.save(a1);
		sendLeaveApprovalEmail(e.getEmail(),"darshanclassic8@gmail.com", e.getEname(), status);
		if(status.equals("Approved")) {
			e.setLeaves(e.getLeaves()-a1.getDays());
			repo.save(e);
			return "Leave Approved";
		}
		else {
			return "Leave Not Approved";
		}
	}

	

}
