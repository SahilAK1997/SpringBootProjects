package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Product;
import com.project.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService proService;

	
	

	@PostMapping
	public Product createProduct(@RequestBody Product product)
	{
		return proService.createProduct(product);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product pro) {
		Product existingProduct = proService.getProductById(id);

		if (existingProduct != null) {
			pro.setId(id);

			return ResponseEntity.ok(proService.updateProduct(pro));
		}

		else
			return ResponseEntity.notFound().build();

	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	
		Product existingProduct = proService.getProductById(id);
		if (existingProduct != null)
		{
			proService.deleteProduct(id);
			return ResponseEntity.noContent().build();
		} 
		else 
			return ResponseEntity.notFound().build();
		
	}


	
	@GetMapping
	public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) 
	{
		return proService.getAllProducts(page, size);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product pro = proService.getProductById(id);
		if (pro != null)
		{
			return ResponseEntity.ok(pro);
		} 
		else
	       return ResponseEntity.notFound().build();
		
	}
	
	
}
