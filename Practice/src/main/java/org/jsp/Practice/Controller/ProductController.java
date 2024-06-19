package org.jsp.Practice.Controller;

import java.util.List;

import org.jsp.Practice.Dto.Product;
import org.jsp.Practice.Dto.ResponseStructure;
import org.jsp.Practice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@Autowired
	private ProductService service;

	@PostMapping("/products/{user_id}")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product,
			@PathVariable int user_id) {
		return service.saveProduct(product, user_id);
	}

	@PutMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product) {
		return service.updateProduct(product);
	}
	@GetMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> findByProductId(@PathVariable(name = "id") int id)
	{
		return service.findByProductId(id);
	}
	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProduct()
	{
		return service.findAllProduct();
	}
	@GetMapping("/products/find-by-brand/{brand}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable(name = "brand") String brand)
	{
		return service.findByBrand(brand);
	}
	@GetMapping("/products/find-by-category/{category}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@PathVariable(name = "category") String category)
	{
		return service.findByCategory(category);
	}
}
