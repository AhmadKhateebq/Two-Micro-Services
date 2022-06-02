package com.example.Test;

import com.example.Test.model.GroceryItem;
import com.example.Test.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MongoDbApplication implements CommandLineRunner {
    @Autowired
    ItemRepository groceryItemRepo;

    public static void main(String[] args) {
        SpringApplication.run (MongoDbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println ("Data creation started...");
        groceryItemRepo.save (new GroceryItem ("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
        groceryItemRepo.save (new GroceryItem ("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
        groceryItemRepo.save (new GroceryItem ("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
        groceryItemRepo.save (new GroceryItem ("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
        groceryItemRepo.save (new GroceryItem ("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
        System.out.println ("Data creation complete...");
        groceryItemRepo.findAll ().forEach (item -> System.out.println (getItemDetails (item)));
    }

    // 2. Get item by name
    public void getGroceryItemByName(String name) {
        System.out.println ("Getting item by name: " + name);
        GroceryItem item = groceryItemRepo.findItemByName (name);
        System.out.println (getItemDetails (item));
    }

    public String getItemDetails(GroceryItem item) {

        System.out.println (
                "Item Name: " + item.getName () +
                        ", \nQuantity: " + item.getQuantity () +
                        ", \nItem Category: " + item.getCategory ()
        );

        return "";

    }

//    public void run(String... args) {
//        Controller controller = new Controller ();
//        System.out.println ("-------------CREATE GROCERY ITEMS-------------------------------\n");
//
//        controller.createGroceryItems ();
//
//        System.out.println ("\n----------------SHOW ALL GROCERY ITEMS---------------------------\n");
//
//        controller.showAllGroceryItems ();
//
//        System.out.println ("\n--------------GET ITEM BY NAME-----------------------------------\n");
//
//        controller.getGroceryItemByName ("Whole Wheat Biscuit");
//
//        System.out.println ("\n-----------GET ITEMS BY CATEGORY---------------------------------\n");
//
//        controller.getItemsByCategory ("millets");
//
//        System.out.println ("\n-----------UPDATE CATEGORY NAME OF SNACKS CATEGORY----------------\n");
//
//        controller.updateCategoryName ("snacks");
//
//        System.out.println ("\n----------DELETE A GROCERY ITEM----------------------------------\n");
//
//        controller.deleteGroceryItem ("Kodo Millet");
//
//        System.out.println ("\n------------FINAL COUNT OF GROCERY ITEMS-------------------------\n");
//
//        controller.findCountOfGroceryItems ();
//
//        System.out.println ("\n-------------------THANK YOU---------------------------");
//
//    }

}
