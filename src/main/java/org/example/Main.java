import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Scanner;

public class Main {
    private static MongoCollection<Document> collection;

    public static void main(String[] args) {
        // Connect to MongoDB
        MongoClient mongoClient = MongoClients.create(new ConnectionString("mongodb://localhost:27017"));
        MongoDatabase database = mongoClient.getDatabase("inventoryDB");
        collection = database.getCollection("items");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Inventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item Quantity");
            System.out.println("3. Remove Item");
            System.out.println("4. View Inventory");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    InventoryManager.addItem(scanner, collection);
                    break;
                case 2:
                    InventoryManager.updateItemQuantity(scanner, collection);
                    break;
                case 3:
                    InventoryManager.removeItem(scanner, collection);
                    break;
                case 4:
                    InventoryManager.viewInventory(collection);
                    break;
                case 5:
                    mongoClient.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
