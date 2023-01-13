/*
 * This file illustrates Hero.java from hw4.
 */
package hw4.heroes;

import hw4.game.HeroParty;
import hw4.game.Team;

import java.util.ArrayList;

/**
 * Superclass (abstract) of Hero containing common methods and data values
 * Subclasses are Berserker, Tank, nad Healer
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public abstract class Hero {
    protected String name;
    protected int hitPoints;
    protected int attackDamage;

    /**
     * creates a hero
     *
     * @param name the string name
     * @param hitPoints the hit points of hero
     * @param attackDamage damage
     * @return Hero newly created
     */

    protected Hero(String name, int hitPoints, int attackDamage) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.attackDamage = attackDamage;
    }

    /**
     * Create a specific type of hero.
     *
     * @param role role of player (tank, healer, beserker)
     * @param team lion or dragon team
     * @param party which party they belong to
     * @return Hero newly created hero
     */
    public static Hero createHero(Heroes.Role role, Team team, Party party) {
        if (role == Heroes.Role.BERSERKER) return new Berserker(Heroes.getName(team, role), 30, 20);
        else if (role == Heroes.Role.HEALER) return new Healer(Heroes.getName(team, role), 35, 10, party);
        else if (role == Heroes.Role.TANK) return new Tank(Heroes.getName(team, role), 40, 15);
        else return null;
    }

    /**
     * Returns string name of the hero
     *
     * @return string name returned
     */
    public String getName() {
        return name;
    }

    /**
     * Determines if hero is dead or not
     *
     * @return boolean value of alive or dead
     */
    public boolean hasFallen() {
        if (hitPoints == 0) return true;
        else return false;
    }

    /**
     * Heals the hero
     *
     * @param healAmount amount of HP healed
     */
    public void heal(int healAmount) {
        hitPoints += healAmount;
        System.out.println(name + " heals " + healAmount + " points");
    }

    /**
     * Returns the role of the player
     *
     * @return hero.role enumerated type of role
     */
    public Heroes.Role getRole() {
        if (name.equals("Trogdor") || name.equals("Simba")) return Heroes.Role.BERSERKER;
        else if (name.equals("Spyro") || name.equals("Elsa")) return Heroes.Role.HEALER;
        else if (name.equals("Smaug") || name.equals("Aslan")) return Heroes.Role.TANK;
        else return null;
    }

    /**
     * Method simulating attack and taking away HP
     *
     * @param enemy the new hero
     */
    public void attack(Hero enemy) {
        enemy.takeDamage(attackDamage);
    }

    /**
     * Method subtracting damage
     *
     * @param damage the new hero
     */
    public void takeDamage(int damage) {
        hitPoints -= damage;
        if (hitPoints < 0) hitPoints = 0;
        System.out.println(name + " takes " + damage + " damage");
    }

    /**
     * Returns the HP
     *
     * @return String the new hero
     */
    public String getHP() {
        return "";
    }

    /**
     * Heals all players on a team
     *
     * @param aa list of heroes to be healed
     */
    public void healTeammate(HeroParty aa) {
    }
}
