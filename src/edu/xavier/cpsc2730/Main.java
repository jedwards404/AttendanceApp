package edu.xavier.cpsc2730;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Main {
    final static int MIN_ALLOWED_ABSENCES = 3;
    final static int MAX_NUM_ABSENCES = 10;
    final static int FE = 7;
    final static int X = 8;
    final static int Y = 4;

    final static Random rand = new Random();

    public static void main(String[] args) {

        int sprintNum = 1;

        System.out.println("\n=====  Welcome to the Attendance App  =====\n");

        // todoTODO difference between i++ and ++i
        System.out.println("\n>>>>>>>>>>  Sprint #" + (sprintNum++) + "\n");
        String username = readUserName();
        System.out.println("\nHello, your name is " + username + "\n");

        ArrayList<Integer> attended = initializeAbsences(username.length(), MAX_NUM_ABSENCES);
        System.out.println("\nThe absences are " + attended);

        ArrayList<Integer> listOfIntegers = listOfPerfectAttendees(attended);
        System.out.println("\nStudents at indexes " + listOfIntegers + " had perfect attendance");

        int count = countPerfectAttendees(attended);
        System.out.println("There are " + count + " students with perfect attendance.");

        double avg = averageAbsences(attended);
        System.out.println("The average number of absences is " + avg);

        // TODO change name of absencesLessHolder to something more descriptive
        listOfIntegers = absencesLessThan(attended, MIN_ALLOWED_ABSENCES);
        System.out.println("There are " + listOfIntegers.size() + " students with fewer than " + MIN_ALLOWED_ABSENCES + " absences.");

        avg = percentOfAbsences(attended, MIN_ALLOWED_ABSENCES);
        System.out.println("The percent of students with fewer than " + MIN_ALLOWED_ABSENCES + " absences is " + avg);

        listOfIntegers = indexesOfFEs(attended, FE);
        System.out.println("The students who FE'd are at indexes " + listOfIntegers);


        System.out.println("\n>>>>>>>>>>  Sprint #" + (sprintNum++) + "\n");
        System.out.println("\nthe absences are                    " + attended);
        listOfIntegers = duplicates(attended);
        System.out.println("The values that are duplicated:     " + listOfIntegers);

        listOfIntegers = uniqueAbsences(attended);
        System.out.println("the values with duplicates removed: " + listOfIntegers);


        System.out.println("\n>>>>>>>>>>  Sprint #" + (sprintNum++) + "\n");
        Map<Integer, Integer> mapIntCount = numOfEachAbsenceValue(attended);
        System.out.println("\nThe count of each absence value (value=count) is " + mapIntCount);

        listOfIntegers = shuffleAbsences(attended);
        System.out.println("\nthe shuffled absences are       " + listOfIntegers);

        // todoTODO fix bubbleSort so it properly returns "new memory"
        listOfIntegers = bubbleSort(attended);
        System.out.println("The array when bubble sorted is " + listOfIntegers);


        System.out.println("\n>>>>>>>>>>  Sprint #" + (sprintNum++) + "\n");
        ArrayList<String> names = store5names();
        names = shuffleNames(names);
        names = buildListNames(store5names(), attended.size());

        System.out.printf("\n%2s \t %10s \t %2s\n", "i", "names", "absences");
        for (int i = 0; i < names.size(); i++) {
            System.out.printf("%2d \t %10s \t %2d\n", i, names.get(i), attended.get(i));
        }

        ArrayList<String> uniqueNames = uniques(names);
        System.out.println("\nThe unique names are " + uniqueNames);

        boolean allFiveUsed = allNamesUsed(names, uniqueNames);
        System.out.println("Were all the names used? " + allFiveUsed);


        System.out.println("\n>>>>>>>>>>  Sprint #" + (sprintNum++) + "\n");
        ArrayList<String> listOfStrings = perfectAttendeesNames(names, attended);
        System.out.println("\nThe names of the students with perfect attendance are " + listOfStrings);

        listOfStrings = studentsWhoFEd(names, attended);
        System.out.println("The names of the students who FE'd a course are " + listOfStrings);

        String name = names.get(rand.nextInt(names.size()));
        int num = numOfCoursesFinder(buildListNames(names, attended.size()), name);
        System.out.println("\nStudent name: " + name + " has " + num + " courses");

        //How many courses does each student have? (using map)
        listOfStrings = buildListNames(names, attended.size());
        Map<String, Integer> map = allStudentCourseFinder(listOfStrings);
        System.out.println("\nThe number of courses for all the students are : " + map);
        for (String key : map.keySet())
            System.out.println("Student : " + key + " Courses: " + map.get(key));


        System.out.println("\n>>>>>>>>>>  Sprint #" + (sprintNum++) + "\n");
        LocalDate today = LocalDate.now();
        System.out.println("today is " + today);
        System.out.println("today is " + today.format(DateTimeFormatter.ofPattern("yyyy MM dd")));
        System.out.println("today's month is " + today.getMonth());

        LocalDateTime todayTime = LocalDateTime.now();
        System.out.println("\ntoday is " + todayTime);
        System.out.println("today is " + todayTime.format(DateTimeFormatter.ofPattern("yyyy MM dd  hh:ss")));

    }

    // TODO replace all fori loops, that you can, with equivalent enhanced-for loops
    private static Map<String, Integer> allStudentCourseFinder(ArrayList<String> elements) {
        Map<String, Integer> map = new HashMap<>();
        for (String key : elements) {

            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }


        return map;
    }


    private static int numOfCoursesFinder(ArrayList<String> buildListNames, String name) {
        //todo numOfCoursesFinder() has an error
        int answer = 0;
        for (int i = 0; i < buildListNames.size(); i++) {
            if (buildListNames.contains(buildListNames.get(i)) == buildListNames.contains(name)) {

                answer++;
            }

            return answer;

        }
        return answer;
    }


    private static ArrayList<String> studentsWhoFEd
            (ArrayList<String> studentNames, ArrayList<Integer> absences) {
        ArrayList<String> answer2 = new ArrayList<>();
        for (int i = 0; i < absences.size(); i++) {
            if (absences.get(i) <= FE) {
                answer2.add(studentNames.get(i));
            }
        }
        return answer2;
    }


    private static ArrayList<String> perfectAttendeesNames
            (ArrayList<String> studentNames, ArrayList<Integer> absences) {
        ArrayList<String> perfectAttendeesNames = new ArrayList<>();
        for (int i = 0; i < absences.size(); i++) {
            if (absences.get(i) == 0) {
                perfectAttendeesNames.add(studentNames.get(i));
            }
        }
        return perfectAttendeesNames;
    }


    private static boolean allNamesUsed(ArrayList<String> usedNames, ArrayList<String> uniqueNames) {
        boolean allUsed = false;
        for (int i = 0; i < usedNames.size(); i++) {
            allUsed = uniqueNames.contains(usedNames.get(i));

            if (!allUsed) {
                return false;
            }
        }
        return allUsed;
    }

    /**
     * count of students who have perfect attendance
     *
     * @param absences list of absences
     * @return the number of students who zero absences
     */
    private static int countPerfectAttendees(ArrayList<Integer> absences) {
        return listOfPerfectAttendees(absences).size();
    }

    /**
     * Return a list of length random integers in the range [0..bound]
     *
     * @param length the number of integers to store in the list
     * @param bound  the upper bound of the random integers
     * @return the list of length random integers
     */
    private static ArrayList<Integer> initializeAbsences(int length, int bound) {
        Random rand = new Random();
        ArrayList<Integer> absences = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int genrandom = rand.nextInt(bound + 1); // [0..bound]
            absences.add(genrandom);
        }
        return absences;
    }

    //Function to find perfect attendees
    private static ArrayList<Integer> listOfPerfectAttendees(final ArrayList<Integer> attended) {
        ArrayList<Integer> perfectAttendees = new ArrayList<>();
        for (int i = 0; i < attended.size(); i++) {
            if (attended.get(i) == 0) {
                perfectAttendees.add(i);
            }
        }
        return perfectAttendees;
    }

    /**
     * calculates the average of all absences
     *
     * @param absences list of absences
     * @return the average
     */
    private static double averageAbsences(final ArrayList<Integer> absences) {
        double total = 0;
        for (int i = 0; i < absences.size(); i++) {
            total = total + absences.get(i);
        }
        total = total / absences.size();
        return total;
    }

    //Function to find people who have less than some # of absences
    private static ArrayList<Integer> absencesLessThan(final ArrayList<Integer> absences, int FE) {
        ArrayList<Integer> absencesCalcHolder = new ArrayList<>();
        for (int i = 0; i < absences.size(); i++) {
            if (absences.get(i) < FE) {
                absencesCalcHolder.add(absences.get(i));
            }
        }
        return absencesCalcHolder;
    }

    /**
     * calculates the percentage of student who FE'd
     *
     * @param attended the lis tof students
     * @param FE       the number of absences that results in an FE
     * @return the percentage of students who have FE'd
     */
    private static double percentOfAbsences(final ArrayList<Integer> attended, int FE) {
        double percentFinder;
        int denominator = absencesLessThan(attended, FE).size();
        if (denominator != 0) {
            percentFinder = (double) absencesLessThan(attended, FE).size() / attended.size() * 100;
        } else {
            return 0;


        }

        return percentFinder;
    }

    /**
     * what are the indexes of students who FE'd
     *
     * @param attended the list of students
     * @param FE       the number of absences causing an FE
     * @return the list of indexes of students who have FE'd
     */
    private static ArrayList<Integer> indexesOfFEs(final ArrayList<Integer> attended, int FE) {

        ArrayList<Integer> studentsWhoFE = new ArrayList<>();
        ArrayList<Integer> list = absencesLessThan(attended, FE);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < FE) {
                studentsWhoFE.add(i);
            }

        }
        return studentsWhoFE;
    }

    //Function to add value [X] to absence greater than [Y]
    private static ArrayList<Integer> changeElementYbyX(final ArrayList<Integer> attended, int X, int Y) {
        for (int i = 0; i < attended.size(); i++) {
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

    /**
     * this function sorts the absences
     *
     * @param attended this is the absence arrayList
     * @return sorted list of absences
     */

    private static ArrayList<Integer> sortAbsences(final ArrayList<Integer> attended) {
        for (int i = 0; i < attended.size(); i++) {
            Collections.sort(attended);
        }
        return attended;
    }


    /**
     * shuffle the provided list, side effect is the parameter is shuffled
     *
     * @param attended the list
     * @return the shuffled lsit
     */
    private static ArrayList<Integer> shuffleAbsences(final ArrayList<Integer> attended) {
        // ArrayList<Integer> temp = attended;  // DON'T DO THIS

        // Notice I used the keyword "new" which means "new memory is used"
        ArrayList<Integer> temp = new ArrayList<>();
        temp.addAll(attended);

        for (int i = 0; i < attended.size(); i++) {
            Collections.shuffle(temp);
        }
        return temp;
    }

    /**
     * What are the duplicates in the list
     *
     * @param attended the list
     * @return the duplicates
     */
    private static ArrayList<Integer> duplicates(final ArrayList<Integer> attended) {
        ArrayList<Integer> duplicateAbsenceValues = new ArrayList<>();
        for (int i = 0; i < attended.size(); i++) {
            if (attended.lastIndexOf(attended.get(i)) != i) {
                duplicateAbsenceValues.add(attended.get(i));
            }

        }
        return uniqueAbsences(duplicateAbsenceValues);
    }

    /**
     * what are the unique absence values, using a Set
     *
     * @param array the absences
     * @return the list of unique absences
     */
    private static ArrayList<Integer> uniqueAbsences(final ArrayList<Integer> array) {
        ArrayList<Integer> uniques = new ArrayList<>();
        Set<Integer> uniquesSet = new HashSet<>();

        for (int i = 0; i < array.size(); i++) {
            int num = array.get(i);

            if (uniquesSet.add(num)) {
                uniques.add(num);
            }
        }

        return uniques;
    }


    /**
     * count the number of each absence value, using a Map
     *
     * @param attended the list of absences
     * @return the count of each absence value
     */
    private static Map<Integer, Integer> numOfEachAbsenceValue(final ArrayList<Integer> attended) {

        Map<Integer, Integer> values = new HashMap<>();
        for (int i = 0; i < attended.size(); i++) {     // for all the values in the list
            int key = attended.get(i);                  // get an absence value, the key

            if (values.containsKey(key)) {              // if the list already has the key
                values.put(key, values.get(key) + 1);   // increment the counter
            } else {
                values.put(key, 1);                     // otherwise set the counter to 1
            }
        }

        return values;
    }

    //function to shuffle absences using Collections.shuffle()

    private static void shufflesAbsences(ArrayList<Integer> attended) {
        Collections.shuffle(attended);
    }

    //function to use bubble sort (takes ArrayList and takes void)
    private static ArrayList<Integer> bubbleSort(ArrayList<Integer> attended) {
        ArrayList<Integer> temp = new ArrayList<>(attended);
        for (int i = 0; i < temp.size(); i++) {
            for (int j = i; j < temp.size(); j++) {
                if (temp.get(i) > temp.get(j)) {
                    //swap
                    int tempNum = temp.get(i);
                    temp.set(i, temp.get(j));
                    temp.set(j, tempNum);
                }
            }
        }
        return temp;
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
    private static ArrayList<String> shuffleNames(ArrayList<String> usernameHolder) {
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
    private static ArrayList<String> uniques(ArrayList<String> list) {
        Set<String> listedUniques = new HashSet<>();
        ArrayList<String> uniques = new ArrayList<>();

        for (int i = 0; i < store5names().size(); i++) {
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
