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
        System.out.println("There are " + countPerfectAttendees(attended) + " students with perfect attendance.");
        int a = 3;
        System.out.println("The average number of absences is " + averageFinder(attended));
        System.out.println("There are " + absencesLessHolder(attended, a) + " students with less than " + a + " absences and " + ((absencesLessHolder(attended, a).size() / attended.size()) * 100) + " students with perfect attendance");


    }

    private static int countPerfectAttendees(ArrayList<Integer> attended) {
        return listOfPerfectAttendees(attended).size();
    }

    // Function to make absences
    private static ArrayList<Integer> initialize(String username) {
        Random rand = new Random();
        ArrayList<Integer> absences = new ArrayList<>();
        for (int i = 0; i < username.length(); i++) {
            int genrandom = rand.nextInt(11);
            absences.add(genrandom);
        }
        return absences;
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

    //Function to find average of all absences
    private static double averageFinder(ArrayList<Integer> absenses) {
        double total = 0;
        for (int i = 0; i < absenses.size(); i = i + 1) {
            total = total + absenses.get(i);
        }
        total = total / absenses.size();
        return total;
    }

    //Function to find people who have less than three absences
    private static ArrayList<Integer> absencesLessHolder(ArrayList<Integer> absences, int a) {
        ArrayList<Integer> absencesCalcHolder = new ArrayList<>();
        for (int i = 0; i < absences.size(); i = i + 1) {
            if (absences.get(i) < a) {
                absencesCalcHolder.add(absences.get(i));
            }
        }
        return absencesCalcHolder;
    }

   /* private static int perfectAttendees(ArrayList<Integer> attended) {
        int numPerfAttendees = 0;
        for (int i = 0; i < attended.size(); i++) {
            if (attended.get(i) == 0) {

                numPerfAttendees = numPerfAttendees + 1;
            }
        }
        return numPerfAttendees;
        /test comment
        */


    private static String readUserName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your name:\t");
        String username = sc.next();
        return username;
    }
}
