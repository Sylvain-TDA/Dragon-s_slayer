package test;


import fr.dragonsslayer.board.dice.TwentyFacesDice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class TwentyFacesDiceTest {

    @Test
    public void testRollDistribution() {
        TwentyFacesDice dice = new TwentyFacesDice();
        Map<Integer, Integer> resultCounts = new HashMap<>();

        // Initialiser le compteur pour chaque face possible (1 à 20)
        for (int i = 1; i <= 20; i++) {
            resultCounts.put(i, 0);
        }

        // Simuler 1000 lancers
        for (int i = 0; i < 1000; i++) {
            int result = dice.roll();
            resultCounts.put(result, resultCounts.get(result) + 1);
        }

        // Afficher la répartition
        System.out.println("Répartition des résultats après 1000 lancers :");
        for (Map.Entry<Integer, Integer> entry : resultCounts.entrySet()) {
            System.out.println("Face " + entry.getKey() + " : " + entry.getValue() + " fois");
        }

        // Vérifier que toutes les faces ont été tirées au moins une fois
        for (int count : resultCounts.values()) {
            Assertions.assertTrue(count > 0, "Toutes les faces doivent être tirées au moins une fois sur 1000 lancers");
        }
    }
}
