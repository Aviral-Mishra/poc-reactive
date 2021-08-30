package com.brillio.pocreactive.service;

import com.brillio.pocreactive.entitiy.employee.EmployeeEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

	Mono<EmployeeEntity> addEmployeeByEmail(EmployeeEntity employeeEntity);
	Mono<EmployeeEntity> findEmployeeByEmail(String employeeEmail);
	Flux<EmployeeEntity> findAllEmployee();
	Mono<EmployeeEntity> deleteEmployeeByEmail(String employeeEmail);
	Mono<EmployeeEntity> updateEmployee(EmployeeEntity employeeEntity);
}
