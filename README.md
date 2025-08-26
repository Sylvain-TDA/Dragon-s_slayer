# Dragon-s_slayer

Un jeu d’aventure en terminal inspiré de Donjons & Dragons, codé en Java dans le cadre d’un cours sur la Programation Orientée Objet (POO).

## Description

Dragon's Slayer est un jeu textuel où le joueur incarne un héros (Guerrier ou Magicien) évoluant sur un plateau de 64 cases.
Le but est de combattre des ennemis, trouver des trésors et survivre jusqu’à la fin de l’aventure.

## Objectif

- Appliquer les concepts de la POO (classes, héritage, encapsulation, etc.).
- Structurer le code en packages pour une meilleure organisation.
- Respecter les bonnes pratiques de développement (noms de variables, getters/setters, méthodes courtes, etc.).
- Minimiser le code dans la classe Main pour favoriser la modularité.


## Technologies Utilisées

- Langage : Java (version 8 ou supérieure)
- Paradigme : Programation Orientée Objet
- Outils : Terminal, IDE IntelliJ

## Structure du Projet

- dragon-slayer/
    - src/
        - fr/
            - dragonsslayer/
                - character/      # Gestion du héros principal
                - ennemy/         # Gestion des ennemis
                - equipment/      # Gestion de l'équipement
                - Game.java       # Logique concernant le jeu
                - Menu.java       # Affiche et gestion du menu
                - Main.java       # Point d'entrée minimaliste
    - README.md              # Documentation


## Comment jouer ?

Comment Jouer ?

1-Créer un personnage : Choisis entre un Guerrier (plus de points de vie) ou un Magicien (plus de magie).  
2-Explorer le plateau : 64 cases générées aléatoirement avec des ennemis et des trésors.  
3-Combattre : Utilise tes armes et tes compétences pour vaincre les ennemis.  
4-Trouver des trésors : Améliore ton équipement pour augmenter tes chances de survie.  
5-Gagner : Atteins la fin du plateau en survivant à tous les combats !

## Règles et Mécaniques

- Chaque case peut contenir un ennemi, un trésor ou être vide.
- Les combats se déroulent au tour par tour.
- Le héros et les ennemis ont des points de vie (HP) et des attaques spécifiques.
- Les trésors améliorent les statistiques du héros.



