package fr.dragonsslayer.board;

import fr.dragonsslayer.characters.Hero;
import fr.dragonsslayer.equipment.OffensiveEquipment;
import fr.dragonsslayer.equipment.Potion;
import fr.dragonsslayer.equipment.Spell;
import fr.dragonsslayer.equipment.Weapon;

public class WeaponCell extends Cell{
    public WeaponCell(OffensiveEquipment equipment) {
        super(equipment);
    }

    @Override
    public void interact(Hero hero) {
        OffensiveEquipment equipment = (OffensiveEquipment) getContent();
        System.out.println("Vous avez trouvé une arme : " + (OffensiveEquipment) getContent());
        if (equipment instanceof Spell && hero.getType()=="Warrior") {
            System.out.println("Un guerrier ne peut pas lancer de sort");
        } else if (equipment instanceof Weapon && hero.getType()=="Warrior") {
            System.out.println("Cette" + equipment.getName() + " sied bien à votre main");
        } else if (equipment instanceof Weapon && hero.getType()=="Magician") {
            System.out.println("Qu'est-ce qu'un magicien pourrait bien faire de " + equipment.getName());
        } else if (equipment instanceof Spell && hero.getType()=="Magician") {
            System.out.println("Apprendre le sort " + equipment.getName() + " a été d'une grande facilité. Vous voilà plus fort");
        }
    }

    @Override
    public String getType() {
        return "Weapon";
    }
}
