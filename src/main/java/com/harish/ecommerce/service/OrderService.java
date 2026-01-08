package com.harish.ecommerce.service;

import java.util.List;

import com.harish.ecommerce.dto.OrderDTO;
import com.harish.ecommerce.dto.OrderResponseDTO;
import com.harish.ecommerce.model.Orders;

public interface OrderService {

    OrderResponseDTO createOrder(OrderDTO orderDTO);

    OrderDTO getOrderById(Long id);

    List<OrderDTO> getAllOrders();

	List<OrderDTO> getOrdersByUserId(Long userId);
}

