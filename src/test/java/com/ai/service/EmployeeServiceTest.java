package com.ai.service;

import com.ai.mapper.EmployeeMapper;
import com.ai.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	private final EmployeeMapper employeeMapper = new EmployeeMapper();

	@Test
	void deleteRemovesExistingEmployee() {
		EmployeeService service = new EmployeeService(employeeRepository, employeeMapper);
		when(employeeRepository.existsById(1L)).thenReturn(true);

		service.delete(1L);

		verify(employeeRepository).existsById(1L);
		verify(employeeRepository).deleteById(1L);
	}

	@Test
	void deleteThrowsNotFoundForMissingEmployee() {
		EmployeeService service = new EmployeeService(employeeRepository, employeeMapper);
		when(employeeRepository.existsById(1L)).thenReturn(false);

		ResponseStatusException exception = assertThrows(ResponseStatusException.class,
				() -> service.delete(1L));

		assertEquals(404, exception.getStatusCode().value());
		verify(employeeRepository).existsById(1L);
		verifyNoMoreInteractions(employeeRepository);
	}
}
