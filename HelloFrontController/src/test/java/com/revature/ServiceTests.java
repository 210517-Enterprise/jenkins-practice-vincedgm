package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.model.Employee;
import com.revature.repositories.EmployeeDAOImpl;
import com.revature.service.EmployeeService;

public class ServiceTests {

	private EmployeeDAOImpl edaoImpl;
	
	@Before
	public void setup() {
		edaoImpl = mock(EmployeeDAOImpl.class);
		
		EmployeeService.edao = edaoImpl;
	}
	
	@Test
	public void test_Employee_findByUsername() {
		Employee e1 = new Employee(1, "a", "b", "c", "d");
		Employee e2 = new Employee(2, "e", "f", "g", "h");
		
		List<Employee> list = new ArrayList<Employee>();
		list.add(e1);
		list.add(e2);
		
		when(edaoImpl.findAll()).thenReturn(list);
		
		String dummyUser = e1.getUsername();
		
		Employee returned = EmployeeService.findByUsername(dummyUser);
		
		assertEquals(e1, returned);
	}
	
	@Test
	public void employeeNotPresentInDb() {
		List<Employee> emptyList = new ArrayList<Employee>();
		
		when(edaoImpl.findAll()).thenReturn(emptyList);
		
		Employee emp = EmployeeService.findByUsername("test");
		
		assertNull(emp);
	}
}
