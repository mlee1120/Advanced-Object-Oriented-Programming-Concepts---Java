/*
 * This file illustrates Berserker.java from hw4.
 */
package hw4.heroes;

/**
 * Berserker Class (subclass of Hero)
 * No unique ability given just more HP than other classes
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class Berserker extends Hero {

    /**
     * Creates a berserker
     *
     * @param name         the string name
     * @param hitPoints    the hit points of hero
     * @param attackDamage damage
     * @return Berserker hero
     */
    public Berserker(String name, int hitPoints, int attackDamage) {
        super(name, hitPoints, attackDamage);
    }

    /**
     * Heals a hero
     *
     * @param healAmount amount to be healed
     */
    public void heal(int healAmount) {
        super.heal(healAmount);
        if (hitPoints > 30) hitPoints = 30;
    }

    /**
     * Returns role of berserker
     *
     * @return Bererker role
     */
    public Heroes.Role getRole() {
        return Heroes.Role.BERSERKER;
    }

    /**
     * Returns string of hp of hero
     *
     * @return string representation of health
     */
    public String getHP() {
        return (super.getHP() + hitPoints + "/30");
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
        sbr.append(Heroes.Role.BERSERKER);
        sbr.append(", ");
        sbr.append(super.hitPoints);
        sbr.append("/30");
        return sbr.toString();
        //more efficient than this: return (super.name + ", " + Heroes.Role.BERSERKER + ", " + super.hitPoints + "/30");}
        //Because there will be less new Strings created.
    }
}
