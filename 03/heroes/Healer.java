/*
 * This file illustrates Healer.java from hw4.
 */
package hw4.heroes;

import hw4.game.HeroParty;

import java.util.ArrayList;
import java.util.List;

/**
 * Healer Class (subclass of Hero)
 * Ability to heal other heroes on their team
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class Healer extends Hero {

    private int healAmount = 10;
    private Party party;

    /**
     * Creates a Healer
     *
     * @param name         the string name
     * @param hitPoints    the hit points of hero
     * @param attackDamage damage
     * @return Healer
     */
    public Healer(String name, int hitPoints, int attackDamage, Party party) {
        super(name, hitPoints, attackDamage);
        this.party = party;
    }

    /**
     * Heals the healer
     *
     * @param healAmount HP to be healed
     */
    public void heal(int healAmount) {
        super.heal(healAmount);
        if (hitPoints > 35) hitPoints = 35;
    }

    /**
     * Obtain role of the healer
     *
     * @return role of healer
     */
    public Heroes.Role getRole() {
        return Heroes.Role.HEALER;
    }

    @Override
    /**
     * Method simulating attack and taking away HP
     * For healers, heal teammates and his/herself before attacking
     *
     * @param enemy the new hero
     */
    public void attack(Hero enemy) {
        this.healTeammate(party.getHeroes());
        enemy.takeDamage(attackDamage);
    }

    /**
     * Applies healing to the entire team
     *
     * @param hero_list List of the heroes alive
     */
    public void healTeammate(List<Hero> hero_list) {
        this.heal(healAmount);
        for (int i = 0; i < hero_list.size(); i++) {
            hero_list.get(i).heal(healAmount);
        }
    }

    /**
     * Returns string of hp of hero
     *
     * @return string representation of health
     */
    public String getHP() {
        return (super.getHP() + hitPoints + "/35");
    }

    /**
     * Returns a string of name, role, and hitpoints left
     *
     * @return string representation of hero status
     */
    public String toString() {
        StringBuilder sbr = new StringBuilder();
        sbr.append(super.name);
        sbr.append(", ");
        sbr.append(Heroes.Role.HEALER);
        sbr.append(", ");
        sbr.append(super.hitPoints);
        sbr.append("/35");
        return sbr.toString();
        //return super.name + ", " + Heroes.Role.HEALER + ", " + super.hitPoints + "/35";
    }
}
