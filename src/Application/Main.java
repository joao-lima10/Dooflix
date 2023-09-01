package Application;

import java.sql.*;
import java.util.Scanner;
import Contas.Users;
import java.util.ArrayList;
import Contas.Login;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Users> userList;

    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection
                    ( "jdbc:mysql://localhost:3306/dooflix", "root", "06062020");

            System.out.println(con);
        }
        catch (Exception e){
        }

        userList = new ArrayList<>();

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

        // Adicione o código para inserir os dados no banco de dados
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

        for (Users user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                validLogin = true;
                break;
            }
        }

        if (validLogin) {
            System.out.println("Next"); // Login válido
            System.out.println("\nWelcome " + username + ", have fun!");
            System.out.println("\nSee some movies in high: ");
            System.out.println("\nOppenheimer" +
                    "\nBarbie" +
                    "\nTransformer" +
                    "\nFast & Furious");
        } else {
            System.out.println("Invalid login"); // Login inválido

        }

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
