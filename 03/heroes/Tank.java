/*
 * This file illustrates Tank.java from hw4.
 */
package hw4.heroes;

/**
 * Tank Class (subclass of Hero)
 * Ability to reduce damage taken from other classes
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class Tank extends Hero {

    /**
     * Creates a Tank
     *
     * @param name         the string name
     * @param hitPoints    the hit points of hero
     * @param attackDamage damage
     * @return Tank
     */
    public Tank(String name, int hitPoints, int attackDamage) {
        super(name, hitPoints, attackDamage);
    }

    /**
     * Heals the tank
     *
     * @param healAmount HP to be healed
     */
    public void heal(int healAmount) {
        super.heal(healAmount);
        if (hitPoints > 40) hitPoints = 40;
    }

    /**
     * Obtain role of the tank
     *
     * @return role of Tank
     */
    public Heroes.Role getRole() {
        return Heroes.Role.TANK;
    }

    /**
     * Applies the shield of the tank to damage applied
     *
     * @param damage damage to be taken
     */
    public void takeDamage(int damage) {
        super.takeDamage(damage * 9 / 10);
    }

    /**
     * Returns string of hp of hero
     *
     * @return string representation of health
     */
    public String getHP() {
        return (super.getHP() + hitPoints + "/40");
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
        sbr.append(Heroes.Role.TANK);
        sbr.append(", ");
        sbr.append(super.hitPoints);
        sbr.append("/40");
        return sbr.toString();
        //return (super.name + ", " + Heroes.Role.TANK + ", " + super.hitPoints + "/40");
    }
}
