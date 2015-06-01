
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lottopicker;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class lottoPicker {

    public static void main(String[] args) {
        Boolean runThis = Boolean.valueOf(true);
        while (runThis.booleanValue()) {
            Integer menu_choice = getMenuItem();
            switch (menu_choice.intValue()) {
                case 0:
                    runThis = Boolean.valueOf(false);
                    break;
                case 1:
                    System.out.println(Arrays.toString(generateRandomNumbs()));
                    break;
                case 2:
                    System.out.println("Choice 2!");
                    break;
                case 3:
                    System.out.println("Choice 3!");
                    break;
                default:
                    System.out.println("What??!!");
            }
            System.out.println("===============================\n");
        }
    }

    private static Integer[] generateRandomNumbs() {
        Integer[] intArray = {Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)};
        Integer tempInt = Integer.valueOf(0);
        Integer tempIntFind = Integer.valueOf(1);
        Random randomGenerator = new Random();

        tempIntFind = Integer.valueOf(Arrays.asList(intArray).indexOf(Integer.valueOf(0)));
        while (tempIntFind.intValue() > -1) {
            tempInt = Integer.valueOf(randomGenerator.nextInt(49));
            if (!numbExists(intArray, tempInt)) {
                intArray[tempIntFind.intValue()] = tempInt;
            }
            Arrays.sort(intArray);
            tempIntFind = Integer.valueOf(Arrays.asList(intArray).indexOf(Integer.valueOf(0)));
        }
        return intArray;
    }

    private static boolean numbExists(Integer[] numbArray, Integer numbIn) {
        boolean returnVal = false;
        for (int x = 1; x < numbArray.length; x++) {
            if (Objects.equals(numbArray[x], numbIn)) {
                returnVal = true;
                break;
            }
        }
        return returnVal;
    }

    private static Integer getMenuItem() {
        Scanner user_input = new Scanner(System.in);
        Integer userChoice = Integer.valueOf(0);
        int loopIdx = 1;
        List<String> results = new ArrayList();
        ConnectMSSQLServer myDbTest = new ConnectMSSQLServer();
        myDbTest.displayDbProperties();
        try {
            ResultSet rs = myDbTest.viewTable("SELECT [menu_item] FROM [dbo].[menu_list]");
            while (rs.next()) {
                results.add(rs.getString(1));
            }
            myDbTest.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Please make a selection from the menu - \n-------------------------------------\n");
        System.out.println("    0 Exit prog");
        for (int x = 0; x < results.size(); x++) {
            System.out.println("    " + loopIdx + ": " + (String) results.get(x));
            loopIdx++;
        }
        System.out.println("-------------------------------------");
        try {
            userChoice = Integer.valueOf(Integer.parseInt(user_input.next()));
        } catch (NumberFormatException e) {
            userChoice = Integer.valueOf(-1);
        }
        return userChoice;
    }
}
