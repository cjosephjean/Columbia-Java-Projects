/**
 * Fail2Ban.java - Documenter of IP Addresses of Invalid Login Attempts
 * 
 * Christopher Jean cjj2126
 * 
 */

import java.util.*;
import java.io.*;

public class Fail2Ban{

    public static void main(String[] args){
        // Checks that there are at least two arguments
        if (args.length != 2){
            System.out.println("Unable to check login.");
        }
        else{
            ArrayList<String> loginList = new ArrayList<String>();
            ArrayList<String> ipMasterList = new ArrayList<String>();
            String inputFile = args[0];
            String outputFile = args[1];
            try{
              File file = new File(inputFile);
              Scanner sc = new Scanner(file);
              // Checks for invalid login info
              while (sc.hasNextLine()){
                  String loginInfo = sc.nextLine();
                  if (loginInfo.contains("Invalid")){
                      String lineColumns[] = loginInfo.split(" ");
                      loginList.add(lineColumns[lineColumns.length - 1]);  
                  }
              }
            }
            catch(FileNotFoundException ex){
                System.out.println("File not found.");
            }
            // Checks for logins with 3+ invalid attempts
            ArrayList<String> ipAddresses = new ArrayList<String>();
            for (String item : loginList){
                if (Collections.frequency(loginList, item) >= 3){
                    ipAddresses.add(item);
                }
            }
            // Saves above invalid IP addresses to an output file
            if (ipAddresses.size() > 0){
                  for (String item : ipAddresses){
                      if (!ipMasterList.contains(item)){
                          ipMasterList.add(item);
                      }   
                  }
                  try{    
                      PrintWriter f0 = new PrintWriter(new FileWriter(outputFile));
                      for (String item : ipMasterList)
                      f0.println(item);
                      f0.close();
                  }
                  catch(IOException ex){
                        System.out.println("Unable to initialise.");
                  }
            }
            
        }
        
    }
}