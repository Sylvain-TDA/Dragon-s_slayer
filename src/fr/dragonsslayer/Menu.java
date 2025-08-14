package fr.dragonsslayer;
import fr.dragonsslayer.characters.Hero;

import java.util.Scanner;

public class Menu {
    private Scanner keyboard = new Scanner(System.in);

    private String name;
    private String type;

    public Menu() {
        System.out.println("Bienvenue dans Dragon's Slayer !");
        System.out.println("Vous sentez-vous prêt à combattre des ordes d'ennemis et de dragons ?");
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName() {
        this.name = requestInput(keyboard, "Avant de vous jetter corps et âmes dans une aventure fantastique, veuillez entrer votre nom");
    }

    public void setType() {
        this.type = requestInput(keyboard, "Quel type de combattant êtes-vous ? Plutôt Warrior ou plutôt Magician ?");
    }

    public String askType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le type (Warrior ou Magician) : ");
        return scanner.nextLine();
    }

    public String askName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom : ");
        return scanner.nextLine();
    }

    public int printMenu() {
        System.out.println("                          ");
        System.out.println("===== Menu Principal =====");
        System.out.println("                          ");
        System.out.println("1. Nouveau personnage");
        System.out.println("2. Jouer");
        System.out.println("3. Quitter");
        System.out.print("Votre choix : ");
        return keyboard.nextInt();
    }

    public int printCharacterMenu() {
        System.out.println("===== Menu Personnage =====");
        System.out.println("1. Afficher les infos");
        System.out.println("2. Modifier le personnage");
        System.out.println("3. Retour au menu principal");
        System.out.print("Votre choix : ");
        return keyboard.nextInt();
    }

    public Hero createHero() {
        return new Hero(askType(), askName());
    }

    public String requestInput(Scanner keyboard, String message) {
        System.out.println(message);
        return keyboard.nextLine();
    }

    public void manageHero(Hero hero) {
        boolean back = false;
        while (!back) {
            switch (printCharacterMenu()) {
                case 1:
                    System.out.println(hero);
                    break;
                case 2:
                    hero.modify(askType(), askName());
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
    }
}

