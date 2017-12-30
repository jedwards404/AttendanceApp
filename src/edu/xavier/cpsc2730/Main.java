package edu.xavier.cpsc2730;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    final static int minOfAbsences = 3;
    final static int FE = 7;
    final static int X = 8;
    final static int Y = 4;

    public static void main(String[] args) {

        //read user's name
        String username = readUserName();
        System.out.println("\n\nHello " + username + ". Welcome to my new Attendance App!\n");

        //create ArrayList to find absences and put random numbers in an elements
        ArrayList<Integer> attended = initialize(username);
        ArrayList<Integer> countOfSameAbsenceValue = new ArrayList<>();
        System.out.println("The elements are " + attended);
        System.out.println("There are " + listOfPerfectAttendees(attended) + " students with perfect attendance");
        System.out.println("There are " + countPerfectAttendees(attended) + " students with perfect attendance.");
        System.out.println("The average number of absences is " + averageFinder(attended));
        System.out.println("There are " + absencesLessHolder(attended, minOfAbsences).size() + " students with fewer than " + minOfAbsences + " absences and " + ((absencesLessHolder(attended, minOfAbsences).size() / attended.size()) * 100) + " students with perfect attendance");
        System.out.println("The percent of students with less than " + minOfAbsences + " absences is " + percentOfAbsences(attended, minOfAbsences));
        System.out.println("The students who FE'd are " + indexOfFEStudents(attended, FE));
        System.out.println(duplicateElementFinder(attended));
        System.out.println("uniques: " + uniqueElementFinder(attended));
        System.out.println("The absences are " + numOfEachAbsenceValue(attended, countOfSameAbsenceValue));
        shuffleAbsences(attended);
        System.out.println("the shuffled absences are " + attended);
        bubbleSort(attended);
        System.out.println("The array when bubble sorted is " + attended);


        for (int element : attended) {
            System.out.print(element + ", ");
        }

        System.out.println("stream: " + attended.stream().filter(p -> p % 2 == 0).count());
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
    private static ArrayList<Integer> listOfPerfectAttendees(final ArrayList<Integer> attended) {
        ArrayList<Integer> perfectAttendees = new ArrayList<>();
        for (int i = 0; i < attended.size(); i = i + 1) {
            if (attended.get(i) == 0) {
                perfectAttendees.add(i);
            }
        }
        return perfectAttendees;
    }

    //Function to find average of all absences
    private static double averageFinder(final ArrayList<Integer> absenses) {
        double total = 0;
        for (int i = 0; i < absenses.size(); i = i + 1) {
            total = total + absenses.get(i);
        }
        total = total / absenses.size();
        return total;
    }

    //Function to find people who have less than some # of absences
    private static ArrayList<Integer> absencesLessHolder(final ArrayList<Integer> absences, int FE) {
        ArrayList<Integer> absencesCalcHolder = new ArrayList<>();
        for (int i = 0; i < absences.size(); i = i + 1) {
            if (absences.get(i) < FE) {
                absencesCalcHolder.add(absences.get(i));
            }
        }
        return absencesCalcHolder;
    }

    //Function to find percentage
    private static int percentOfAbsences(final ArrayList<Integer> attended, int FE) {
        int percentFinder = ((listOfPerfectAttendees(attended).size() / absencesLessHolder(attended, FE).size()) * 100);
        return percentFinder;
        //test
        //test 2
        //test 3
    }

    //Function to find index of people who FE'd
    private static ArrayList<Integer> indexOfFEStudents(final ArrayList<Integer> attended, int FE) {

        ArrayList<Integer> studentsWhoFE = new ArrayList<>();
        ArrayList<Integer> list = absencesLessHolder(attended, FE);
        for (int i = 0; i < list.size(); i = i + 1) {
            if (list.get(i) < FE) {
                studentsWhoFE.add(i);
            }

        }
        return studentsWhoFE;
    }

    //Function to add value [X] to absence greater than [Y]
    private static ArrayList<Integer> changeElementYbyX(final ArrayList<Integer> attended, int X, int Y) {
        for (int i = 0; i < attended.size(); i = i + 1) {
            if (attended.get(i) < Y) {
                attended.set(X, i);
                // example int num = attended.get(i < Y ? 5:2);
                if (attended.get(i) > 15) {
                    attended.set(i, 15);
                }
                if (attended.get(i) < 0) {
                    attended.set(i, 0);
                }
            }
        }
        return attended;

    }

    //Function to sort absences array using library function
    private static ArrayList<Integer> sortAbsences(final ArrayList<Integer> attended) {
        for (int i = 0; i < attended.size(); i = i + 1) {
            Collections.sort(attended);
        }
        return attended;
    }

    //Function to shuffle the elements in the absences ArrayList
    private static ArrayList<Integer> shuffleAbsences(final ArrayList<Integer> attended) {
        for (int i = 0; i < attended.size(); i = i + 1) {
            Collections.shuffle(attended);
        }
        return attended;
    }

    //Function to find duplicate elements in absences
    private static ArrayList<Integer> duplicateElementFinder(final ArrayList<Integer> attended) {
        ArrayList<Integer> duplicateAbsenceValues = new ArrayList<>();
        for (int i = 0; i < attended.size(); i = i + 1) {
            if (attended.lastIndexOf(attended.get(i)) != i) {
                duplicateAbsenceValues.add(attended.get(i));
            }

        }
        return duplicateAbsenceValues;
    }

    // function to return unique elements in attended
    private static ArrayList<Integer> uniqueElementFinder(final ArrayList<Integer> array) {
        ArrayList<Integer> uniques = new ArrayList<>();
        Set<Integer> uniquesSet = new HashSet<>();

        for (int i = 0; i < array.size(); i = i + 1) {
            int num = array.get(i);
            if (uniquesSet.add(num) == true) {
                uniques.add(num);
            }
        }

        return uniques;
    }

    /*

    get unique elements
    for each element in uniques
        int count = countElement( element, attended );




     */

    //function to return num of each absence value
    private static Map<Integer, Integer> numOfEachAbsenceValue(final ArrayList<Integer> attended, ArrayList<Integer> countOfSameAbsenceValue) {

        Map<Integer, Integer> values = new HashMap<>();
        for (int i = 0; i < attended.size(); i++) {
            int key = attended.get(i);

            if (values.containsKey(key)) {
                values.put(key, values.get(key) + 1);
            } else {
                values.put(key, 1);
            }
        }

        return values;
    }

    //function to shuffle absences using Collections.shuffle()

    private static void shufflesAbsences(ArrayList<Integer> attended) {
        Collections.shuffle(attended);
    }

    //function to use bubble sort (takes ArrayList and takes void)
    private static void bubbleSort(ArrayList<Integer> attended) {
        for (int i = 0; i < attended.size(); i++) {
            for (int j = i; j < attended.size(); j++) {
                if (attended.get(i) > attended.get(j)) {
                    //swap
                    int temp = attended.get(i);
                    attended.set(i, attended.get(j));
                    attended.set(j, temp);

//                int temp = num1;
//                num1 = num2;
//                num2 = temp;


                }
            }
        }
    }

//
//
//        ArrayList<Integer> sortedElementOfAbsenceValue = new ArrayList<>();
//        sortAbsences(attended);
//        System.out.println("Sorted attended: " + attended);
//        int i = 0;
//        while (i < attended.size() - 1) {
//            sortedElementOfAbsenceValue.add(attended.get(i));
//            countOfSameAbsenceValue.add(1);
//            // if (attended.get(i) == attended.get(i + 1)) {
//            while (i < attended.size() && sortedElementOfAbsenceValue.get(i) == attended.get(i + 1)) {
//                countOfSameAbsenceValue.set(i, countOfSameAbsenceValue.get(i) + 1);
//                i = i + 1;
//            }
//            if (sortedElementOfAbsenceValue.get(sortedElementOfAbsenceValue.size()) != attended.get(attended.size() - 1)) {
//                sortedElementOfAbsenceValue.add(attended.size() - 1);
//                countOfSameAbsenceValue.add(1);
//            }
//            //}
//
//            i++;
//        }
//
//        return sortedElementOfAbsenceValue;
//




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
