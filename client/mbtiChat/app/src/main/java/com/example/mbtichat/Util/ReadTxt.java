package com.example.mbtichat.Util;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadTxt {
    private File file;
    private Scanner reader;
    public void openFile (String fileName) {
        try {
            file = new File(fileName);
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Errror in File Read");
            e.printStackTrace();
        }
    }

    public String getLine(){
        if(reader.hasNextLine()) {
            return reader.nextLine();
        }else return "EOF";
    }

    public void closeFile(){
        reader.close();
    }
}