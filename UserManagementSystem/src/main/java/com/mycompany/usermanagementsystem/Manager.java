package com.mycompany.usermanagementsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Manager {
    Validate vl = new Validate();
    
    public int menu() {
        System.out.println("1. Create a new account.");
        System.out.println("2. Login system.");
        System.out.println("3. Exit.");
        System.out.print("Enter your choice: ");
        int choice = vl.checkInputIntLimit(1, 3);
        return choice;
    }

    
    public void createNewAccount() {
        //check file data exist or not
        if (!vl.checkFileExist()) {
            return;
        }
        String username = vl.checkInputUsername();
        String password = vl.checkInputPassword();
        
        if (!vl.checkUsernameExist(username)) {
            System.err.println("Username exist.");
            return;
        }
        addAccountData(username, password);
    }

   
    public void loginSystem() {
        
        if (!vl.checkFileExist()) {
            return;
        }
        String username = vl.checkInputUsername();
        String password = vl.checkInputPassword();
        
        if (!vl.checkUsernameExist(username)) {
            if (!password.equalsIgnoreCase(passwordByUsername(username))) {
                System.err.println("Password incorrect.");
            }
            System.err.println("Login success.");
        }
    }

    
    public void addAccountData(String username, String password) {
        File file = new File("E:/user.dat");
        try {
            FileWriter fileWriter = new FileWriter("E:/user.dat", true);
            fileWriter.write(username + ";" + password + "\n");
            fileWriter.close();
            System.err.println("Create successly.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    public String passwordByUsername(String username) {
        File file = new File("E:/user.dat");
        try {
            FileReader fileReader = new FileReader("E:/user.dat");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] account = line.split(";");
                if (username.equalsIgnoreCase(account[0])) {
                    return account[1];
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}