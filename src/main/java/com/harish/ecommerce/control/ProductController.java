package com.harish.ecommerce.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harish.ecommerce.dto.ProductDTO;
import com.harish.ecommerce.service.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	ProductServiceImpl service;
	
	@PostMapping
	public ProductDTO addProductDTO(@RequestBody ProductDTO dto) {
		service.addProduct(dto);
		return dto;	
	}
	
	 @GetMapping
	    public List<ProductDTO> getAllProducts() {
	        return service.getAllProducts();
	    }

	    // 3️⃣ Get Product By Id
	    @GetMapping("/{id}")
	    public ProductDTO getProductById(@PathVariable Long id) {
	        return service.getProductById(id);
	    }

	    // 4️⃣ Update Product
	    @PutMapping("/{id}")
	    public ProductDTO updateProduct(
	            @PathVariable Long id,
	            @RequestBody ProductDTO productDTO) {
	        return service.updateProduct(id, productDTO);
	    }

	    // 5️⃣ Delete Product
	    @DeleteMapping("/{id}")
	    public String deleteProduct(@PathVariable Long id) {
	        service.deleteProduct(id);
	        return "Product deleted successfully";
	    }

}
