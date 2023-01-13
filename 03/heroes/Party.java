/*
 * This file illustrates Party.java from hw4.
 */
package hw4.heroes;

import java.util.List;
import hw4.game.Team;

/**
 * The interface of party
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public interface Party {
    /**
     * Add a hero to the back of the collection.
     *
     * @param hero the new hero
     */
    void addHero(Hero hero);

    /**
     * Get all the undefeated heroes in the party.
     *
     * @return the party
     */
    List<Hero> getHeroes();

    /**
     * The team which this party is affiliated with.
     *
     * @return the team
     */
    Team getTeam();

    /**
     * Get the number of non-fallen heroes.
     *
     * @return number of heroes in the party
     */
    int numHeroes();

    /**
     * Remove the hero at the front of the collection.
     *
     * @return the hero at the front
     */
    Hero removeHero();
}
