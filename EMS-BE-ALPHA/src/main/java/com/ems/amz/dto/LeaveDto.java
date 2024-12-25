package com.ems.amz.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class LeaveDto {

	private String eid;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromdate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;
	private String reason;
}
