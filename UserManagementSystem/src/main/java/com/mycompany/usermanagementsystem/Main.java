

package com.mycompany.usermanagementsystem;




public class Main {

    public static void main(String[] args) {
        Manager mn = new Manager();
        while(true) {
            int choice = mn.menu();
            switch (choice) {
                case 1:
                    mn.createNewAccount();
                    break;
                case 2:
                    mn.loginSystem();
                    break;
                case 3:
                    return;
            }
        }
    }
}
