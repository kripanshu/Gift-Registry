package wpl.spring.Controller;

import java.util.List;

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
import org.springframework.web.client.RestTemplate;

import wpl.spring.entity.Registry;

@Controller
@RequestMapping("/registry")
public class RegistryController {


	//------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/add/", method = RequestMethod.POST,
	consumes = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> add(@RequestBody Registry registry)
	{
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unused")
		String result = restTemplate.postForObject("http://localhost:8090/Wpl/registry/add/",registry, String.class);

		//			if(registry.getShare() == 1)
		//				sharedRegistryService.addAutomated("Public", registry.getRegistryUrl());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	//-------------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/getregistry/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@Cacheable(value="findCache", key="#registryUrl")
	public ResponseEntity<Registry> getRegistry(@RequestParam("registryUrl") String registryUrl) {

		RestTemplate restTemplate = new RestTemplate();
		Registry registry = restTemplate.getForObject("http://localhost:8090/Wpl/registry/getregistry/?registryUrl={registryUrl}", Registry.class, registryUrl);


		if (registry == null) {
			System.out.println("Registry having URL " + registryUrl + " not found");
			return new ResponseEntity<Registry>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Registry>(registry, HttpStatus.OK);
	}
	//---------------------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/registrylist/", method = RequestMethod.GET)
//	@Cacheable(value="findCache", key="#userEmail")

	public ResponseEntity<List<Registry>> getAllRegistries(@RequestParam("userEmail") String userEmail) {

		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		List<Registry> registries = restTemplate.getForObject("http://localhost:8090/Wpl/registry/registrylist/?userEmail={userEmail}", List.class, userEmail);


		if(registries.isEmpty()){
			return new ResponseEntity<List<Registry>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Registry>>(registries, HttpStatus.OK);
	}

	//---------------------------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/deleteregistry/", method = RequestMethod.DELETE)
	public ResponseEntity<Registry> deleteItem(@RequestParam("registryUrl") String registryUrl) {

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8090/Wpl/registry/deleteregistry/?registryUrl={registryUrl}";
		restTemplate.delete(url,registryUrl);


		return new ResponseEntity<Registry>(HttpStatus.NO_CONTENT);
	}



}
