package Application;

import java.util.Scanner;
import Contas.Users;
import java.util.ArrayList;
import Contas.Login;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Users> userList;

    public static void main(String[] args) {

        userList = new ArrayList<>();

        choice();

    }

    public static void choice() {

        System.out.println("Welcome to Dooflix");
        System.out.println("Select option");
        System.out.println("Option 1: Create Account");
        System.out.println("Option 2: Enter your account");
        System.out.println("Option 3: Recover my password");
        System.out.println("Option 4: List Account");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:

                createAccount();
                break;

            case 2:

                enterAccount();
                break;

            case 3:

                recoverPassword();
                break;

            case 4:

                listAccount();
                break;

            default:

                System.out.println("Invalid Option");
                break;
        }

    }

    public static void createAccount() {

        System.out.println("Name: ");
        String name = sc.next();

        System.out.println("Cpf: ");
        String cpf = sc.next();

        System.out.println("Email: ");
        String email = sc.next();

        System.out.println("Username: ");
        String username = sc.next();

        System.out.println("Password: ");
        String password = sc.next();

        Users users = new Users(name, cpf, email, username, password);

        userList.add(users);

        System.out.println("Account created successfully");

        choice();
    }

    public static void enterAccount() {

        System.out.printf("\nUsername: ");

        String username = sc.next();

        System.out.printf("\nPassword: ");

        String password = sc.next();

        choice();
    }

    public static void recoverPassword() {

        System.out.println("Email: ");
        String email = sc.next();

        System.out.println("Link send to " + email + ", please check");

        choice();
    }

    public static void listAccount() {

        if (userList.size() > 0) {

            for (Users users : userList) {

                System.out.println(users);

            }

        } else {

            System.out.println("No accounts");

        }

        choice();

    }

    public static void findAccount(String username) {

        Users users = null;

        for (Users users1 : userList) {

            if (users.getUsername() == username) {

                users = users1;

            } else {
                System.out.println("No Accounts");
            }
        }
    }
}
