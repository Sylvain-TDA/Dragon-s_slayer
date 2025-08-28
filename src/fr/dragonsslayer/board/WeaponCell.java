package fr.dragonsslayer.board;

import fr.dragonsslayer.equipment.Potion;
import fr.dragonsslayer.equipment.Weapon;

public class WeaponCell extends Cell{
    public WeaponCell(Weapon weapon) {
        super(weapon);
    }

    @Override
    public void interact() {
        System.out.println("Vous avez trouvé une arme : " + ((Weapon) getContent()));
    }
}
