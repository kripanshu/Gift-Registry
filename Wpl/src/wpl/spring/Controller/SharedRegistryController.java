package wpl.spring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import wpl.spring.entity.RegistryItem;
import wpl.spring.entity.SharedRegistry;

@Controller
@RequestMapping("/sharedregistry")
public class SharedRegistryController {
	
	
	//------------------------------------------------------
		@CrossOrigin
		@RequestMapping(value = "/add/", method = RequestMethod.POST,
	            consumes = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Void> add(@RequestBody SharedRegistry sharedRegistry)
		{
//			System.out.println("SharedReg:" +sharedRegistry.getRegistryUrl());
//			sharedRegistryService.addToShared(sharedRegistry);
			
			RestTemplate restTemplate = new RestTemplate();
			@SuppressWarnings("unused")
			String result = restTemplate.postForObject("http://localhost:8090/Wpl/sharedregistry/add/",sharedRegistry, String.class);
			
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		//---------------------------------------------------------------------
		@CrossOrigin
		 @RequestMapping(value = "/registrylist/", method = RequestMethod.GET)
		    public ResponseEntity<List<SharedRegistry>> getAllitems(@RequestParam("email") String email) {
		        
//			List<SharedRegistry> registrylist = sharedRegistryService.getSharedWithUser(email);
			
			RestTemplate restTemplate = new RestTemplate();
			@SuppressWarnings("unchecked")
			List<SharedRegistry> registrylist = restTemplate.getForObject("http://localhost:8090/Wpl/sharedregistry/registrylist/?email={email}", List.class, email);
		        
			
		        if(registrylist.isEmpty()){
		            return new ResponseEntity<List<SharedRegistry>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<List<SharedRegistry>>(registrylist, HttpStatus.OK);
		    }
	

}
