package com.harish.ecommerce.service;

import java.util.List;

import com.harish.ecommerce.dto.ProductDTO;

public interface ProductService {
	
	ProductDTO addProduct(ProductDTO dto);

    ProductDTO getProductById(Long id);

    List<ProductDTO> getAllProducts();

    ProductDTO updateProduct(Long id, ProductDTO dto);

    void deleteProduct(Long id);

}
