/*
 * file: TestParty.java
 */

package hw4.test;

import java.util.LinkedList;
import java.util.List;

import hw4.game.Team;
import hw4.heroes.Hero;
import hw4.heroes.Heroes;
import hw4.heroes.Heroes.Role;
import hw4.heroes.Party;

/**
 * This is a party used only for testing.  It is incomplete in that it only
 * creates the party of heroes (without shuffling) and it gives access to the
 * collection of members.
 *
 * @author RIT CS
 */
public class TestParty implements Party {
    private List<Hero> heroes;

    public TestParty(Team team) {
        this.heroes = new LinkedList<>();
        this.heroes.add(Hero.createHero(Role.BERSERKER, team, this));
        this.heroes.add(Hero.createHero(Role.HEALER, team, this));
        this.heroes.add(Hero.createHero(Role.TANK, team, this));
    }


    @Override
    public Hero removeHero() {
        // not implemented
        return null;
    }

    @Override
    public int numHeroes() {
        // not implemented
        return 0;
    }

    @Override
    public Team getTeam() {
        // not implemented
        return null;
    }

    @Override
    public void addHero(Hero hero) {

    }

    @Override
    public List<Hero> getHeroes() {
        return this.heroes;
    }
}
