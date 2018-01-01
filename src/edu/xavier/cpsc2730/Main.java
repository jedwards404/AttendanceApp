package edu.xavier.cpsc2730;


import java.util.*;


public class Main {
    final static int MIN_ALLOWED_ABSENCES = 3;
    final static int MAX_NUM_ABSENCES = 10;
    final static int FE = 7;
    final static int X = 8;
    final static int Y = 4;


    public static void main(String[] args) {

        System.out.println("\n=====  Welcome to the Attendance App  =====\n");

        String username = readUserName();
        System.out.println("\nHello, your name is " + username + "\n");

        ArrayList<Integer> attended = initializeAbsences(username.length(), MAX_NUM_ABSENCES);
        System.out.println("The elements are " + attended);

        ArrayList<Integer> countOfSameAbsenceValue = new ArrayList<>();
        System.out.println("There are " + listOfPerfectAttendees(attended) + " students with perfect attendance");
        System.out.println("There are " + countPerfectAttendees(attended) + " students with perfect attendance.");
        System.out.println("The average number of absences is " + averageFinder(attended));
        System.out.println("There are " + absencesLessHolder(attended, MIN_ALLOWED_ABSENCES).size() + " students with fewer than " + MIN_ALLOWED_ABSENCES + " absences and " + ((absencesLessHolder(attended, MIN_ALLOWED_ABSENCES).size() / attended.size()) * 100) + " students with perfect attendance");
        System.out.println("The percent of students with less than " + MIN_ALLOWED_ABSENCES + " absences is " + percentOfAbsences(attended, MIN_ALLOWED_ABSENCES));
        System.out.println("The students who FE'd are " + indexOfFEStudents(attended, FE));
        System.out.println(duplicateElementFinder(attended));
        System.out.println("uniques: " + uniqueElementFinder(attended));
        System.out.println("The absences are " + numOfEachAbsenceValue(attended, countOfSameAbsenceValue));
        shuffleAbsences(attended);
        System.out.println("the shuffled absences are " + attended);
        bubbleSort(attended);
        System.out.println("The array when bubble sorted is " + attended);
        System.out.println("Your usernames are " + store5names());
        System.out.println("your shuffled usernames are " + shuffleUsernameHolder(store5names()));
        int numOfStudents = attended.size();
        System.out.println("usernames for array size of attended are " + buildListNames(store5names(), numOfStudents));

        // create an ArrayList of names
        ArrayList<String> names = store5names();

        // pull out the unique names
        ArrayList<String> uniqueNames = findUniques(names);
        System.out.println("\nThe unique names are " + uniqueNames);

        //Were all five names used at least once
        ArrayList<String> usedNames = store5names();
        boolean allFiveUsed = allNamesUsed(usedNames, uniqueNames);
        System.out.println("\nWere all the names were used? " + allNamesUsed(usedNames, uniqueNames));


        //What are the names of the students with perfect attendance
        ArrayList<String> studentNames = buildListNames(uniqueNames, attended.size());
        ArrayList<Integer> absences = initializeAbsences(username);

        ArrayList<String> answer = perfectAttendeesNames(studentNames, absences);
        System.out.println("The names of the students with perfect attendance are " + answer);

        //What are the names of the students who FE'd some course
        ArrayList<String> answer2 = studentsWhoFEd(studentNames, absences);
        System.out.println("The names of the students who FE'd a course are " + answer2);
    }

    private static ArrayList<String> studentsWhoFEd(ArrayList<String> studentNames, ArrayList<Integer> absences) {
        ArrayList<String> answer2 = new ArrayList<>();
        for (int i = 0; i < absences.size(); i = i + 1) {
            if (absences.get(i) <= FE) {
                answer2.add(studentNames.get(i));
            }
        }
        return answer2;
    }


    private static ArrayList<String> perfectAttendeesNames(ArrayList<String> studentNames, ArrayList<Integer> absences) {
        ArrayList<String> perfectAttendeesNames = new ArrayList<>();
        for (int i = 0; i < absences.size(); i = i + 1) {
            if (absences.get(i) == 0) {
                perfectAttendeesNames.add(studentNames.get(i));
            }
        }
        return perfectAttendeesNames;
    }


    private static boolean allNamesUsed(ArrayList<String> usedNames, ArrayList<String> uniqueNames) {
        boolean allUsed = false;
        for (int i = 0; i < usedNames.size(); i = i + 1) {
            allUsed = uniqueNames.contains(usedNames.get(i));
            if (allUsed == false) {
                return false;
            }
        }
        return allUsed;
    }

    private static int countPerfectAttendees(ArrayList<Integer> attended) {
        return listOfPerfectAttendees(attended).size();
    }

    /**
     *  Return a list of length random integers in the range [0..bound]
     * @param length the number of integers to store in the list
     * @param bound the upper bound of the random integers
     * @return the list of length random integers
     */
    private static ArrayList<Integer> initializeAbsences(int length, int bound) {
        Random rand = new Random();
        ArrayList<Integer> absences = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int genrandom = rand.nextInt(bound+1); // [0..bound]
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
        int percentFinder;
        int denominator = absencesLessHolder(attended, FE).size();
        if (denominator != 0) {
            percentFinder = ((listOfPerfectAttendees(attended).size() / denominator) * 100);
        } else {
            return 0;


        }

        return percentFinder;
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
                }
            }
        }
    }


    //Create and output an ArrayList of 5 distinct names
    private static ArrayList<String> store5names() {
        ArrayList<String> userNames = new ArrayList<>();
        userNames.add("Joshua");
        userNames.add("Andrea");
        userNames.add("Torin");
        userNames.add("Beatrice");
        userNames.add("Willie");
        return userNames;

    }

    //function to shuffle the name using a user-defined shuffle function
    private static ArrayList<String> shuffleUsernameHolder(ArrayList<String> usernameHolder) {
        Random random = new Random(usernameHolder.size());
        random.setSeed(System.nanoTime());


        for (int i = 0; i < usernameHolder.size(); i = i + 1) {
            int rand = random.nextInt(usernameHolder.size() + 1) % usernameHolder.size();
            String temp = usernameHolder.get(rand);
            usernameHolder.set(rand, usernameHolder.get(i));
            usernameHolder.set(i, temp);

        }

        return usernameHolder;
    }


    // repeatedly use list of names to end up with numOfNeedeNames
    private static ArrayList<String> buildListNames(ArrayList<String> names, int numOfNeededNames) {
        ArrayList<String> answer = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < numOfNeededNames; i++) {
            answer.add(names.get(rand.nextInt(names.size())));
        }

        return answer;
    }

    //Function to create second arraylist the same size as absenceslist
//    private static ArrayList<String> attendedUsernameHolderBuilder(ArrayList<String> names, int numNeededNames) {
//        ArrayList<String> dynamicUsernameHolder = new ArrayList<>();
//        for (int i = 0; i < numNeededNames; i = i + 1) {
//            dynamicUsernameHolder.add(shuffleUsernameHolder(names).get(i));
//        }
//        return dynamicUsernameHolder;
//    }

    //
    private static ArrayList<String> findUniques(ArrayList<String> list) {
        Set<String> listedUniques = new HashSet<>();
        ArrayList<String> uniques = new ArrayList<>();

        for (int i = 0; i < store5names().size(); i = i + 1) {
            listedUniques.add(store5names().get(i));

        }
        uniques.addAll(listedUniques);
        return uniques;
    }


    private static String readUserName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter your name:\t");
        String username = sc.next();
        return username;
    }

}
