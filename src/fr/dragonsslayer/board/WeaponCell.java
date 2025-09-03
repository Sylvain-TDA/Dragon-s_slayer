package fr.dragonsslayer.board;

import fr.dragonsslayer.equipment.OffensiveEquipment;
import fr.dragonsslayer.equipment.Potion;
import fr.dragonsslayer.equipment.Weapon;

public class WeaponCell extends Cell{
    public WeaponCell(OffensiveEquipment equipment) {
        super(equipment);
    }

    @Override
    public void interact() {
        System.out.println("Vous avez trouvé une arme : " + (OffensiveEquipment) getContent());
    }

    @Override
    public String getType() {
        return "Weapon";
    }
}
