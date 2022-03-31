package com.capg.repositroy;

import org.springframework.data.repository.CrudRepository;

import com.capg.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
