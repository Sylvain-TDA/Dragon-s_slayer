package fr.dragonsslayer.board;

import fr.dragonsslayer.Game;
import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.equipment.OffensiveEquipment;
import fr.dragonsslayer.equipment.Spell;
import fr.dragonsslayer.equipment.Weapon;

import java.util.Objects;

public class WeaponCell extends Cell {
    public WeaponCell(OffensiveEquipment equipment) {
        super(equipment);
    }

    @Override
    public void interact(Hero hero, Game game) {
        OffensiveEquipment equipment = (OffensiveEquipment) getContent();

        System.out.println("Vous avez trouvé une arme : " + getContent());
        if (equipment instanceof Spell && Objects.equals(hero.getType(), "Warrior")) {
            System.out.println("""
                    Un guerrier ne peut pas lancer de sort
                    """);
        } else if (equipment instanceof Weapon && Objects.equals(hero.getType(), "Warrior")) {
            Weapon weapon = (Weapon) getContent();
            System.out.println("""
                    Cette\s"""
                    + equipment.getName() + """
                     sied bien à votre main
                    
                    """);
            int newStrength = weapon.setAttackLevel(hero, equipment.getAttackLevel());
            hero.setAttackLevel(newStrength);

            System.out.println("""
                    Votre attaque est maintenant à :\s""" + newStrength + """
                    
                    """);

        } else if (equipment instanceof Weapon && Objects.equals(hero.getType(), "Magician")) {
            System.out.println("""
                     Qu'est-ce qu'un magicien pourrait bien faire de\s
                    \s""" + equipment.getName());
        } else if (equipment instanceof Spell && Objects.equals(hero.getType(), "Magician")) {
            Spell spell = (Spell) equipment;
            System.out.println(""" 
                    Apprendre le sort 
                    """ + equipment.getName() + " a été d'une grande facilité. Vous voilà plus fort");
            int newStrength = spell.setAttackLevel(hero, equipment.getAttackLevel());
            hero.setAttackLevel(newStrength);
            System.out.println("""
                    Votre attaque est maintenant à :\s""" + newStrength + """
                    
                    """);
        }
    }

    @Override
    public String getType() {
        return "Weapon";
    }
}
