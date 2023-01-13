/*
 * This file illustrates Heroes.java from hw4.
 */
package hw4.heroes;


import hw4.game.Team;
import java.io.Serializable;

/**
 * File storing the types and names of all the potential heroes
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class Heroes {

    /**
     * The three kinds of heroes
     */
    public enum Role implements Serializable, Comparable<Heroes.Role>, java.lang.constant.Constable {
        BERSERKER,
        HEALER,
        TANK;
    }

    /**
     * Dragon berserker name
     */
    public static final String DRAGON_BERSERKER = "Trogdor";

    /**
     * Dragon healer name
     */
    public static final String DRAGON_HEALER = "Spyro";

    /**
     * Dragon tank name
     */
    public static final String DRAGON_TANK = "Smaug";

    /**
     * Lion berserker name
     */
    public static final String LION_BERSERKER = "Simba";

    /**
     * Lion healer name
     */
    public static final String LION_HEALER = "Elsa";

    /**
     * Lion tank name
     */
    public static final String LION_TANK = "Aslan";


    public Heroes() {
    }

    /**
     * Get the name of the hero based on their role and team.
     *
     * @param team hero's team
     * @param role hero's role
     * @return name of hero
     */
    public static String getName(hw4.game.Team team, Role role) {
        String name = "";
        switch (role) {
            case BERSERKER:
                if (team == Team.DRAGON) name = DRAGON_BERSERKER;
                else name =  LION_BERSERKER;
                break;
            case HEALER:
                if (team == Team.DRAGON) name = DRAGON_HEALER;
                else name = LION_HEALER;
                break;
            case TANK:
                if (team == Team.DRAGON) name = DRAGON_TANK;
                else name = LION_TANK;
                break;
        }
        return name;
    }
}
