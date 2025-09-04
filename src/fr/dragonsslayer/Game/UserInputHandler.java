package fr.dragonsslayer.Game;

import java.util.Scanner;

public class UserInputHandler {
    private final Scanner keyboard;

    public UserInputHandler() {
        this.keyboard = new Scanner(System.in);
    }

    public String getUserInput(String prompt) {
        System.out.println(prompt);
        return keyboard.nextLine().trim();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
