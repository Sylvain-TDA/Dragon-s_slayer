package fr.dragonsslayer.Game;

import java.util.Random;

public class RandomNameGenerator {
    private static final String[] ENEMYBEGINNINGS = {"Zor", "Gor", "Mal", "Thal", "Kor", "Bra", "Drak", "Grim", "Vex", "Morg"};
    private static final String[] ENEMYMIDDLES = {"ath", "oth", "um", "ar", "ak", "ix", "or", "an", "ur", "ok"};
    private static final String[] ENEMYENDS = {"oth", "ar", "ak", "ix", "or", "an", "ur", "ok", "ash", "oth"};
    private static final String[] WEAPONBEGINNINGS = {
            "Zar", "Vorth", "Dusk", "Grim", "Morg", "Korv", "Neth", "Xal", "Vex", "Thal",
            "Bael", "Kor", "Drav", "Frost", "Shadow", "Blood", "Bone", "Ash", "Doom", "Wraith",
            "Gloom", "Hate", "Rune", "Skull", "Viper", "Omen", "Cursed", "Abyssal", "Void", "Phantom"
    };
    private static final String[] WEAPONENDS = {
            " des ombres maudites",
            " de la lune sanglante",
            " du crépuscule éternel",
            " des âmes perdues",
            " de la nuit sans fin",
            " des pleurs des damnés",
            " du cœur des ténèbres",
            " de la rage des démons",
            " des murmures de l'abîme",
            " de la malédiction ancienne",
            " des flammes oubliées",
            " du sang des anciens",
            " des cris des torturés",
            " de la peur incarnée",
            " des cœurs brisés",
            " de la folie des dieux",
            " des larmes des anges déchus",
            " de la haine pure",
            " des secrets interdits",
            " de la mort elle-même",
            " des cauchemars éveillés",
            " de la trahison éternelle",
            " des ombres voraces",
            " de la douleur infinie",
            " des sorts brisés",
            " de la vengeance implacable",
            " des malédictions oubliées",
            " de la folie des sorciers",
            " des cris des morts-vivants",
            " de la terreur nocturne"
    };

    private static final Random RANDOM = new Random();

    public static String generateEnemyRandomName() {

        String beginning = ENEMYBEGINNINGS[RANDOM.nextInt(ENEMYBEGINNINGS.length)];
        String middle = ENEMYMIDDLES[RANDOM.nextInt(ENEMYMIDDLES.length)];
        String end = ENEMYENDS[RANDOM.nextInt(ENEMYENDS.length)];
        return beginning + middle + end;
    }

    public static String generateWeaponRandomName() {

        String beginning = WEAPONBEGINNINGS[RANDOM.nextInt(WEAPONBEGINNINGS.length)];
        String end = WEAPONENDS[RANDOM.nextInt(WEAPONENDS.length)];
        return beginning  + end;
    }
}