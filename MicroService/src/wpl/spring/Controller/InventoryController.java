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

import wpl.spring.entity.Inventory;
import wpl.spring.service.InventoryService;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	//------------------------------------------------------
			@CrossOrigin
			@RequestMapping(value = "/add/", method = RequestMethod.POST,
		            consumes = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<Void> add(@RequestBody Inventory inventory)
			{
				System.out.println("Inventory Item Id:" +inventory.getItemId() + "Item Name: " +inventory.getItemName());
				inventoryService.addInventryItem(inventory);
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
			
	//-------------------------------------------------------------
			@CrossOrigin
			@RequestMapping(value = "/getitem/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		    public ResponseEntity<Inventory> getInventoryItem(@RequestParam("itemId") int itemId) {		        
				Inventory inventoryItem = inventoryService.getInventoryItem(itemId);
		        if (inventoryItem == null) {
		            System.out.println("item having item id " + itemId + " not found");
		            return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
		        }
		        return new ResponseEntity<Inventory>(inventoryItem, HttpStatus.OK);
		    }
	//----------------------------------------------------------------------
			
			@CrossOrigin
			@RequestMapping(value = "/updateitem/", method = RequestMethod.PUT)
		    public ResponseEntity<Inventory> updateitem(@RequestParam("itemId") int itemId, @RequestBody Inventory inventory) {
		        System.out.println("Updating item with item  " + itemId);
		         
		        Inventory inventoryItemUpdate = inventoryService.getInventoryItem(itemId);
		         
		        if (inventoryItemUpdate==null) {
		            System.out.println("Item with id " + itemId + " not found");
		            return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
		        }

		        inventoryItemUpdate.setItemName(inventory.getItemName());
		        inventoryItemUpdate.setCategory(inventory.getCategory());
		        inventoryItemUpdate.setDescription(inventory.getDescription());
		        inventoryItemUpdate.setPrice(inventory.getPrice());
		        inventoryItemUpdate.setQuantity(inventory.getQuantity());
		         
		        int result = inventoryService.updateInventoryItem(inventoryItemUpdate);
		        if(result>0)
		        	return new ResponseEntity<Inventory>(inventoryItemUpdate, HttpStatus.OK);
		        else
		        	return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
		    }
	//---------------------------------------------------------------------
			@CrossOrigin
			 @RequestMapping(value = "/inventorylist/", method = RequestMethod.GET)
			    public ResponseEntity<List<Inventory>> getAllRegistries() {
			        List<Inventory> inventoryitems = inventoryService.getInventory();
			        if(inventoryitems.isEmpty()){
			            return new ResponseEntity<List<Inventory>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
			        }
			        return new ResponseEntity<List<Inventory>>(inventoryitems, HttpStatus.OK);
			    }
	//---------------------------------------------------------------------------
			@CrossOrigin
			 @RequestMapping(value = "/deleteinventory/", method = RequestMethod.DELETE)
			    public ResponseEntity<Inventory> deleteItem(@RequestParam("itemId") int itemId) {
			        System.out.println("Fetching & Deleting Registry with id " + itemId);
			        inventoryService.deleteInventoryItem(itemId);
			        return new ResponseEntity<Inventory>(HttpStatus.NO_CONTENT);
			    }


}
