package com.harish.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harish.ecommerce.dto.OrderDTO;
import com.harish.ecommerce.dto.OrderResponseDTO;
import com.harish.ecommerce.model.Orders;
import com.harish.ecommerce.model.Product;
import com.harish.ecommerce.model.Users;
import com.harish.ecommerce.repo.OrderRepo;
import com.harish.ecommerce.repo.ProductRepo;
import com.harish.ecommerce.repo.UserRepo;
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private OrderRepo orderRepo;
	@Override
	public OrderResponseDTO createOrder(OrderDTO orderDTO) {

	    Users user = userRepo.findById(orderDTO.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    List<Product> products = orderDTO.getProductIds().stream()
	            .map(id -> productRepo.findById(id)
	                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + id)))
	            .toList();

	    double total = products.stream()
	            .mapToDouble(Product::getPrice)
	            .sum();

	    Orders order = new Orders();
	    order.setUser(user);
	    order.setProducts(products);
	    order.setTotalPrice(total);
	    order.setLocalDateTime(LocalDateTime.now());
	    order.setStatus("CREATED");

	    Orders savedOrder = orderRepo.save(order);

	    return MapToRDTO(savedOrder);
	}

	public OrderResponseDTO MapToRDTO(Orders order) {
		OrderResponseDTO dto=new OrderResponseDTO();
		dto.setStatus(order.getStatus());
		dto.setTotalPrice(order.getTotalPrice());
		dto.setMessage("Order Created Successfully");
		dto.setOrderId(order.getId());
		return dto;
		
	}
	public OrderDTO getOrderById(Long id) {
		// TODO Auto-generated method stub
		Orders o=orderRepo.findById(id).orElseThrow(()->new RuntimeException("Order Not Found"));
		return mapToDTO(o);
	}
	
	private OrderDTO mapToDTO(Orders order) {

	    OrderDTO dto = new OrderDTO();

	    // User ID
	    dto.setUserId(order.getUser().getId());
        dto.setOrderId(order.getId());
	    // Product IDs
	    List<Long> productIds = order.getProducts()
	            .stream()
	            .map(Product::getId)
	            .toList();
	    dto.setProductIds(productIds);

	    // Total price
	    dto.setTotalPrice(order.getTotalPrice());

	    return dto;
	}

	 public List<OrderDTO> getAllOrders() {
	        return orderRepo.findAll().stream()
	                .map(this::mapToDTO)
	                .toList();
	    }
	 @Override
	 public List<OrderDTO> getOrdersByUserId(Long userId) {

	     // Optional: validate user exists
	     userRepo.findById(userId)
	             .orElseThrow(() -> new RuntimeException("User not found"));

	     return orderRepo.findByUserId(userId)
	             .stream()
	             .map(this::mapToDTO)
	             .toList();
	 }


}
