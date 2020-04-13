package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static void main(String[] args) {
    	getCSVContent("\\target\\test-classes\\com\\upgrade\\testdata\\basicinfo.csv");
    }

    public static List<String[]> getCSVContent(String str) {
    	File file = new File(System.getProperty("user.dir")+str);
        List<String[]> records = new ArrayList<String[]>();
        BufferedReader buffer = null;
        String line = "";
        String cvsSplitBy = ","; 
        
        try {
        	buffer = new BufferedReader(new FileReader(file));
            while ((line = buffer.readLine()) != null) {
                // use comma as separator
                String[] elementRecord = line.split(cvsSplitBy);
               records.add(elementRecord);
            }
            
//            for(int i=0;i<records.size();i++){
//                System.out.println(records.get(i));
//                String[] info1 = records.get(i);
//                for(int j=0;j<info1.length;j++) {
//                    System.out.println(info1[j]);
//                }
//            } 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buffer != null) {
                try {
                	buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return records;
    }  
}