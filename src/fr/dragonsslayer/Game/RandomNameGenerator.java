package fr.dragonsslayer.Game;

import java.util.Random;

public class RandomNameGenerator {
    private static final String[] ENEMYBEGINNINGS = {"Zor", "Gor", "Mal", "Thal", "Kor", "Bra", "Drak", "Grim", "Vex", "Morg"};
    private static final String[] ENEMYMIDDLES = {"ath", "oth", "um", "ar", "ak", "ix", "or", "an", "ur", "ok"};
    private static final String[] ENEMYENDS = {"oth", "ar", "ak", "ix", "or", "an", "ur", "ok", "ash", "oth"};
    private static final String[] WEAPONBEGINNINGS = {"Dar", "Ful", "Elc", "Sul", "Fal", "Gda", "Klu"};
    private static final String[] WEAPONENDS = {"des flammes de l'enfer", "source du massacre", "qui défait les goblins", "venant de l'amère agonie", "avortée de l'abysse"};

    public static String generateEnemyRandomName() {
        Random random = new Random();
        String beginning = ENEMYBEGINNINGS[random.nextInt(ENEMYBEGINNINGS.length)];
        String middle = ENEMYMIDDLES[random.nextInt(ENEMYMIDDLES.length)];
        String end = ENEMYENDS[random.nextInt(ENEMYENDS.length)];
        return beginning + middle + end;
    }

    public static String generateWeaponRandomName() {
        Random random = new Random();
        String beginning = WEAPONBEGINNINGS[random.nextInt(WEAPONBEGINNINGS.length)];
        String end = WEAPONENDS[random.nextInt(WEAPONENDS.length)];
        return beginning  + end;
    }
}
