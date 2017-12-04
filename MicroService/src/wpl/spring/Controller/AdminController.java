package wpl.spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import wpl.spring.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@CrossOrigin
	 @RequestMapping(value = "/authadmin/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Cacheable(value="findCache", key="#email")
	    public ResponseEntity<String> authAdmin(@RequestParam("email") String email,@RequestParam("password") String password) {
	        System.out.println("Fetching Admin with email from database " + email);
	        String adminEmail = adminService.authenticateAdmin(email, password);
	        if (adminEmail == null) {
	            System.out.println("User with email " + email + " not found");
	            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<String>(adminEmail, HttpStatus.OK);
	    }
	

}
