//this is here as it might be easier yo work from this instead of having unecisary code within out current main
//If you are okay to work with this please adjust to the current needs
package solver;   
import java.io.*;
import java.util.*;
import java.nio.file.*;
   
class program {

    public static void main(String[] args) {
        // this is for demonstrative purposes change X to to apropraite value and args requirments
        String textFile = "test_HornKB.txt";
        reader Data = new reader();
        Data.read(textFile);
        System.out.println(Data._horny);
        System.out.println(Data._query);
        System.out.println(Data._symbols);
    }
}