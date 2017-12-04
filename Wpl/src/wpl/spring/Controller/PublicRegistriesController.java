package wpl.spring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wpl.spring.entity.Registry;
import wpl.spring.service.PublicRegistriesService;

@Controller
@RequestMapping("/registry")
public class PublicRegistriesController {

	@Autowired
	private PublicRegistriesService publicRegistriesService;
	
	@RequestMapping("/public")
	public String listPublicRegistries(Model theModel)
	{
		//get registries from Service layer
		List<Registry> publicRegistries = publicRegistriesService.getPublicRegistries();
		
		//add list to model
		theModel.addAttribute("publicRegistry", publicRegistries);
		return "public-registry";
	}
	
}
