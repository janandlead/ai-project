package com.ai.service;

import com.ai.dto.EmployeeDto;
import com.ai.entity.Employee;
import com.ai.mapper.EmployeeMapper;
import com.ai.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;

	public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
		this.employeeRepository = employeeRepository;
		this.employeeMapper = employeeMapper;
	}

	public EmployeeDto create(EmployeeDto dto) {
		Employee employee = employeeMapper.toEntity(dto);
		Employee savedEmployee = employeeRepository.save(employee);
		return employeeMapper.toDto(savedEmployee);
	}

	public EmployeeDto update(Long id, EmployeeDto dto) {
		if (!employeeRepository.existsById(id)) {
			throw new ResponseStatusException(NOT_FOUND, "Employee not found: " + id);
		}

		Employee employee = new Employee(id, dto.name(), dto.salary());
		Employee savedEmployee = employeeRepository.save(employee);
		return employeeMapper.toDto(savedEmployee);
	}

	public void delete(Long id) {
		if (!employeeRepository.existsById(id)) {
			throw new ResponseStatusException(NOT_FOUND, "Employee not found: " + id);
		}

		employeeRepository.deleteById(id);
	}
}
