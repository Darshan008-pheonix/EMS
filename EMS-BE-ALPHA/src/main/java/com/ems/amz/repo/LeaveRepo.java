package com.ems.amz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.amz.entity.LeaveApplication;

public interface LeaveRepo extends JpaRepository<LeaveApplication,Integer>{
	LeaveApplication findByEid(String eid);
}
