package com.brillio.pocreactive.service.Impl;

import com.brillio.pocreactive.entitiy.employee.EmployeeEntity;
import com.brillio.pocreactive.exception.EmployeeAlreadyExistsException;
import com.brillio.pocreactive.exception.EmployeeNotFoundException;
import com.brillio.pocreactive.repository.EmployeeRepository;
import com.brillio.pocreactive.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Mono<EmployeeEntity> addEmployeeByEmail(EmployeeEntity employeeEntity) {
		return employeeRepository.save(employeeEntity);

	}

	@Override
	public Mono<EmployeeEntity> findEmployeeByEmail(String employeeEmail) {
		return employeeRepository.findByEmployeeEmail(employeeEmail);
	}

	@Override
	public Flux<EmployeeEntity> findAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Mono<EmployeeEntity> deleteEmployeeByEmail(String employeeEmail) {
		return employeeRepository.findByEmployeeEmail(employeeEmail).flatMap(employee->employeeRepository.delete(employee).then(Mono.just(employee)));
	}

	@Override
	public Mono<EmployeeEntity> updateEmployee(EmployeeEntity employeeEntity) {
		return employeeRepository.findByEmployeeEmail(employeeEntity.getEmployeeEmail())
				.flatMap(employeeEntity1 -> {
					employeeEntity1.setEmployeeAddress(employeeEntity.getEmployeeAddress());
					employeeEntity1.setEmployeeName(employeeEntity.getEmployeeName());
					employeeEntity1.setEmployeeDesignation(employeeEntity.getEmployeeDesignation());
					return employeeRepository.save(employeeEntity1);
				});
	}
}
