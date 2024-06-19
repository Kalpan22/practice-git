package org.jsp.Practice.Dao;

import java.util.List;
import java.util.Optional;

import org.jsp.Practice.Dto.Product;
import org.jsp.Practice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao 
{
	@Autowired
	private ProductRepository repository;
	
	public Product saveProduct(Product product)
	{
		return repository.save(product);
	}
	public Optional<Product> findByProductId(int id)
	{
		return repository.findById(id);
	}
	public Boolean deleteProduct(int id)
	{
		Optional<Product> dbPro=repository.findById(id);
		if(dbPro.isPresent())
		{
			repository.delete(dbPro.get());
			return true;
		}
		return false;
	}
	public List<Product> findByBrand(String brand)
	{
		return repository.findByBrand(brand);
	}
	public List<Product> findByCategory(String category)
	{
		return repository.findByCategory(category);
	}
	public List<Product> findAll()
	{
		return repository.findAll();
	}
}
