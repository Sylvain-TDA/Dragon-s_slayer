package fr.dragonsslayer.Game;

import fr.dragonsslayer.Hero.Hero;
import fr.dragonsslayer.db.DataBaseHandling;

import java.util.ArrayList;

public class PrayerHandler {
    private final DataBaseHandling db;
    private boolean hadPray;

    public PrayerHandler(DataBaseHandling db) {
        this.db = db;
        this.hadPray = false;
    }

    public void prayForRespect(Hero hero) {
        ArrayList<String> dbHeroes = db.getHeroes();
        System.out.println("Vous avez prié : " + dbHeroes);
        if (!hadPray) {
            db.changeLifePoints(hero);
            System.out.println("Vous vous sentez plus revigoré.");
        } else {
            System.out.println("Les morts n'apprécient pas la triche.");
        }
        hadPray = true;
    }
}
