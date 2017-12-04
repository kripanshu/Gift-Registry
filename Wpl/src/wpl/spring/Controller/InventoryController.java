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
import org.springframework.web.client.RestTemplate;

import wpl.spring.entity.Inventory;

@Controller
@RequestMapping("/inventory")
public class InventoryController {


	//------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/add/", method = RequestMethod.POST,
	consumes = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> add(@RequestBody Inventory inventory)
	{
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unused")
		String result = restTemplate.postForObject("http://localhost:8090/Wpl/inventory/add/",inventory, String.class);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	//-------------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/getitem/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Inventory> getInventoryItem(@RequestParam("itemId") int itemId) {		        


		RestTemplate restTemplate = new RestTemplate();
		Inventory inventoryItem = restTemplate.getForObject("http://localhost:8090/Wpl/inventory/getitem/?itemId={itemId}", Inventory.class, itemId);

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


		RestTemplate restTemplate = new RestTemplate();
		Inventory inventoryItemUpdate = restTemplate.getForObject("http://localhost:8090/Wpl/inventory/getitem/?itemId={itemId}", Inventory.class, itemId);


		if (inventoryItemUpdate==null) {
			System.out.println("Item with id " + itemId + " not found");
			return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
		}

		inventoryItemUpdate.setItemName(inventory.getItemName());
		inventoryItemUpdate.setCategory(inventory.getCategory());
		inventoryItemUpdate.setDescription(inventory.getDescription());
		inventoryItemUpdate.setPrice(inventory.getPrice());
		inventoryItemUpdate.setQuantity(inventory.getQuantity());


		String url = "http://localhost:8090/Wpl/inventory/updateitem/?itemId={itemId}";
		restTemplate.put(url, inventoryItemUpdate, itemId);



		return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
	}
	//---------------------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/inventorylist/", method = RequestMethod.GET)
	public ResponseEntity<List<Inventory>> getAllRegistries() {


		RestTemplate restTemplate = new RestTemplate();
		List<Inventory> inventoryitems = restTemplate.getForObject("http://localhost:8090/Wpl/inventory/inventorylist/", List.class);


		if(inventoryitems.isEmpty()){
			return new ResponseEntity<List<Inventory>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Inventory>>(inventoryitems, HttpStatus.OK);
	}
	//---------------------------------------------------------------------------
	@CrossOrigin
	@RequestMapping(value = "/deleteinventory/", method = RequestMethod.DELETE)
	public ResponseEntity<Inventory> deleteItem(@RequestParam("itemId") int itemId) {

		RestTemplate restTemplate = new RestTemplate();

		String url = "http://localhost:8090/Wpl/inventory/deleteinventory/?itemId={itemId}";
		restTemplate.delete(url,itemId);

		return new ResponseEntity<Inventory>(HttpStatus.NO_CONTENT);
	}


}
