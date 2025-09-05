package test;

import fr.dragonsslayer.Game.RandomNameGenerator;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class RandomNameGeneratorTest {

    @Test
    public void testGenerateWeaponRandomName_NotNull() {
        // Vérifie que le nom généré n'est pas null
        String weaponName = RandomNameGenerator.generateWeaponRandomName();
        assertNotNull(weaponName, "Le nom de l'arme ne doit pas être null.");
    }

    @Test
    public void testGenerateWeaponRandomName_NotEmpty() {
        // Vérifie que le nom généré n'est pas vide
        String weaponName = RandomNameGenerator.generateWeaponRandomName();
        assertFalse(weaponName.isEmpty(), "Le nom de l'arme ne doit pas être vide.");
    }

    @Test
    public void testGenerateWeaponRandomName_UniqueNames() {
        // Vérifie que les noms générés sont uniques (ou au moins différents)
        Set<String> weaponNames = new HashSet<>();
        int numberOfTests = 100; // Nombre de noms à générer pour le test

        for (int i = 0; i < numberOfTests; i++) {
            String weaponName = RandomNameGenerator.generateWeaponRandomName();
            weaponNames.add(weaponName);
        }

        // Si tous les noms sont identiques, la taille du Set sera 1
        assertTrue(weaponNames.size() > 1, "Les noms d'armes générés doivent être variés.");
    }

    @Test
    public void testGenerateWeaponRandomName_Format() {
        // Vérifie que le nom généré suit le format attendu (exemple : "Dusk des ombres maudites")
        String weaponName = RandomNameGenerator.generateWeaponRandomName();
        assertTrue(weaponName.matches("^[A-Z][a-zA-Z]+ [a-zA-Z0-9éèêëàâäôöùûüîïç' -]+$"),
                "Le nom de l'arme doit suivre le format 'Préfixe Suffix'.");
    }

    @Test
    public void testGenerateWeaponRandomName_Consistency() {
        // Vérifie que la méthode retourne toujours un nom (pas d'erreur aléatoire)
        for (int i = 0; i < 1000; i++) {
            String weaponName = RandomNameGenerator.generateWeaponRandomName();
            assertNotNull(weaponName, "Le nom de l'arme ne doit jamais être null.");
            assertFalse(weaponName.isEmpty(), "Le nom de l'arme ne doit jamais être vide.");
        }
    }
}
