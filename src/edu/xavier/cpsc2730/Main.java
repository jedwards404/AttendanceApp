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
        System.out.println("\n\nHello, and welcome " + username + " to my new Attendance App!");

        //create ArrayList to find absences and put random numbers in as elements
        Random rand = new Random();
        int genrandom = rand.nextInt(11);
        ArrayList attended = new ArrayList();
        for (int i = 0; i < 10,i++){
            attended.add(genrandom);

        }
        System.out.println("The elements are " + attended[])
    }
}
