package com.brillio.pocreactive;

import com.brillio.pocreactive.entitiy.employee.EmployeeEntity;
import com.brillio.pocreactive.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebFluxTest
class PocReactiveApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private EmployeeService employeeService;


	EmployeeEntity employeeEntity;
	@BeforeEach
	public void setUp(){
		employeeEntity=new EmployeeEntity(1,"john@gmail.com","John Murphy","engineer","New york");
	}

	@Test
	public void givenEmployeeEmailThenShouldReturnEmployeeDetailsWithStatusIsOk(){
		when(employeeService.findEmployeeByEmail("john@gmail.com")).thenReturn(Mono.just(employeeEntity));
		webTestClient.get().uri("/api/v1/john@gmail.com")
				.exchange()
				.expectStatus().isOk()
				.expectBody().jsonPath("$.employeeName","John Murphy");
	}

	@Test
	public void givenEmployeeEmailIfEmployeeIsNotPresentThenShouldReturnStatusIsNotFound(){
		when(employeeService.findEmployeeByEmail(anyString())).thenReturn(Mono.empty());
		webTestClient.get().uri("/api/v1/john@gmail.com")
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	public void givenEmployeeDeatilsThenShouldReturnStatusIsOkWithEmployeeDetailsAlongWithId(){
		when(employeeService.addEmployeeByEmail(employeeEntity)).thenReturn(Mono.just(employeeEntity));
		webTestClient.post().uri("/api/v1")
				.contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
				.body(Mono.just(employeeEntity),EmployeeEntity.class)
				.exchange().expectStatus().isOk()
				.expectBody().jsonPath("$.employee_id").isNotEmpty()
				.jsonPath("$.employeeName","John Murphy");

	}

	@Test
	public void givenEmployeeEmailToDeleteThenShouldDeleteEmployeeAndReturnEmployeeDetails(){
		when(employeeService.deleteEmployeeByEmail("john@gmail.com")).thenReturn(Mono.just(employeeEntity));
		webTestClient.delete().uri("/api/v1/john@gmail.com")
				.exchange()
				.expectStatus().isOk()
				.expectBody().jsonPath("employeeEmail","john@gmail.com");
	}

	@Test
	public void givenEmployeeEmailToDeleteIfEmployeeIsNotPresentThenShouldReturnStatusIsNotFound(){
		when(employeeService.deleteEmployeeByEmail(anyString())).thenReturn(Mono.empty());
		webTestClient.delete().uri("/api/v1/john@gmail.com")
				.exchange()
				.expectStatus().isNotFound();
	}



}
