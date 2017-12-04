package wpl.spring.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.json.simple.JSONObject;

import wpl.spring.entity.User;
import wpl.spring.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	//--------------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/add/", method = RequestMethod.POST,
            consumes = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> add(@RequestBody User user) throws IOException	{
		
		System.out.println("email:" +user.getEmail() + "1st name: " +user.getFirstName());
		userService.addUser(user);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	//--------------------------------------get user by email-----------------
	
	@CrossOrigin
//	@Cacheable(value="findCache", key="#email")
	 @RequestMapping(value = "/getuser/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public ResponseEntity<User> getUser(@RequestParam("email") String email) {
	        System.out.println("Fetching User with email " + email);
	        User user = userService.getUser(email);
	        if (user == null) {
	            System.out.println("User with email " + email + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<User>(user, HttpStatus.OK);
	    }
	
	
	
		//form action: Update
	@CrossOrigin
		@RequestMapping(value = "/updateuser/", method = RequestMethod.PUT)
//	@Cacheable(value="findCache", key="#email")
	    public ResponseEntity<User> updateUser(@RequestParam("email") String email, @RequestBody User user) {
	        System.out.println("Updating User " + email);
	         
	        User currentUser = userService.getUser(email);
	         
	        if (currentUser==null) {
	            System.out.println("User with email " + email + " not found");
	            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	        }
	 
	        currentUser.setFirstName(user.getFirstName());
	        currentUser.setLastName(user.getLastName());
	        currentUser.setPassword(user.getPassword());
	        currentUser.setSecurityQuestion(user.getSecurityQuestion());
	        currentUser.setSecurityAnswer(user.getSecurityAnswer());
	        currentUser.setPassword(user.getPassword());
	        currentUser.setStreet(user.getStreet());
	        currentUser.setState(user.getState());
		    currentUser.setZipcode( user.getZipcode());
		    currentUser.setCountry(user.getCountry());
	         
	        int result = userService.updateUser(currentUser);
	        if(result>0)
	        	return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	        else
	        	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	    }
	//----------------------------------------------------------------------
	@CrossOrigin
		 @RequestMapping(value = "/deleteuser/", method = RequestMethod.DELETE)
		    public ResponseEntity<User> deleteUser(@RequestParam("email") String email) {
		        System.out.println("Fetching & Deleting User with id " + email);
		 
		        User user = userService.getUser(email);
		        if (user == null) {
		            System.out.println("Unable to delete. User with email " + email + " not found");
		            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		        }
		 
		        userService.deleteUser(email);
		        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		    }
	//------------------------------------------------------------------------------------
	@CrossOrigin
		 @RequestMapping(value = "/userlist/", method = RequestMethod.GET)
		    public ResponseEntity<List<User>> getAllUsers() {
		        List<User> users = userService.getAllUsers();
		        if(users.isEmpty()){
		            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		    }
	//--------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	@CrossOrigin
	 @RequestMapping(value = "/authuser/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public ResponseEntity<JSONObject> authUser(@RequestParam("email") String email,@RequestParam("password") String password) {
	        System.out.println("Fetching User with email " + email);
	        String token = userService.authenticateUser(email, password);
	        
	        JSONObject user = new JSONObject();
	        user.put("email", email);
	        user.put("token", token);
	        
	        
	        if (token == null) {
	            System.out.println("User with email " + email + " not found");
	            return new ResponseEntity<JSONObject>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<JSONObject>(user, HttpStatus.OK);
	    }
	//--------------------------------------check email availability-----------------
	
	@CrossOrigin
	 @RequestMapping(value = "/emailavailable/", method = RequestMethod.GET)
	    public ResponseEntity<String> emailAvailable(@RequestParam("email") String email) {
	        System.out.println("Fetching User with email " + email);
	        boolean emailavailable = userService.emailAvailability(email);
	        if (emailavailable == true) {
	            System.out.println("User with email " + email + " is not found and can be used");
	            return new ResponseEntity<String>(HttpStatus.OK);
	        }
	        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	    }
		 

}
