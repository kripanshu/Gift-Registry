package wpl.spring.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;



@Controller
@RequestMapping("/admin")
public class AdminController {

	@CrossOrigin
	@RequestMapping(value = "/authadmin/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> authAdmin(@RequestParam("email") String email,@RequestParam("password") String password) {
		System.out.println("Fetching Admin with email " + email);

		RestTemplate restTemplate = new RestTemplate();
		String adminEmail = restTemplate.getForObject("http://localhost:8090/Wpl/admin/authadmin/?email={email}&password={password}", String.class, email,password);


		if (adminEmail == null) {
			System.out.println("User with email " + email + " not found");
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(adminEmail, HttpStatus.OK);
	}


}
