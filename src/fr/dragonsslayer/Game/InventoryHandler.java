package fr.dragonsslayer.Game;

import fr.dragonsslayer.board.Inventory;
import fr.dragonsslayer.board.InventoryField;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class InventoryHandler {
    public static ArrayList<Object> inventory;

    public InventoryHandler() {
        inventory = new ArrayList<>();
    }

    public static void showInventory() {
        String inventoryBorder = "+-------------------------------+";
        System.out.println(inventoryBorder);
        System.out.println("|        LISTE DES ARMES         |");
        System.out.println(inventoryBorder);

        for (int i = 0; i < inventory.size(); i++) {
            String itemLine = String.format("| %2d. %-27s |", i + 1, inventory.get(i).toString());
            System.out.println(itemLine);
        }

        System.out.println(inventoryBorder);
    }

    public static void showItemInInventory(int choice) {
        Object item = inventory.get(choice);
        System.out.println(item);
    }

    public static int inventorySize() {
        System.out.println(inventory.size() + " armes" + """
                
                """);
        return  inventory.size();
    }

    public static Object getItemFromInventory(int choice) {
        if (choice >= 0 && choice < inventory.size()) {
            return inventory.get(choice);
        } else {
            return "Choix invalide !";
        }
    }

}
