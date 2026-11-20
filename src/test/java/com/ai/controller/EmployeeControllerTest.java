package com.ai.controller;

import com.ai.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmployeeControllerTest {

	private final EmployeeService employeeService = mock(EmployeeService.class);
	private final MockMvc mockMvc = standaloneSetup(new EmployeeController(employeeService)).build();

	@Test
	void deleteReturnsNoContent() throws Exception {
		mockMvc.perform(delete("/rest/employees/1"))
				.andExpect(status().isNoContent());

		verify(employeeService).delete(1L);
	}
}
