package com.brillio.pocreactive.controller;

import com.brillio.pocreactive.entitiy.employee.EmployeeEntity;
import com.brillio.pocreactive.exception.InsufficientDataException;
import com.brillio.pocreactive.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class Controller {

	@Autowired
	EmployeeService employeeService;

	@PostMapping
	Mono<EmployeeEntity> addNewEmployeeByEmail(@RequestBody EmployeeEntity employeeEntity){
		if(employeeEntity.getEmployeeEmail()==null||employeeEntity.getEmployeeEmail().length()<=0){
			throw new InsufficientDataException("Employee email id is not present");
		}
		else {
			return employeeService.addEmployeeByEmail(employeeEntity);
		}
	}

	@GetMapping("/{employeeEmail}")
	Mono<ResponseEntity<EmployeeEntity>> findEmployeeByEmail(@PathVariable String employeeEmail){
		return employeeService.findEmployeeByEmail(employeeEmail).map(employeeEntity -> ResponseEntity.ok(employeeEntity))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping
	Flux<EmployeeEntity> findAllEmployee(){
		return employeeService.findAllEmployee();
	}

	@DeleteMapping("/{employeeEmail}")
	Mono<ResponseEntity<Void>> deleteEmployeeByEmail(@PathVariable String employeeEmail){
		return employeeService.deleteEmployeeByEmail(employeeEmail)
				.map(r->ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PutMapping
	Mono<ResponseEntity<EmployeeEntity>> updateEmployee(@RequestBody EmployeeEntity employeeEntity){
		if(employeeEntity.getEmployeeEmail()==null||employeeEntity.getEmployeeEmail().length()<=0){
			throw new InsufficientDataException("Employee email id is not present");
		}
		else {
			return employeeService.updateEmployee(employeeEntity)
					.map(employeeEntity1 -> ResponseEntity.ok(employeeEntity1))
					.defaultIfEmpty(ResponseEntity.badRequest().build());
		}
	}


}