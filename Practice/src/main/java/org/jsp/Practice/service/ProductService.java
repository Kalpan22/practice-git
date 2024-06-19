package org.jsp.Practice.service;
import java.util.List;
import java.util.Optional;
import org.jsp.Practice.Dao.ProductDao;
import org.jsp.Practice.Dao.UserDao;
import org.jsp.Practice.Dto.Product;
import org.jsp.Practice.Dto.ResponseStructure;
import org.jsp.Practice.Dto.User;
import org.jsp.Practice.Exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int user_id) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<User> dbUser = userDao.findById(user_id);
		if (dbUser.isPresent()) {
			User recUser = dbUser.get();
			recUser.getProducts().add(product);
			userDao.saveUser(recUser);
			product.setUser(recUser);
			structure.setData(productDao.saveProduct(product));
			structure.setMessage("product saved");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		Optional<Product> dbProduct = productDao.findByProductId(product.getId());
		if (dbProduct.isPresent()) {
			Product recProduct = dbProduct.get();
			recProduct.setName(product.getName());
			recProduct.setUser(product.getUser());
			recProduct.setDescription(product.getDescription());
			recProduct.setBrand(product.getBrand());
			recProduct.setCategory(product.getCategory());
			recProduct.setCost(product.getCost());
			structure.setData(productDao.saveProduct(recProduct));
			structure.setMessage("products had been updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure<Product>> findByProductId(int id)
	{
		ResponseStructure<Product> structure=new ResponseStructure<>();
		Optional<Product> dbProduct=productDao.findByProductId(id);
		if(dbProduct.isPresent())
		{
			structure.setData(dbProduct.get());
			structure.setMessage("Product Details");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		Optional<Product> dbProduct=productDao.findByProductId(id);
		if(dbProduct.isPresent())
		{
			productDao.deleteProduct(id);
			structure.setData(id+" Product id is Deleted");
			structure.setMessage("Product deleted");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProduct()
	{
		ResponseStructure<List<Product>> structure=new ResponseStructure<>();
		structure.setData(productDao.findAll());
		structure.setMessage("All the Products");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.ACCEPTED);
	}
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand)
	{
		ResponseStructure<List<Product>> structure=new ResponseStructure<>();
		structure.setData(productDao.findByBrand(brand));
		structure.setMessage("List of All the Product distribute by"+brand);
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category)
	{
		ResponseStructure<List<Product>> structure=new ResponseStructure<>();
		structure.setData(productDao.findByCategory(category));
		structure.setMessage("List of All the Product distribute by"+category);
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
	}
	
}