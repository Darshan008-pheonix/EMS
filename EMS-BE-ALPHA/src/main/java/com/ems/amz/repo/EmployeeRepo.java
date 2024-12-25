package com.ems.amz.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.amz.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,String> {

}
