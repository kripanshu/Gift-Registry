package wpl.spring.Controller;

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

import wpl.spring.entity.Registry;
import wpl.spring.service.RegistryService;
import wpl.spring.service.SharedRegistryService;

@Controller
@RequestMapping("/registry")
public class RegistryController {
	
	@Autowired
	private RegistryService registryService;
	@Autowired
	private SharedRegistryService sharedRegistryService;
	
	//------------------------------------------------------
		@CrossOrigin
		@RequestMapping(value = "/add/", method = RequestMethod.POST,
	            consumes = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<Void> add(@RequestBody Registry registry)
		{
			System.out.println("RegistryName: " +registry.getRegistryName());
			registryService.addRegistry(registry);
			if(registry.getShare() == 1)
				sharedRegistryService.addAutomated("ShareToAll", registry.getRegistryUrl());
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		//-------------------------------------------------------------
		@CrossOrigin
		@RequestMapping(value = "/getregistry/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//		@Cacheable(value="findCache", key="#registryUrl")
		public ResponseEntity<Registry> getRegistry(@RequestParam("registryUrl") String registryUrl) {
	        Registry registry = registryService.getRegistry(registryUrl);
	        if (registry == null) {
	            System.out.println("Registry having URL " + registryUrl + " not found");
	            return new ResponseEntity<Registry>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Registry>(registry, HttpStatus.OK);
	    }
		//---------------------------------------------------------------------
			@CrossOrigin
			@RequestMapping(value = "/registrylist/", method = RequestMethod.GET)
//			@Cacheable(value="findCache", key="#userEmail")
		    public ResponseEntity<List<Registry>> getAllRegistries(@RequestParam("userEmail") String userEmail) {
		        
				List<Registry> registries = registryService.getUserRegistry(userEmail);
				
		        if(registries.isEmpty()){
		            return new ResponseEntity<List<Registry>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<List<Registry>>(registries, HttpStatus.OK);
		    }
		//------------------------------------------------------------------------------------
		
		@CrossOrigin
		@RequestMapping(value = "/updateregistry/", method = RequestMethod.PUT)
	    public ResponseEntity<Registry> updateUser(@RequestParam("registryUrl") String registryUrl, @RequestBody Registry registry) {
	        System.out.println("Updating Registry with url  " + registryUrl);
	            
	        int result = registryService.updateRegistry(registry);
	        if(result>0)
	        	return new ResponseEntity<Registry>(registry, HttpStatus.OK);
	        else
	        	return new ResponseEntity<Registry>(HttpStatus.NOT_FOUND);
	    }
		
		//---------------------------------------------------------------------------
		@CrossOrigin
		 @RequestMapping(value = "/deleteregistry/", method = RequestMethod.DELETE)
		    public ResponseEntity<Registry> deleteItem(@RequestParam("registryUrl") String registryUrl) {
		        System.out.println("Fetching & Deleting Registry with id " + registryUrl);
		        registryService.deleteRegistry(registryUrl);
		        return new ResponseEntity<Registry>(HttpStatus.NO_CONTENT);
		    }
		//--------------------------------------check email availability-----------------
		
		@CrossOrigin
		 @RequestMapping(value = "/urlavailable/", method = RequestMethod.GET)
		    public ResponseEntity<String> emailAvailable(@RequestParam("registryUrl") String registryUrl) {
		        System.out.println("Fetching Registry with URL  " + registryUrl);
		        boolean userEmail = registryService.urlAvailability(registryUrl);
		        if (userEmail == true) {
		            System.out.println("User Registry with URL " + registryUrl + " is not found and can be used");
		            return new ResponseEntity<String>(HttpStatus.OK);
		        }
		        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		    }
	

}
