package edu.xavier.cpsc2730;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        //read user's name
        String username = readUserName();
        System.out.println("\n\nHello " + username + ". Welcome to my new Attendance App!\n");

        //create ArrayList to find absences and put random numbers in an elements
        ArrayList<Integer> attended = initialize(username);
        System.out.println("The elements are " + attended);
        System.out.println("There are " + listOfPerfectAttendees(attended) + " students with perfect attendance");
        System.out.println("There are " + countPerfectAttendees( attended ) + " students with perfect attendance.");


    }

    private static int countPerfectAttendees( ArrayList<Integer> attended ) {
        return listOfPerfectAttendees( attended ).size();
    }
    
    // Function to make absenses
    private static ArrayList<Integer> initialize(String username) {
        Random rand = new Random();
        ArrayList<Integer> absenses = new ArrayList<>();
        for (int i = 0; i < username.length(); i++) {
            int genrandom = rand.nextInt(11);
            absenses.add(genrandom);
        }
        return absenses;
    }

    //Function to find perfect attendees
    private static ArrayList<Integer> listOfPerfectAttendees(ArrayList<Integer> attended) {
        ArrayList<Integer> perfectAttendees = new ArrayList<>();
        for (int i = 0; i < attended.size(); i = i + 1) {
            if (attended.get(i) == 0) {
                perfectAttendees.add(i);
            }
        }
        return perfectAttendees;
    }

    private static String readUserName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your name:\t");
        String username = sc.next();
        return username;
    }
}
