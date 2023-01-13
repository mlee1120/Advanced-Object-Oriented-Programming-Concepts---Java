/*
 * This file illustrates HeroStrom.java from hw4.
 */
package hw4.game;

import hw4.heroes.Hero;

/**
 * the next big fantasy role playing game (RPG), Super Fantasy Hero Storm.
 * he game deals with the ongoing skirmish in the realm of The Feral Vales, between two ancient and opposing teams:
 * Dragons and Lions
 * Each team is a party of three members, the heroes, each with their own unique characteristics
 * and abilities: Berserker, Healer, and Tank.
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class HeroStorm {

    private HeroParty dragon;
    private HeroParty lion;
    private int roundCounter;

    /**
     * Create the parties and initialize the round counter.
     *
     * @param dragonSeed the seed for the dragon random number generator
     * @param lionSeed   the seed for the lion random number generator
     */
    public HeroStorm(int dragonSeed, int lionSeed) {
        dragon = new HeroParty(Team.DRAGON, dragonSeed);
        lion = new HeroParty(Team.LION, lionSeed);
        roundCounter = 1;
    }

    /**
     * The game is played in battle rounds. A round is one attack between the "front" heroes of the two teams who are
     * temporarily removed from the party. The first hero to attack alternates by round, starting with Team Dragon.
     * If the hero who is attacked is not defeated, they can attack the first hero back. Afterwards each non-defeated
     * hero is added to the back of their party. Defeated heroes merely "disappear" with a farewell message about
     * having fallen. The rounds continue until one of the teams has all of their members defeated. The other team
     * is declared the winner. There is no interaction by the user in this game. Refer to the sample outputs for
     * details on the output formatting.
     */
    public void play() {
        Hero fighter1, fighter2;
        boolean fight = true;
        do {
            System.out.println("Battle #" + roundCounter);
            System.out.println("==========");
            System.out.println("DRAGON:");
            System.out.println(dragon);
            System.out.println("LION:");
            System.out.println(lion);
            if (roundCounter % 2 == 1) {
                fighter1 = dragon.removeHero();
                fighter2 = lion.removeHero();
            } else {
                fighter1 = lion.removeHero();
                fighter2 = dragon.removeHero();
            }
            System.out.println("*** " + fighter1.getName() + " vs " + fighter2.getName() + "\n");
            fighter1.attack(fighter2);
            if (fighter2.hasFallen()) System.out.println(fighter2.getName() + " has fallen!");
            else fighter2.attack(fighter1);
            if (fighter1.hasFallen()) System.out.println(fighter1.getName() + " has fallen!");
            if (!fighter1.hasFallen()) {
                if (roundCounter % 2 == 1) dragon.getHeroes().add(fighter1);
                else lion.getHeroes().add(fighter1);
            }
            if (!fighter2.hasFallen()) {
                if (roundCounter % 2 == 1) lion.getHeroes().add(fighter2);
                else dragon.getHeroes().add(fighter2);
            }
            if (dragon.numHeroes() == 0 || lion.numHeroes() == 0) fight = false;
            System.out.println();
            roundCounter += 1;
        } while (fight);
        if (dragon.numHeroes() == 0) System.out.println("Team Lion wins!");
        else System.out.println("Team Dragon wins!");
    }

    /**
     * The main method. It checks the number of command line arguments, then creates and plays the game.
     *
     * @param args the command line arguments, two integers for the dragon and lion random number generator seeds
     */
    public static void main(java.lang.String[] args) {
        HeroStorm hw4 = new HeroStorm(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        hw4.play();

    }
}
