/*
 * This file illustrates HeroParty.java from hw4.
 */
package hw4.game;

import hw4.heroes.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Creates the main interaction with the other files, Hero parties, the play method, and the main method
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class HeroParty implements Party {

    /**
     * Create the party. Here we associate the team with the party. We then add the heroes in the following order:
     * Berserker, Healer and Tank. The collection is then shuffled using the random number generator seed value.
     * To shuffle the collection of heroes (assumed to be either an ArrayList or LinkedList):
     * <p>
     * Collections.shuffle(this.heroes, new Random(seed));
     *
     * @param team the team
     * @param seed the random number generator seed
     */
    private List<Hero> party = new ArrayList<>();
    private Team team;

    public HeroParty(Team team, int seed) {
        this.team = team;
        this.addHero(Hero.createHero(Heroes.Role.BERSERKER, team, this));
        this.addHero(Hero.createHero(Heroes.Role.HEALER, team, this));
        this.addHero(Hero.createHero(Heroes.Role.TANK, team, this));
        Collections.shuffle(party, new Random(seed));
    }

    /**
     * Add a hero to the back of the collection.
     *
     * @param hero the new hero
     */
    public void addHero(Hero hero) {
        party.add(hero);
    }

    /**
     * Get all the undefeated heroes in the party.
     *
     * @return the party
     */
    public List<Hero> getHeroes() {
        return party;
    }

    /**
     * The team which this party is affiliated with.
     *
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Get the number of non-fallen heroes.
     *
     * @return number of heroes in the party
     */
    public int numHeroes() {
        return party.size();
    }

    /**
     * Remove the hero at the front of the collection.
     *
     * @return the hero at the front
     */
    public Hero removeHero() {
        return party.remove(0);
    }

    /**
     * Returns a string representation of the party.
     * <p>
     * {Dragons|Lions}:
     * Hero1, Role, currentHP/maxHP
     * ...
     * <p>
     * To get the hero string details you should call Hero::toString.
     *
     * @return the string
     * @override toString in class java.lang.Object
     */
    public java.lang.String toString() {
        String aString = "";
        for (int i = 0; i < party.size(); i++) {
            aString += party.get(i).getName() + ", " + party.get(i).getRole() + ", " + party.get(i).getHP() + "\n";
        }
        return aString;
    }
}
