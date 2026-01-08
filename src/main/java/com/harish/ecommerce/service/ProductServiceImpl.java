package com.harish.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harish.ecommerce.dto.ProductDTO;
import com.harish.ecommerce.model.Product;
import com.harish.ecommerce.repo.ProductRepo;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepo repo;

	@Override
	public ProductDTO addProduct(ProductDTO dto) {
		// TODO Auto-generated method stub
		Product p=new Product();
		p.setName(dto.getName());
		p.setDescription(dto.getDescription());
		p.setPrice(dto.getPrice());
		p.setQuantity(dto.getQuantity());
		repo.save(p);
		return MapToDTO(p);
	}

	private ProductDTO MapToDTO(Product p) {
		// TODO Auto-generated method stub
		ProductDTO dto=new ProductDTO();
		dto.setName(p.getName());
		dto.setPrice(p.getPrice());
		dto.setQuantity(p.getQuantity());
		dto.setDescription(p.getDescription());
		return dto;
	}

	@Override
	public ProductDTO getProductById(Long id) {
		// TODO Auto-generated method stub
		Product p=repo.findById(id).orElseThrow(()->new RuntimeException("Product Not Found"));
		return MapToDTO(p);
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		// TODO Auto-generated method stub
		
		return repo.findAll().stream().map(this::MapToDTO).collect(Collectors.toList());
	}

	@Override
	public ProductDTO updateProduct(Long id, ProductDTO dto) {
		// TODO Auto-generated method stub
		Product p=repo.findById(id).orElseThrow(()->new RuntimeException("Product Not Found"));
		p.setName(dto.getName());
		p.setDescription(dto.getDescription());
		p.setQuantity(dto.getQuantity());
		p.setPrice(dto.getPrice());
		repo.save(p);
		return MapToDTO(p);
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		repo.findById(id).orElseThrow(()->new RuntimeException("Product Not Found"));
		
	}

}
