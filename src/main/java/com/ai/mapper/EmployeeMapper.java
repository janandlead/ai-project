package com.ai.mapper;

import com.ai.dto.EmployeeDto;
import com.ai.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

	public Employee toEntity(EmployeeDto dto) {
		return new Employee(dto.id(), dto.name(), dto.salary());
	}

	public EmployeeDto toDto(Employee employee) {
		return new EmployeeDto(employee.getId(), employee.getName(), employee.getSalary());
	}
}
