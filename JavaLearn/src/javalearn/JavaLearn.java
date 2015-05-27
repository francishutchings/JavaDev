/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javalearn;

import java.util.Random;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author frank.hutchings
 */
public class JavaLearn {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print(Arrays.toString(generateRandomNumbs()));
    }
    
    /**
     * 1 to 49
     */
    private static Integer[] generateRandomNumbs(){
        
        Integer intArray[] = new Integer[] { 0,0,0,0,0,0 };
        Integer tempInt = 0;
        Integer tempIntFind = 1;
        Random randomGenerator = new Random();

        tempIntFind = Arrays.asList(intArray).indexOf(0);

        while(tempIntFind > -1){
            
            tempInt = randomGenerator.nextInt(49);

            if(!numbExists(intArray, tempInt)){
                intArray[tempIntFind] = tempInt;
            }

            tempIntFind = Arrays.asList(intArray).indexOf(0);
        }
        
        Arrays.sort(intArray);

        return intArray;
    }
    
    private static boolean numbExists(Integer[] numbArray, Integer numbIn){
        boolean returnVal = false;
        for(int x=1; x<numbArray.length; ++x){
            if(Objects.equals(numbArray[x], numbIn)){
                returnVal = true;
                break;
            }
        }
        return returnVal;
    }
    
}
