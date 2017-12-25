package edu.xavier.cpsc2730;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        //read in user's name
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your name:\t");
        String username = sc.next();
        System.out.println("\n\nHello, and welcome " + username + " to my new Attendance App!\n");

        //create ArrayList to find absences and put random numbers in an elements
        ArrayList<Integer> attended = Initialize(username);
        System.out.println("The elements are " + attended);

        // how many students have perfect att
    }

    private static ArrayList<Integer> Initialize(String username) {
        Random rand = new Random();
        ArrayList<Integer> absenses = new ArrayList<>();
        for (int i = 0; i < username.length(); i++) {
            int genrandom = rand.nextInt(11);
            absenses.add(genrandom);
        }
        return absenses;
    }
}
