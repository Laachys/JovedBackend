package com.joved.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
//import com.joved.SecurityConfig;
import com.joved.entity.Favorite;
import com.joved.entity.Product;
import com.joved.entity.Response;
import com.joved.entity.Sold;
import com.joved.entity.User;
import com.joved.interfaces.BuyProduct;
import com.joved.interfaces.ImageI;
import com.joved.interfaces.LoginUser;
import com.joved.interfaces.NewFavorite;
import com.joved.interfaces.ProductI;
import com.joved.interfaces.RegisterUser;
import com.joved.repository.FavoriteRepository;
import com.joved.repository.ProductRepository;
import com.joved.repository.SoldRepository;
import com.joved.repository.UserRepository;

@Service
public class JovedService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SoldRepository soldRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User get(Integer id) {
		return userRepository.findById(id).get();
	}
	
	
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}
	
	public User findByUsername(String username) {
		return  userRepository.findByUsername(username);
	}
	
	public Integer usercorrect(LoginUser login) {
		/*Funcion que realiza la comprobación del usuario y contraseña recogidas del formulario*/
		User p = userRepository.findByUsername(login.getUsername());
		if(!Objects.isNull(p)) {
			//String password = passwordEncoder.encode(login.getPassword());
			//System.out.println(login.getPassword());
			// Crear un encoder BCrypt lo que hace es desencriptar la contraseña
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        
			if(encoder.matches(login.getPassword(), p.getPassword())) {
				//System.out.println(p.getId());
				return p.getId();
			}
			else {
				return null;
			}
		}
		else {
		return null;
		}
	}
	//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	public Response addNewUser(RegisterUser register){
		Response response = new Response();
		User checkifexists = userRepository.findByUsername(register.getUsername());
		if(Objects.isNull(checkifexists) && !Objects.isNull(register)) {
			
			
			User newuser = new User();
			newuser.setName(register.getName());
			newuser.setEmail(register.getEmail());
			newuser.setSurname(register.getSurname());
			newuser.setUsername(register.getUsername());
			newuser.setDate(register.getDate());
			
			String hashedPassword = passwordEncoder.encode(register.getPassword());
			System.out.println(hashedPassword);
			newuser.setPassword(hashedPassword);
			newuser.setPhone(register.getPhone());
			userRepository.save(newuser);
			response.setResponse("Creado con exito");
			return response;
		}else {
			response.setResponse("El usuario ya existe");
			return response;
		}
		
	}
	
	public Object checkUsername(String username) {
		User checkuser = userRepository.findByUsername(username);
		Response response = new Response();
		
		if(!Objects.isNull(checkuser)) {
			response.setResponse("El usuario ya existe");
			return response;
		}else {
			response.setResponse(null);
			return response;
		}
	}
	
	public Map<String, String> AllForCategory(Integer category) {
		//List<Product> products = productRepository.findByCategoryIs(category);
		List<Product> products = productRepository.findByCategoryAndActiveNot(category, "0");
		

		List<ImageI> imageList = new ArrayList<>();
		
		 for (Product product : products) {
			 //System.out.println(product.toString());
			 String path = uploadDir+"/" + product.getId_user().toString() + "/" + product.getId();
			 File folder = new File(path);
			 //System.out.println(folder.getAbsolutePath());
			 //File folder = new File(path);
		        File[] imageFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".png"));

		        if (imageFiles != null) {
		            for (File imageFile : imageFiles) {
		            	ImageI image = new ImageI();
		            	image.setName(imageFile.getName());
		            	image.setPath(imageFile.getAbsolutePath());
		            	imageList.add(image);
		            	//imageList.add((MultipartFile) imageFile);
		            }
		        }   
			 
		 }
		 
		 String json = new Gson().toJson(imageList);
		 System.out.print(json);
		 String productsjson = new Gson().toJson(products);
		 
		 Map<String, String> parametros = new HashMap<>();
		 parametros.put("files",json);
		 parametros.put("products",productsjson);
		 
		return parametros;
	}
	
	public Map<String, String> findAllProducts(){
		List<Product> products = productRepository.findByActive("1");
		List<ImageI> imageList = new ArrayList<>();
		
		 for (Product product : products) {
			 //System.out.println(product.toString());
			 String path = uploadDir+"/" + product.getId_user().toString() + "/" + product.getId();
			 File folder = new File(path);
			 //System.out.println(folder.getAbsolutePath());
			 //File folder = new File(path);
		        File[] imageFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".png"));

		        if (imageFiles != null) {
		            for (File imageFile : imageFiles) {
		            	ImageI image = new ImageI();
		            	image.setName(imageFile.getName());
		            	image.setPath(imageFile.getAbsolutePath());
		            	imageList.add(image);
		            	//imageList.add((MultipartFile) imageFile);
		            }
		        }   
			 
		 }
		 
		 String json = new Gson().toJson(imageList);
		 System.out.print(json);
		 String productsjson = new Gson().toJson(products);
		 
		 Map<String, String> parametros = new HashMap<>();
		 parametros.put("files",json);
		 parametros.put("products",productsjson);
		 
		return parametros;
	}
	
	//C:\\Users\\laura\\OneDrive\\Escritorio\\Joved repositorio\\Joved\\src\\assets\\jovedfiles
	 @Value("${file.location}")
	 private String uploadDir;
	 
	public Response addProduct(List<MultipartFile> files, ProductI form ) {
		Response response = new Response();
		if(!Objects.isNull(form)) {
			Product newproduct = new Product();
			newproduct.setName(form.getName());
			newproduct.setPrice(form.getPrice());
			newproduct.setId_user(form.getId_user());
			newproduct.setDescription(form.getDescription());
			newproduct.setCategory(form.getCategory());
			newproduct.setAddress(form.getAddress());
			newproduct.setActive(form.getActive());
			productRepository.save(newproduct);
			response.setResponse("Saved");
			
			//tras su insercción vamos a guardar las imagenes
			int x=0;
			 for (MultipartFile file : files) {
				 try {
					 	String newPath = newproduct.getId_user();
			        	String uniqueFileName = newproduct.getId()+"_" +x+ ".jpeg";
			        	String secundaryPath = newproduct.getId().toString();
			            Path subdirectoryPath = Path.of(uploadDir, newPath, secundaryPath);
			            Files.createDirectories(subdirectoryPath);
			            Path filePath =subdirectoryPath.resolve(uniqueFileName);
			            
			            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			            x++;
			        } catch (IOException e) {
			            e.printStackTrace();   
			        }
			 }
		}
		return response;
	}
	
	public Response newfavorite(NewFavorite newfavorite) {
		Response response = new Response();
		
		if(!Objects.isNull(newfavorite)) {
			
			Favorite addfavorite = new Favorite();
			addfavorite.setUser(newfavorite.getId_user());
			addfavorite.setProduct(newfavorite.getId_product());
			if(favoriteRepository.findByUserAndProduct(newfavorite.getId_user(), newfavorite.getId_product()) != null) {
				Favorite oldfavorite = favoriteRepository.findByUserAndProduct(newfavorite.getId_user(), newfavorite.getId_product());
				favoriteRepository.delete(oldfavorite);
				System.out.print("ELIMINADO");
				response.setResponse("false");
			}else {
				System.out.print("CREADO");
				favoriteRepository.save(addfavorite);
				response.setResponse("true");
			}
		}else {
			response.setResponse("false");
		}
		return response;
	}
	
	public List<Favorite> allFavorites(String id_user) {
		return favoriteRepository.findAllByUser(id_user);
	}

	public Map<String, String> findProduct(Integer id_product) {
		
		Product product = productRepository.findById(id_product).get();
		List<ImageI> imageList = new ArrayList<>();
		
		User user = userRepository.findById(Integer.parseInt( product.getId_user())).get();
		String path = uploadDir+"/" + product.getId_user().toString() + "/" + product.getId();
		File folder = new File(path);
			 
		File[] imageFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".png"));

		if (imageFiles != null) {
		    for (File imageFile : imageFiles) {
		         ImageI image = new ImageI();
		         image.setName(imageFile.getName());
		         image.setPath(imageFile.getAbsolutePath());
		         imageList.add(image);
		      }
		}   
			 
		 
		 String json = new Gson().toJson(imageList);
		 String productsjson = new Gson().toJson(product);
		 String userjson = new Gson().toJson(user);
		 
		 Map<String, String> parametros = new HashMap<>();
		 parametros.put("user", userjson);
		 parametros.put("files",json);
		 parametros.put("product",productsjson);
		 
		return parametros;
	}
	
	
	public Response buyproduct(BuyProduct infoproduct) {
		System.out.println(infoproduct.getId_user());
		Response response = new Response();
		
		if(!Objects.isNull(infoproduct)) {
			Sold sold = new Sold();
			sold.setUser(infoproduct.getId_user());
			sold.setProduct(infoproduct.getId_product());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String fecha = sdf.format(new Date());
			sold.setDate(fecha);
			soldRepository.save(sold);
			
			//Actualizamos en la bbdd que el producto ya no está activo
			Product product = productRepository.findById(Integer.parseInt(infoproduct.getId_product())).get();
			product.setActive("0");
			productRepository.save(product);
			
			response.setResponse("true");
		}
		
		return response;
	}

	
	public Map<String, String> findAllProductsByUser(String user){
		System.out.println(user);
		List<Product> products = productRepository.findByUserAndActiveNot(user , "0");
		
		List<ImageI> imageList = new ArrayList<>();
		
		 for (Product product : products) {
			 //System.out.println(product.toString());
			 String path = uploadDir+"/" + product.getId_user().toString() + "/" + product.getId();
			 File folder = new File(path);
			 //System.out.println(folder.getAbsolutePath());
			 //File folder = new File(path);
		        File[] imageFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".png"));

		        if (imageFiles != null) {
		            for (File imageFile : imageFiles) {
		            	ImageI image = new ImageI();
		            	image.setName(imageFile.getName());
		            	image.setPath(imageFile.getAbsolutePath());
		            	imageList.add(image);
		            	//imageList.add((MultipartFile) imageFile);
		            }
		        }   
			 
		 }
		 
		 String json = new Gson().toJson(imageList);
		// System.out.print(json);
		 String productsjson = new Gson().toJson(products);
		 System.out.print(productsjson);
		 //System.out.println("¿HOLA?");
		 Map<String, String> parametros = new HashMap<>();
		 parametros.put("files",json);
		 parametros.put("products",productsjson);
		 
		return parametros;
	}
	
	public Response changepassword(String newpass, String oldpass , String id_user) {
		Response response = new Response();
		User p = userRepository.findById(Integer.parseInt(id_user)).get();
		if(!Objects.isNull(p)) {
			//String password = passwordEncoder.encode(login.getPassword());
			//System.out.println(login.getPassword());
			// Crear un encoder BCrypt lo que hace es desencriptar la contraseña
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        
			if(encoder.matches(oldpass, p.getPassword())) {
				
				String hashedPassword = passwordEncoder.encode(newpass);
				p.setPassword(hashedPassword);
				userRepository.save(p);
				response.setResponse("true");
			}
			else {
				response.setResponse("false");
			}
		}
		else {
			response.setResponse("false");
		}
		return response;
	}
	
	
	public Response changeemail(String password, String newemail , String id_user) {
		Response response = new Response();
		User p = userRepository.findById(Integer.parseInt(id_user)).get();
		if(!Objects.isNull(p)) {
			//String password = passwordEncoder.encode(login.getPassword());
			//System.out.println(login.getPassword());
			// Crear un encoder BCrypt lo que hace es desencriptar la contraseña
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        
			if(encoder.matches(password, p.getPassword())) {
				
				p.setEmail(newemail);
				userRepository.save(p);
				response.setResponse("true");
			}
			else {
				response.setResponse("false");
			}
		}
		else {
			response.setResponse("false");
		}
		return response;
	}
	
	public Response checkpass(String password, String id_user) {
		Response response = new Response();
		User p = userRepository.findById(Integer.parseInt(id_user)).get();
		if(!Objects.isNull(p)) {
			//String password = passwordEncoder.encode(login.getPassword());
			//System.out.println(p.getEmail());
			// Crear un encoder BCrypt lo que hace es desencriptar la contraseña
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        
			if(encoder.matches(password, p.getPassword())) {
				response.setResponse("true");
			}
			else {
				response.setResponse("false");
			}
		}
		else {
			response.setResponse("false");
		}
		return response;
	}
	
	public Response desactivate(String password, String id_user) {
		Response response = new Response();
		User p = userRepository.findById(Integer.parseInt(id_user)).get();
		if(!Objects.isNull(p)) {
			//String password = passwordEncoder.encode(login.getPassword());
			//System.out.println(p.getEmail());
			// Crear un encoder BCrypt lo que hace es desencriptar la contraseña
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        
			if(encoder.matches(password, p.getPassword())) {
				//****COMPROBAMOS SI TIENE PRODUCTOS 
				List<Product> products = productRepository.findByUserAndActiveNot(id_user , "0");
				 for (Product product : products) {
					 product.setActive("0");
					productRepository.save(product);
				}
				userRepository.delete(p);
				response.setResponse("true");
			}
			else {
				response.setResponse("false");
			}
		}
		else {
			response.setResponse("false");
		}
		return response;
	}
}
