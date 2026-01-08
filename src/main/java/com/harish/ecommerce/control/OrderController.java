package com.harish.ecommerce.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harish.ecommerce.dto.OrderDTO;
import com.harish.ecommerce.dto.OrderResponseDTO;
import com.harish.ecommerce.model.Orders;
import com.harish.ecommerce.service.OrderService;
import com.harish.ecommerce.service.OrderServiceImpl;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderServiceImpl serviceImpl;
	
	 @PostMapping
	    public OrderResponseDTO createOrder(@RequestBody OrderDTO orderDTO) {
	        return serviceImpl.createOrder(orderDTO);
	    }

	    // 2️⃣ Optional: Get All Orders
	    @GetMapping
	    public List<OrderDTO> getAllOrders() {
	        return serviceImpl.getAllOrders();
	    }

	    // 3️⃣ Optional: Get Order by Id
	    @GetMapping("/{id}")
	    public OrderDTO getOrderById(@PathVariable Long id) {
	        return serviceImpl.getOrderById(id);
	    }
	    @GetMapping("/user/{userId}")
	    public List<OrderDTO> getOrdersByUserId(@PathVariable Long userId) {
	        return serviceImpl.getOrdersByUserId(userId);
	    }

}
