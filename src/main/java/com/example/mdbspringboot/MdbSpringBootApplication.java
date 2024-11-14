package com.example.mdbspringboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.mdbspringboot.repository.CustomItemRepository;
import com.example.mdbspringboot.repository.ItemRepository;
import com.example.mdbspringboot.GroceryItem;
//import com.example.mdbspringboot.SimpleMongoRepository;

@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication implements CommandLineRunner{
	
	@Autowired
	ItemRepository groceryItemRepo;
	
	@Autowired
	CustomItemRepository customRepo;
	
	List<GroceryItem> itemList = new ArrayList<GroceryItem>();

 

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}

	void createGroceryItems() {
		System.out.println("Data creation started...");

		groceryItemRepo.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
		groceryItemRepo.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
		groceryItemRepo.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
		groceryItemRepo.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
		groceryItemRepo.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
		
		System.out.println("Data creation complete...");
	}

	public void showAllGroceryItems() {
		 
		itemList = groceryItemRepo.findAll();
		
		itemList.forEach(item -> System.out.println(getItemDetails(item)));
	}

	// public void getGroceryItemByName(String name) {
	// 	System.out.println("Getting item by name: " + name);
	// 	GroceryItem item = groceryItemRepo.findItemByName(name);
	// 	System.out.println(getItemDetails(item));
	// }

	public void getItemsByCategory(String category) {
		System.out.println("Getting items for the category " + category);
		List<GroceryItem> list = groceryItemRepo.findAll(category);
		
		list.forEach(item -> System.out.println("Name: " + item.getName() + ", Quantity: " + item.getItemQuantity()));
	}

	public void findCountOfGroceryItems() {
		long count = groceryItemRepo.count();
		System.out.println("Number of documents in the collection = " + count);
	}

	// public void updateCategoryName(String category) {
		 
	// 	String newCategory = "munchies";
		
	// 	List<GroceryItem> list = groceryItemRepo.findAll(category);
		
	// 	list.forEach(item -> {
	// 		item.setCategory(newCategory);
	// 	});
		
	// 	List<GroceryItem> itemsUpdated = groceryItemRepo.saveAll(list);
		
	// 	if(itemsUpdated != null)
	// 		System.out.println("Successfully updated " + itemsUpdated.size() + " items.");		 
	// }

	// public void deleteGroceryItem(String id) {
	// 	groceryItemRepo.deleteById(id);
	// 	System.out.println("Item with id " + id + " deleted...");
	// }
	// public String getItemDetails(GroceryItem item) {

	// 	System.out.println(
	// 	"Item Name: " + item.getName() + 
	// 	", \nItem Quantity: " + item.getItemQuantity() + 
	// 	", \nItem Category: " + item.getCategory()
	// 	);
	// 		 return "";
	// }

	public void run(String... args) {
		
		groceryItemRepo.deleteAll(); 
		
		System.out.println("-------------CREATE GROCERY ITEMS-------------------------------\n");
		
		createGroceryItems();
		
		System.out.println("\n----------------SHOW ALL GROCERY ITEMS---------------------------\n");
		
		showAllGroceryItems();
		
		System.out.println("\n--------------GET ITEM BY NAME-----------------------------------\n");
		
		// getGroceryItemByName("Whole Wheat Biscuit");
		
		System.out.println("\n-----------GET ITEMS BY CATEGORY---------------------------------\n");
		
		getItemsByCategory("millets");
		
		System.out.println("\n-----------UPDATE CATEGORY NAME OF ALL GROCERY ITEMS----------------\n");
		
		// updateCategoryName("snacks");
		
		System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");
		
		// deleteGroceryItem("Kodo Millet");
		
		System.out.println("\n------------FINAL COUNT OF GROCERY ITEMS-------------------------\n");
		
		findCountOfGroceryItems();
		
		System.out.println("\n-------------------THANK YOU---------------------------");
						
	}

	

}

