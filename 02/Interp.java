/*
 * This file illustrates Interp.java from hw3.
 */
package hw3;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Creates the user interface of Arithmetic Interpreter v 1.0
 * Receives the user's input (mathematical expression) in prefix form
 * converts the expression into a parse binary tree, evaluates the expression, and
 * displays the infix form of the expression (referred as emitting)
 *
 * @author Krystian Alexander Derhak, kad4374@rit.edu
 * @author Michael Lee, ml3406@rit.edu
 */

public class Interp {
    /**
     * Creates the user interface of Arithmetic Interpreter v 1.0, receives the user's input
     * (mathematical expression) in prefix form, stores those expressions in a list separated
     * by white-spaces, and calls the helper function.
     *
     * @param userIn        determines to stay in or quit the program according to user's
     * @param expFinal      declares the interface Expression
     * @param exp           String array that stores the user's input separated by white-spaces
     * @param oldExpression Array.asList that stores the data from exp with fixed size
     * @param express       ArrayList that stores the data from oldExpression (remove() method applicable)
     */
    public void userMenu() {
        boolean userIn = true;
        hw3.Expression expFinal;
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to your Arithmetic Interpreter v 1.0 :)");
        while (userIn) {
            System.out.print("> ");
            String[] exp = in.nextLine().split("\\s");
            List<String> oldExpress = Arrays.asList(exp);
            List<String> express = new ArrayList<>(oldExpress);
            if (express.get(0).equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                userIn = false;
            } else {
                expFinal = helpExpress(express);
                System.out.println("Emit: " + expFinal.emit());
                System.out.println("Evaluate: " + expFinal.evaluate());
            }
        }
        in.close();
    }

    /**
     * Helper function that converts the expression into a parse binary tree via Classes IntExp, AddExp, SubExp,
     * MulExp, DivExp, and ModExp, and returns Expression that helps evaluating the expression and displaying
     * the infix form of the expression (referred as emitting)
     *
     * @param tokens        reference of the ArrayList in userMenu()
     * @param expFinal      declares the interface Expression
     * @param exp           String array that stores the user's input separated by white-spaces
     * @param oldExpression Array.asList that stores the data from exp with fixed size
     * @param express       ArrayList that stores the data from oldExpression (remove() method applicable)
     * @return tokenExp (an Expression)
     */
    public Expression helpExpress(List<String> tokens) {
        hw3.Expression tokenExp = null;
        switch (tokens.get(0)) {
            case "+":
                tokens.remove(0);
                tokenExp = new AddExp(helpExpress(tokens), helpExpress(tokens));
                break;
            case "-":
                tokens.remove(0);
                tokenExp = new SubExp(helpExpress(tokens), helpExpress(tokens));
                break;
            case "*":
                tokens.remove(0);
                tokenExp = new MulExp(helpExpress(tokens), helpExpress(tokens));
                break;
            case "/":
                tokens.remove(0);
                tokenExp = new DivExp(helpExpress(tokens), helpExpress(tokens));
                break;
            case "%":
                tokens.remove(0);
                tokenExp = new ModExp(helpExpress(tokens), helpExpress(tokens));
                break;
            default:
                tokenExp = new IntExp(Integer.parseInt(tokens.get(0)));
                tokens.remove(0);
                break;
        }
        return (tokenExp);
    }

    /**
     * Main method.
     *
     * @param args command line arguments -- unused
     */
    public static void main(String[] args) {
        Interp hw3 = new Interp();
        hw3.userMenu();
    }


}
