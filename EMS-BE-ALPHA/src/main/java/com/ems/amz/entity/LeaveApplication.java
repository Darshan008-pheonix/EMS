package com.ems.amz.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor 
@AllArgsConstructor
public class LeaveApplication {

	@Id
	private int lid;
	private String eid;
	private String mid;
	private String hid;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromdate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;
	private int days;
	private String reason;
	private String status;
	
}
