

package com.ems.amz.service;

import com.ems.amz.dto.LeaveDto;
import com.ems.amz.entity.Employee;

public interface IEmployeeService {

	Employee addEmployee(Employee e);
	
	String applyLeave(LeaveDto l);
	
	String approveLeave(String eid,String status);
}
