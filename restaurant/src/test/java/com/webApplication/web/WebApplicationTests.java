package com.webApplication.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.webApplication.web.entity.Employee;
import com.webApplication.web.entity.MealDTO;
import com.webApplication.web.entity.Order;
import com.webApplication.web.entity.OrderList;
import com.webApplication.web.repository.EmployeeRepo;
import com.webApplication.web.repository.MealOrderRepo;
import com.webApplication.web.repository.MealRepo;
import com.webApplication.web.service.AdminService;
import com.webApplication.web.service.BalanceService;
import com.webApplication.web.service.EmployeeService;
import com.webApplication.web.service.OrderService;

class WebApplicationTests {
	
	@Mock
    private EmployeeRepo employeeRepo;
	
    @InjectMocks
    private EmployeeService employeeService;
	
	@Spy
	private BalanceService balanceService;
	
	@Spy
	private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        balanceService.setBalance(1000);
		adminService.logout();
    }

    @Test
    void testCalculateSalaryPositive() {
        List<Employee> employees = List.of(
            new Employee("Chef", 1000),
            new Employee("Waiter", 500)
        );
        when(employeeRepo.findAll()).thenReturn(employees);

        int result = employeeService.calculateSalary();

        assertEquals(1500, result);
    }

	@Test 
	void testCalculateSalaryNegative() {

		List<Employee> employees = List.of(
			new Employee("Delivery man", -100),
			new Employee("Chef", -1000)
		);

		when(employeeRepo.findAll()).thenReturn(employees);

		int result = employeeService.calculateSalary();

		assertEquals(0, result);
	}

	@Test
	void testCalculateSalaryEmptyList() {
		when(employeeRepo.findAll()).thenReturn(List.of());
		int result = employeeService.calculateSalary();
		assertEquals(0, result);
	}

	@Test
    void testAddToBalance() {
        balanceService.addToBalance(200);
        assertEquals(1200, balanceService.getBalance());
    }

	@Test
    void testRemoveFromBalance() {
        balanceService.removeFromBalance(300);
        assertEquals(700, balanceService.getBalance());
    }

	@Test
    void testSetBalance() {
        balanceService.setBalance(999);
        assertEquals(999, balanceService.getBalance());
    }

	@Test 
	void testRemoveFromBalanceToZero() {
		balanceService.removeFromBalance(1001);
		assertEquals(0, balanceService.getBalance());
	}

	@Test
	void testRemoveExactBalance() {
		balanceService.setBalance(500);
		balanceService.removeFromBalance(500);
		assertEquals(0, balanceService.getBalance());
	}

	@Test
	void testDefaultAdminStatus() {
		assertEquals(false, adminService.isAdmin());
	}

	@Mock
    private MealRepo mealRepo;

    @Mock
    private MealOrderRepo mealOrderRepo;

    @InjectMocks
    private OrderService orderService;


	@Test
	void testPriceCalculationWithDelivery() {

		MealDTO meal = new MealDTO();
		meal.setId(1);
        meal.setPricetopay(200);
        meal.setPricetocook(120);

		Order order = new Order();
		order.setId(1);
		order.setQuantity(3);

		OrderList orderList = new OrderList();
		orderList.setOrders(List.of(order));
		orderList.setDelivery(true);

		when(mealRepo.findById(1)).thenReturn(Optional.of(meal));

		Integer result = orderService.getPriceToGet(orderList);
        assertEquals(339, result);
	}

	@Test
	void testPriceCalculationWithoutDelivery() {

		MealDTO meal = new MealDTO();
		meal.setId(1);
        meal.setPricetopay(200);
        meal.setPricetocook(120);

		Order order = new Order();
		order.setId(1);
		order.setQuantity(3);

		OrderList orderList = new OrderList();
		orderList.setOrders(List.of(order));
		orderList.setDelivery(false);

		when(mealRepo.findById(1)).thenReturn(Optional.of(meal));

		Integer result = orderService.getPriceToGet(orderList);
        assertEquals(339 - 99, result);
	}

	@Test
	void testOrderTimeMealNotFound_shouldThrowException() {
		Order order = new Order();
		order.setId(1);
		order.setQuantity(15);

		OrderList orderList = new OrderList();
		orderList.setOrders(List.of(order));
		orderList.setDelivery(false);

		when(mealRepo.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> {
			orderService.getOrderTime(orderList);
		});
	}


}
