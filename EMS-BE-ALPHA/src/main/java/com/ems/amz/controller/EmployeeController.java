package com.ems.amz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.amz.dto.LeaveDto;
import com.ems.amz.entity.Employee;
import com.ems.amz.service.IEmployeeService;


@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;
	
	@PostMapping("addEmp")
	ResponseEntity<Employee> addEmp(@RequestBody Employee e){
		return new ResponseEntity<Employee>(employeeService.addEmployee(e),HttpStatus.CREATED);
	}
	
	@PostMapping("applyLeave")
	ResponseEntity applyLeave(@RequestBody LeaveDto l){
		return new ResponseEntity (employeeService.applyLeave(l),HttpStatus.CREATED);
	}
	
	@GetMapping("approveLeave")
	ResponseEntity approveLeave(@RequestHeader String eid,@RequestHeader String status) {
		return new ResponseEntity(employeeService.approveLeave(eid, status),HttpStatus.ACCEPTED);
	}
	
	
}
