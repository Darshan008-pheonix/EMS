package com.ems.amz.entity;


import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
	
	@Id
	private String eid;  //amz01,amz02
	private String mid;
	private String hid;
	private String ename;
	private String desgn;
	private double sal;
	private double leaves;
	private boolean ismgr;
	private boolean ishr;
	private String dp;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate doj;
	private boolean isactive;
	private String email;
	private String role;
	private String gender;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dor;
	private String adhNo;
	
	

}
