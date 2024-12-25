package com.ems.amz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.amz.entity.EmpIdGenerator;

public interface EmployeeIdGeneratorRepo  extends JpaRepository<EmpIdGenerator,Integer>{

}
