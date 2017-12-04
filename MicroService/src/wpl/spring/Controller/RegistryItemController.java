package wpl.spring.Controller;

import java.util.ArrayList;
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
import org.springframework.web.client.RestTemplate;

import wpl.spring.entity.Inventory;
import wpl.spring.entity.RegistryItem;
import wpl.spring.service.InventoryService;
import wpl.spring.service.RegistryItemService;

@Controller
@RequestMapping("/item")
public class RegistryItemController {

	//inject addToREgistryservice
	@Autowired
	private RegistryItemService registryItemService;
	
	@Autowired
	private InventoryService inventoryService;
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
    public ResponseEntity<RegistryItem> getUser(@RequestParam("registryUrl") String registryUrl, @RequestParam("itemId") int itemId) {
//        System.out.println("Fetching User with email " + email);
        RegistryItem registryItem = registryItemService.getRegistryItem(registryUrl, itemId);
        if (registryItem == null) {
            System.out.println("item having item id " + itemId + " not found");
            return new ResponseEntity<RegistryItem>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<RegistryItem>(registryItem, HttpStatus.OK);
    }
	
	
	//----------------------------------------------------------------------
	
	@CrossOrigin
	@RequestMapping(value = "/updateitem/", method = RequestMethod.PUT)
    public ResponseEntity<RegistryItem> updateUser(@RequestParam("registryUrl") String registryUrl, @RequestParam("itemId") int itemId, @RequestBody RegistryItem registryItem) {
        System.out.println("Updating item with item  " + itemId);
         
        RegistryItem registryItemUpdate = registryItemService.getRegistryItem(registryUrl, itemId);
         
        if (registryItemUpdate==null) {
            System.out.println("Item with id " + itemId + " not found");
            return new ResponseEntity<RegistryItem>(HttpStatus.NOT_FOUND);
        }
 
        registryItemUpdate.setQuantity(registryItem.getQuantity());
         
        int result = registryItemService.updateRegistryItem(registryItemUpdate);;
        if(result>0)
        	return new ResponseEntity<RegistryItem>(registryItemUpdate, HttpStatus.OK);
        else
        	return new ResponseEntity<RegistryItem>(HttpStatus.NOT_FOUND);
    }
	
	//---------------------------------------------------------------------------
	@CrossOrigin
	 @RequestMapping(value = "/deleteitem/", method = RequestMethod.DELETE)
	    public ResponseEntity<RegistryItem> deleteItem(@RequestParam("registryUrl") String registryUrl, @RequestParam("itemId") int itemId) {
	        System.out.println("Fetching & Deleting Item with id " + itemId);
	 
	        RegistryItem deleteRegistryItem = registryItemService.getRegistryItem(registryUrl, itemId);
	        if (deleteRegistryItem == null) {
	            System.out.println("Unable to delete. Item with id " + itemId + " not found");
	            return new ResponseEntity<RegistryItem>(HttpStatus.NOT_FOUND);
	        }
	 
	        registryItemService.deleteRegistryItem(deleteRegistryItem);
	        return new ResponseEntity<RegistryItem>(HttpStatus.NO_CONTENT);
	    }
	//---------------------------------------------------------------------------
		@CrossOrigin
		 @RequestMapping(value = "/registryitems/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		    public ResponseEntity<List<Inventory>> registryItems(@RequestParam("registryUrl") String registryUrl) {
		        System.out.println("Fetching All Items for Registry" + registryUrl);
		        
		        List<RegistryItem> registryItems = registryItemService.getRegistryItems(registryUrl);
		        List<Inventory> inventoryitems =new ArrayList<Inventory>();
		        Inventory temp;
		       
		        for(RegistryItem item :registryItems){
		        	temp =inventoryService.getInventoryItem(item.getItemId());
		        	inventoryitems.add(temp);
		        }
		 
		        if(inventoryitems.isEmpty()){
		            return new ResponseEntity<List<Inventory>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<List<Inventory>>(inventoryitems, HttpStatus.OK);
		    }

		//----------------------------------------------------------------------
		
		@CrossOrigin
		@RequestMapping(value = "/selfassign/", method = RequestMethod.PUT)
	    public ResponseEntity<RegistryItem> selfassign(@RequestParam("email") String email, @RequestBody RegistryItem registryItem) {
	        System.out.println("Updating item with item  " + registryItem.getItemId()+" and RegistryUrl : "+ registryItem.getRegistryUrl());
	         
	        RegistryItem registryItemUpdate = registryItemService.getRegistryItem(registryItem.getRegistryUrl(), registryItem.getItemId());
	         
	        if (registryItemUpdate==null) {
	            System.out.println("Item with id " + registryItem.getItemId()+ " not found");
	            return new ResponseEntity<RegistryItem>(HttpStatus.NOT_FOUND);
	        }
	 
	        registryItemUpdate.setTaken(1);
	        registryItemUpdate.setEmail(email);
	        
	         
	        int result = registryItemService.selfAssign(registryItemUpdate, email);
	        if(result>0)
	        	return new ResponseEntity<RegistryItem>(registryItemUpdate, HttpStatus.OK);
	        else
	        	return new ResponseEntity<RegistryItem>(HttpStatus.NOT_FOUND);
	    }
		//---------------------------------------------------------------------------
		@CrossOrigin
		 @RequestMapping(value = "/registryitemdetail/",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		    public ResponseEntity<List<RegistryItem>> registryItemdetail(@RequestParam("registryUrl") String registryUrl) {
		        System.out.println("Fetching All Items for Registry" + registryUrl);
		        
		        List<RegistryItem> registryItems = registryItemService.getRegistryItems(registryUrl);
		        System.out.println(registryItems);
		 
		        if(registryItems.isEmpty()){
		            return new ResponseEntity<List<RegistryItem>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<List<RegistryItem>>(registryItems, HttpStatus.OK);
		    }

		

}
