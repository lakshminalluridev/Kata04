//package com.codekata.Kata04;
//import pacakages that are needed to read from and write to a file, ArrayList
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class LakshmiSai {
    public static void main(String[] args) {
        List<List<String>> dataFile = readFile("C:/CodeKata/weather.dat");
        //System.out.println("reading weather file");
        System.out.println("Day of the smallest spread: " + smallestSpread(dataFile, "weather.dat"));
        dataFile = readFile("C:/CodeKata/football.dat");
        //System.out.println("reading football file");
        System.out.println("Team has smallest spread for and against goals: " + smallestSpread(dataFile, "football.dat"));
   }

    public static String smallestSpread(List<List<String>> dataFile, String file){
        int smallestValue = 0, spread = 0, indexHigh = 0, indexLow = 0, indexReturn = 0;
        String returnValue = "";

        //want to try something different without hardcoding file names here.
        if(file.equals("weather.dat")){
            indexHigh = 1;
            indexLow = 2;
            indexReturn = 0;
          }
        else if(file.equals("football.dat")){
            indexHigh = 6;
            indexLow = 7;
            indexReturn = 1;
        }
        else{
            return "System didn't recognize the file";
        }

        for(int i = 1; i < dataFile.size(); i++){

            //not continue to calculate difference for an empty list
            if(dataFile.get(i).size() == 0){
                continue;
            }
            try {
                //parse the string in dataFile at row i at specified index 1 and 2 or 6 and 7.
                //sanitize numbers before calculating difference to handle any special characters in temperature
                spread = Math.abs(Integer.parseInt(dataFile.get(i).get(indexHigh).replaceAll("[^0-9]","").trim()) - Integer.parseInt(dataFile.get(i).get(indexLow).replaceAll("[^0-9]","").trim()));
            }catch (Exception e){
                e.printStackTrace();
            }
            //set smallest spread and return value
            if(smallestValue > spread || smallestValue == 0){
                smallestValue = spread;
                returnValue = dataFile.get(i).get(indexReturn);
            }
        }
        return returnValue;
    }

    //create method to read files by passing file name as a parameter
    public static List<List<String>> readFile(String inputFile) {
        BufferedReader br = null;
        List<List<String>> dataFile = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(inputFile));
            String line = br.readLine();
            List<String> columnsList = new ArrayList<>();

            while ((line != null)) {
                if (!line.isEmpty() && !line.equals("")) {
                    line.trim();
                    String[] columns = line.split(" ");
                    //System.out.println(Arrays.toString(columns));
                    for (int i = 0; i < columns.length; i++) {
                        //make sure not to add any empty characters or those ridiculous dashes
                        if (!columns[i].equals("") && !columns[i].contains("-")) {
                            columnsList.add(columns[i]);
                        }
                    }

                    dataFile.add(columnsList);
                   // System.out.println("dataFile=====" + dataFile);
                    columnsList = new ArrayList<>();
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataFile;
    }
}

