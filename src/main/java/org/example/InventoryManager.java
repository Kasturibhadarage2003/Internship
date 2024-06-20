import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Scanner;

public class InventoryManager {

    public static void addItem(Scanner scanner, MongoCollection<Document> collection) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Document item = new Document("name", name)
                .append("quantity", quantity);
        collection.insertOne(item);
        System.out.println("Item added successfully.");
    }

    public static void updateItemQuantity(Scanner scanner, MongoCollection<Document> collection) {
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter new quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Document query = new Document("_id", new ObjectId(id));
        Document update = new Document("$set", new Document("quantity", quantity));
        collection.updateOne(query, update);
        System.out.println("Item quantity updated successfully.");
    }

    public static void removeItem(Scanner scanner, MongoCollection<Document> collection) {
        System.out.print("Enter item ID: ");
        String id = scanner.nextLine();

        Document query = new Document("_id", new ObjectId(id));
        collection.deleteOne(query);
        System.out.println("Item removed successfully.");
    }

    public static void viewInventory(MongoCollection<Document> collection) {
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }
    }
}
