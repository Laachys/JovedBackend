package com.joved.controller;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.joved.entity.Product;
import com.joved.entity.User;
import com.joved.interfaces.BuyProduct;
import com.joved.interfaces.LoginUser;
import com.joved.interfaces.NewFavorite;
import com.joved.interfaces.ProductI;
import com.joved.interfaces.RegisterUser;
import com.joved.service.JovedService;


@RestController
@RequestMapping("/apirest")
@CrossOrigin("*")
public class Controller {
	
	@Autowired
	private JovedService service;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	
	@PostMapping("/save")
	public ResponseEntity<User> save( @RequestBody User user){
		return new ResponseEntity<>(service.save(user), HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> finAll(){
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id){
		return new ResponseEntity<>(service.get(id), HttpStatus.OK);
	}
	
	/*
	@PostMapping("/comprueba")
	public ResponseEntity<Object> comprueba(@RequestBody User user){
		return new ResponseEntity<Object>(service.compruebaUser(user) , HttpStatus.OK);
	}*/
	
	@PostMapping("/comprueba")
	public ResponseEntity<User> comprueba(@RequestBody String x){
		return new ResponseEntity<>( service.findByUsername(x), HttpStatus.OK);
	}
	
	@PostMapping(path ="/verifica")
	 @PreAuthorize("permitAll") // Permite el acceso sin autenticació
	public ResponseEntity<Object> verifica(@RequestBody LoginUser login) {
		//System.out.println(login.getUsername());
		return new ResponseEntity<Object>( service.usercorrect(login), HttpStatus.OK);
	}
	
	@PostMapping(path="/addUser")
	public  ResponseEntity<Object> addUSer(@RequestBody RegisterUser register){
		//System.out.println(register.getName());
		return new ResponseEntity<Object>( service.addNewUser(register), HttpStatus.OK);
	}
	
	@PostMapping(path ="/checkUsername")
	public ResponseEntity<Object> checkUsername(@RequestBody String username) {
		return new ResponseEntity<>( service.checkUsername(username) , HttpStatus.OK);
	}
	
	
	@PostMapping("/allforcategory")
	public ResponseEntity<Map<String, String>> finAll(@RequestBody Integer category){
		System.out.println(category);
		return new ResponseEntity<Map<String, String>>(service.AllForCategory(category), HttpStatus.OK);
	}
	
	
	 @Value("${file.location}")
	 private String uploadDir;
	 
	 @PostMapping("/upload")
	    public String uploadFile(@RequestParam("files") List<MultipartFile> files, RedirectAttributes redirectAttributes) {
		 for (MultipartFile file : files) {
	        if (file.isEmpty()) {
	            redirectAttributes.addFlashAttribute("message", "Por favor, seleccione un archivo para cargar.");
	            return "redirect:/uploadStatus";
	        }

	        try {
	        	String uniqueFileName = UUID.randomUUID().toString()+ ".jpeg";
	            Path subdirectoryPath = Path.of(uploadDir, uniqueFileName);
	            Files.createDirectories(subdirectoryPath);
	            Path filePath =subdirectoryPath.resolve(uniqueFileName);
	            
	            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	            redirectAttributes.addFlashAttribute("message", "Archivo cargado con éxito: " + file.getOriginalFilename());
	        } catch (IOException e) {
	            e.printStackTrace();
	            redirectAttributes.addFlashAttribute("message", "Error al cargar el archivo: " + e.getMessage());
	        }

	        
	    }
		 return "redirect:/uploadStatus";
	 }

	 private MultipartFile files; 
	 
	 @PostMapping(value="/newproduct" )
	 public void addproduct(@RequestBody Object product) {
		 
		//System.out.println(product.getAddress());
		//System.out.println(product.getId_user());
		//System.out.println(product.getFile());
		 System.out.println(product);
	 
	 }
	 
	 @PostMapping("/addproduct")
	 public ResponseEntity<Object> addproduct2( @RequestParam("files") List<MultipartFile> files, @RequestParam("form") String form) {
		 Gson gson = new Gson();
		 ProductI product = gson.fromJson(form, ProductI.class);
		 return new ResponseEntity<Object>( service.addProduct(files, product), HttpStatus.OK);
		 
	 }
	 
	 
	 @PostMapping("/savefavorite")
	 public ResponseEntity<Object> savefavorite(@RequestBody NewFavorite newfavorite ){
		 return new ResponseEntity<Object>( service.newfavorite(newfavorite), HttpStatus.OK);
	 }
	 
	 @PostMapping("/allfavorites")
		public ResponseEntity<Object> allfavorites(@RequestBody String id_user){
			return new ResponseEntity<Object>(service.allFavorites(id_user), HttpStatus.OK);
		}
	 
	 @PostMapping("/product")
	 public ResponseEntity<Map<String, String>> findProductById(@RequestBody Integer id_product){
		 return new ResponseEntity<>(service.findProduct(id_product), HttpStatus.OK);
	 }
		
	 @PostMapping("/buyproduct")
	 public ResponseEntity<Object> buyproduct(@RequestBody BuyProduct product ){
		 return new ResponseEntity<Object>( service.buyproduct(product), HttpStatus.OK);
	 }
	 
	 @GetMapping("/allproducts")
	 public ResponseEntity<Map<String, String>> finAllProducts(){
		 return new ResponseEntity<Map<String, String>>(service.findAllProducts(), HttpStatus.OK);
		}
	 
	 @PostMapping("/allproductsbyid")
	 public ResponseEntity<Map<String, String>> finAllProductsByIdUser( @RequestBody String user){
		 return new ResponseEntity<Map<String, String>>(service.findAllProductsByUser(user), HttpStatus.OK);
		}
	 
	 
	 @PostMapping("/changepassword")
	 public ResponseEntity<Object> chagepassword( @RequestParam("newpass") String newpass, @RequestParam("oldpass") String oldpass, @RequestParam("id_user") String id_user) {
		
		 // Gson gson = new Gson();
		 //ProductI product = gson.fromJson(form, ProductI.class);
		 return new ResponseEntity<Object>( service.changepassword(newpass, oldpass, id_user), HttpStatus.OK);
		 
	 }
	 
	 @PostMapping("/changeemail")
	 public ResponseEntity<Object> chageemail( @RequestParam("passemail") String password, @RequestParam("email") String newemail, @RequestParam("id_user") String id_user) {
		
		 // Gson gson = new Gson();
		 //ProductI product = gson.fromJson(form, ProductI.class);
		 return new ResponseEntity<Object>( service.changeemail(password, newemail, id_user), HttpStatus.OK);
		 
	 }
	 
	 @PostMapping("/checkpass")
	 public ResponseEntity<Object> checkpass( @RequestParam("passclose") String password, @RequestParam("id_user") String id_user) {
		 return new ResponseEntity<Object>( service.checkpass(password, id_user), HttpStatus.OK);
		 
	 }
	 
	 @PostMapping("/desactivateacount")
	 public ResponseEntity<Object> desactivateacount( @RequestParam("passclose") String password, @RequestParam("id_user") String id_user) {
		 return new ResponseEntity<Object>( service.desactivate(password, id_user), HttpStatus.OK);
		 
	 }
}

