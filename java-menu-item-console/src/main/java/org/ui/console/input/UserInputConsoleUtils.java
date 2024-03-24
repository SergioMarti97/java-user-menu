package org.ui.console.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Esta clase contiene funciones útiles para
 * la interacción usuario - consola
 */
public class UserInputConsoleUtils {

    public static Scanner scan = new Scanner(System.in);

    public static Integer castInt(String input) {
        Integer inputInt = null;
        try {
            inputInt = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("La entrada no es un número, por favor, introduzca un número entero.");
        }
        return inputInt;
    }

    /**
     * Esta función adquiere un integer del usuario
     * Si la entrada no es correcta, vuelve a preguntar al usuario
     * Se gestiona mediante un bloque try - catch a la hora de parsear a un número entero la entrada del usuario.
     * @return un número integer del usuario
     */
    public static int getUserInputInt() {
        String input;
        Integer inputInt;
        do {
            input = scan.nextLine();
            inputInt = castInt(input);
        } while (inputInt == null);
        return inputInt;
    }

    /**
     * Esta función adquiere un integer del usuario
     * Si la entrada no es correcta, vuelve a preguntar al usuario
     * Es necesario que el número entero introducido se encuentre entre los límites ínidicados por parámetro
     * @param min el mínimo por arriba, incluido
     * @param max el máximo por arriba, sin incluir
     * @return uu número integer del usuario, que se encuentra entre los límites específicados por parámetro
     */
    public static int getUserInputIntBetweenBounds(int min, int max) {
        int inputInt;
        boolean condition;
        do {
            inputInt = getUserInputInt();
            condition = inputInt >= min && inputInt <= max;
            if (!condition) {
                System.out.printf("El número introducido debe de estar entre %d y %d.\n", min, max);
            }
        } while (!condition);
        return inputInt;
    }

    public static List<Integer> getUserInputIntListBetweenBounds(int min, int max, String delimiter) {
        List<Integer> list = new ArrayList<>();
        boolean condition = false;
        String input;
        do {
            input = scan.nextLine();
            for (String s : input.split(delimiter)) {
                Integer i = castInt(s);
                if (i == null) {
                    condition = false;
                    break;
                }
                condition = i > min && i <= max;
                if (!condition) {
                    System.out.printf("El número introducido debe de estar entre %d y %d.\n", min, max);
                    break;
                }
                list.add(i);
            }
        } while (!condition);
        return list;
    }

}

