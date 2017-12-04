package wpl.spring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import wpl.spring.entity.RegistryItem;
import wpl.spring.entity.User;
import wpl.spring.service.RegistryItemService;

@Controller
@RequestMapping("/item")
public class RegistryItemController {

	//inject addToREgistryservice
	@Autowired
	private RegistryItemService registryItemService;
	//------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/add/", method = RequestMethod.POST,
            consumes = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> add(@RequestBody RegistryItem ri)
	{
		System.out.println("itemid:" +ri.getItemId() + "Quantity: " +ri.getQuantity());
		registryItemService.addRegistryItem(ri);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	//-------------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/getitem/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RegistryItem> getUser(@RequestParam("registrtyId") int registryId, @RequestParam("itemId") int itemId) {
//        System.out.println("Fetching User with email " + email);
        RegistryItem registryItem = registryItemService.getRegistryItem(registryId, itemId);
        if (registryItem == null) {
            System.out.println("item having item id " + itemId + " not found");
            return new ResponseEntity<RegistryItem>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<RegistryItem>(registryItem, HttpStatus.OK);
    }
	
	//---------------------------------------------------------------------
	@CrossOrigin
	 @RequestMapping(value = "/itemlist/", method = RequestMethod.GET)
	    public ResponseEntity<List<RegistryItem>> getAllitems() {
	        List<RegistryItem> items = registryItemService.getAllRegistryItem();
	        if(items.isEmpty()){
	            return new ResponseEntity<List<RegistryItem>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<RegistryItem>>(items, HttpStatus.OK);
	    }
	
}
