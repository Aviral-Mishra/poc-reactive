package com.brillio.pocreactive.entitiy.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeEntity {
	@Id
	private int employee_id;
	private String employeeEmail;
	private String employeeName;
	private String employeeDesignation;
	private String employeeAddress;
}
