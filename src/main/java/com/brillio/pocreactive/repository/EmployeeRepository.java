package com.brillio.pocreactive.repository;

import com.brillio.pocreactive.entitiy.employee.EmployeeEntity;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Repository
@EnableR2dbcRepositories
public interface EmployeeRepository extends ReactiveCrudRepository<EmployeeEntity,String> {

	Mono<EmployeeEntity> findByEmployeeEmail(String employeeEmail);
}
