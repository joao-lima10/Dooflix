package Application;

import java.awt.*;
import java.sql.*;
import java.util.Scanner;
import java.net.URI;


import Contas.Credit;
import Contas.Users;
import java.util.ArrayList;


public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Users> userList;
    static ArrayList<Credit> creditCard;
    private static Connection con1;

    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection
                    ( "jdbc:mysql://localhost:3306/dooflix", "root", "06062020");

            System.out.println(con);
        }
        catch (Exception e){
        }

        userList = new ArrayList<>();

        creditCard = new ArrayList<>();

        choice();

    }

    public static void choice() {

        System.out.println("\nWelcome to Dooflix");
        System.out.println("\nSelect option");
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

        // Adicionando o código para inserir os dados no banco de dados
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dooflix", "root", "06062020");

            String query = "INSERT INTO users (name, cpf, email, username) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, cpf);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, username);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        userList.add(users);

        System.out.println("Account created successfully");

        choice();
    }

    public static void enterAccount() {
        System.out.printf("\nUsername: ");
        String username = sc.next();

        System.out.printf("\nPassword: ");
        String password = sc.next();

        boolean validLogin = false;

        //declarei uma nova variável sem ligação com a Users
        Users currentUser = null;

        // faz o processo de validação do login
        for (Users user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                validLogin = true;
                currentUser = user;
                break;
            }
        }

        if (validLogin) {
            System.out.println("\nWelcome " + username + ", have fun!");

            boolean loggedIn = true;

            //repetição do menu enquanto for verdadeiro
            while (loggedIn) {

                System.out.println("\nSelect the option:");
                System.out.println("Option 1: View Movies");
                System.out.println("Option 2: Settings");
                System.out.println("Option 3: Logout");

                int userChoice = sc.nextInt();

                switch (userChoice) {

                    case 1:

                        seeMovies(currentUser);
                        break;

                    case 2:

                        settingsMenu(currentUser);
                        break;

                    case 3:

                        System.out.println("Logged out");
                        loggedIn = false;
                        break;

                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        } else {

            System.out.println("Invalid login");
        }

        choice();
    }

    private static void seeMovies(Users currentUser) {
        System.out.println("\nSee some movies in high: ");
        System.out.println("1: Oppenheimer");
        System.out.println("2: Barbie");
        System.out.println("3: Transformer");
        System.out.println("4: Fast & Furious");

        int movieChoice = sc.nextInt();

        switch (movieChoice) {
            case 1:
                openLink("https://www.youtube.com/watch?v=kEGlCYF5gaU");
                break;
            case 2:

                openLink("https://www.youtube.com/watch?v=Ujs1Ud7k49M");
                break;
            case 3:

                openLink("https://www.youtube.com/watch?v=PHC412-pCoQ");
                break;
            case 4:

                openLink("https://www.youtube.com/watch?v=a1w9x5U88jU");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static void openLink(String url) {
        try {
            URI link = new URI(url);
            Desktop.getDesktop().browse(link);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void settingsMenu(Users currentUser) {
        boolean settingsMenuOpen = true;

        while (settingsMenuOpen) {

            System.out.println("\nSettings Menu:");
            System.out.println("Option 1: Add Payment Method");
            System.out.println("Option 2: Back to Main Menu");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nPut your credit card number: ");
                    String cardnumber = sc.next();

                    System.out.println("\nAdd your security code: ");
                    String cod = sc.next();

                    System.out.println("\nAdd the card expiration date: ");
                    String date = sc.next();

                    try {
                        Connection con = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/dooflix", "root", "06062020");

                        String query = "INSERT INTO credit (cardnumber, cod, date) VALUES (?, ?, ?)";

                        PreparedStatement preparedStatement = con.prepareStatement(query);

                        preparedStatement.setString(1, cardnumber);
                        preparedStatement.setString(2, cod);
                        preparedStatement.setString(3, date);

                        preparedStatement.executeUpdate();

                        preparedStatement.close();
                        con.close();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Credit Card added successfully");
                    break;

                case 2:
                    System.out.println("Returning to main menu.");
                    settingsMenuOpen = false;
                    break;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
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
