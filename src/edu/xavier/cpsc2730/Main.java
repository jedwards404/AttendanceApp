package edu.xavier.cpsc2730;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Main {
    final static int MIN_ALLOWED_ABSENCES = 3;
    final static int MAX_NUM_ABSENCES = 10;
    final static int FE = 7;
    final static int X = 8;
    final static int Y = 4;
    final static int dateRange = 21;

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
        System.out.println("Joshua's birth date is " + LocalDate.of(1996, 10, 7));
        System.out.println("Joshua's birth date is " + LocalDate.of(1996, Month.OCTOBER, 7));

        //output the number of days I have been alive
        LocalDate joshuaBirthday = LocalDate.of(1996, Month.OCTOBER, 7);
        long answer1 = joshuaBirthday.toEpochDay();
        long answer2 = today.toEpochDay();
        long answer3 = answer2 - answer1;
        System.out.println("I have been alive " + answer3 + " days");

        LocalDate birthdate = LocalDate.of(1962, 7, 24);
        System.out.println("\nAndrea's birthdate is " + birthdate);

        long daysAlive = today.toEpochDay() - birthdate.toEpochDay();
        System.out.println("Andrea's been alive " + daysAlive + " days.");

        LocalDateTime todayTime = LocalDateTime.now();
        System.out.println("\ntoday is " + todayTime);
        System.out.println("today is " + todayTime.format(DateTimeFormatter.ofPattern("yyyy MM dd  hh:ss")));

        //store today's date
        LocalDate currentDate = LocalDate.now();
        System.out.println("today is " + currentDate.format(DateTimeFormatter.ofPattern("yyyy MM dd")));

        //birthday plus # long days compared to current date
        System.out.println(joshuaBirthday.plusDays(answer3));
        System.out.println(joshuaBirthday.plusDays(answer3).equals(currentDate));

        //add dates til list has same num as names list
        ArrayList<String> nameList = buildListNames(names, attended.size());
        ArrayList<LocalDate> dates = datesForNamesList(nameList, currentDate);

        //names of students with fewest absences
        ArrayList<Integer> absences = attended;
        nameList = buildListNames(names, attended.size());
        table(nameList, dates, attended);


        ArrayList<String> answer = minAbsenceNames(absences, nameList);
        System.out.println("\nThe student with the lowest num of absences is " + answer);

        //names of the students who have the longest number of days since last absence
        names = listOfStrings;
        dates = dates;
        LocalDate earliestDate = latestDate(dates);
        ArrayList<String> answernames = earliestDateName(names, earliestDate, dates);

        System.out.println("\nThe names are " + names);
        System.out.println("The dates are " + dates);
        System.out.println("The current date is " + currentDate);
        System.out.println("The earliest date is " + earliestDate);
        System.out.println("The student with the longest time since a absence is " + answernames);
        System.out.println("The names of the students with the longest number of days since last absence was : " + answernames + "\n");

        //difference between the earliest and the latest date

        LocalDate earliest = earliestDate(dates);
        LocalDate latest = latestDate(dates);
        long range = dateRange(earliest, latest);
        System.out.println("The earliest date is " + earliest);
        System.out.println("The latest date is " + latest);
        System.out.println("The range of absence dates is " + range);

        //what are the indexes of students who have [x] absence date

        LocalDate studentDate = dates.get(0);
        ArrayList<Integer> indexes = dateIndexes(dates, studentDate);
        System.out.println("\nThe date to compare is " + studentDate);
        System.out.println("The indexes of student with absence date " + studentDate + " are " + indexes + "\n");

        //what are the indexes of students with each absence date?

        ArrayList<LocalDate> keys = new ArrayList<>();
        ArrayList<Integer> absenceIndex = new ArrayList<>();
        Map<LocalDate, ArrayList<Integer>> dateMap = absenceDate(dates, attended);
        System.out.println("The indexes of students with each absence date are:  \n" + dateMap + "\n");

        //what are the indexes of students with the same absence date?
        Map<LocalDate, Integer> sameDateMap = sameAbsenceDate(dates);
        System.out.println("The count of indexes of students with the same date are : \n" + sameDateMap + "\n");

        // rename Record
        Record xavier = new Record();
        LocalDate date = LocalDate.now();
        System.out.println(xavier);
        System.out.println("today is " + date);

        //first parameterize constructor
        Record UNO = new Record("Andrea", 10, LocalDate.now());
        System.out.println("the parametrized constructor has " + UNO);

        //second parameterized constructor
        Record second = new Record("Torin", 20, LocalDate.of(2012, Month.FEBRUARY, 5));
        System.out.println("the second parametrized constructor has " + second);
        //test

        //third parameterized constructor
        Record third = new Record("James", 30, LocalDate.of(1999, Month.JULY, 20));
        System.out.println("the third parametrized constructor has " + third);


        //method to change dates
        System.out.println("\nThe original date is:\t" + date + "\n");
        long changeDateBy = 10;
        System.out.println("amount to change date by is:\t" + changeDateBy);
        LocalDate alteredDate = xavier.dateChanger(date, changeDateBy);
        System.out.println("The altered date is:\t" + alteredDate + "\n");

        //change the number of absences to length of name

        int xavierAbsences = xavier.getNumOfAbsences();
        System.out.println("The original number of absences is:\t" + xavierAbsences);
        int newAbsences = xavier.absenceChanger();
        System.out.println("The new number of absences is " + newAbsences + "\n");

        //use Record Objects
        ArrayList<Record> records = new ArrayList<>();
        Record testRecord = new Record();
        System.out.println("Here is the current Record class\t" + testRecord + "\n");
        records.add(testRecord);

        //Exchange name with third record
        String oldName = xavier.getName();
        System.out.println("the old name is " + oldName + "\n");
        xavier.setName("John");
        System.out.println("Here is the current Record with changed name\t" + xavier + "\n");


        Record personalRecord = new Record("Joshua", 5, LocalDate.of(1996, Month.OCTOBER, 7));
        System.out.println("my personal Record contains\t" + personalRecord + "\n");
        records.add(personalRecord);

        Record nonDefaultRecord = new Record("John", 20, LocalDate.of(2000, Month.JANUARY, 1));
        System.out.println("The non default Record contains\t" + nonDefaultRecord + "\n");
        records.add(nonDefaultRecord);

        xavier.setName(oldName);
        System.out.println("the non default Record with new name contains\t" + xavier + "\n");

        //name of Record with least num of absences
        ArrayList<String> leastAbsenceName = nameLeastNumOfAbsences(records);
        System.out.println("The record with the least num of absences is:\t" + leastAbsenceName + "\n");

        //name of the youngest Record
        ArrayList<String> youngestRecordName = nameYoungestRecord(records);
        System.out.println("The record with the youngest year is:\t" + youngestRecordName + "\n");

        //make the second record's num of absences the sum of the other two's num of absences
        System.out.println(records + "\n");
        System.out.println("The second Record's current num of absences is " + records.get(1).getNumOfAbsences() + "\n");
        int newNumAbsences = sumNumOfAbsences(records);
        personalRecord.setNumOfAbsences(newNumAbsences);
        System.out.println("The new sum of absences for the second record is:\t" + personalRecord.getNumOfAbsences() + "\n");

        //add one to the num of absences for every record that has a even num of absences
        System.out.println(records + "\n");
        int numOfAbsencesToAdd = 1;
        ArrayList<Record> recordsWithEvenAbsences = recordsWithEvenAbsences(records);
        System.out.println("The records with even absences contain:\t" + recordsWithEvenAbsences + "\n");
        ArrayList<Record> changedAbsences = addNumForEvenNumAbsences(recordsWithEvenAbsences, numOfAbsencesToAdd);
        System.out.println("when " + numOfAbsencesToAdd + " is added the records contain:\t" + changedAbsences + "\n");

        //create attendance class
        Attendance firstAttendance = new Attendance();
        System.out.println("Here is the current attendance class:\t" + firstAttendance + "\n");

        //create input file


    }

    private static File createInputFile() {
        File inputFile = new File("C://AttendanceApp//inputFile");
        try {

            //create a temp file
            File temp = File.createTempFile("tempfile", ".tmp");

        } catch (IOException e) {

            e.printStackTrace();

        }

        return inputFile;
    }

    private static ArrayList<Record> recordsWithEvenAbsences(ArrayList<Record> records) {
        ArrayList<Record> recordsWithEvenAbsences = new ArrayList<>();
        ArrayList<Integer> numOfAbsences = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            numOfAbsences.add(records.get(i).getNumOfAbsences());
        }
        for (int i = 0; i < records.size(); i++) {
            if (numOfAbsences.get(i) % 2 == 0) {
                recordsWithEvenAbsences.add(records.get(i));
            }
        }
        return recordsWithEvenAbsences;
    }

    private static ArrayList<Record> addNumForEvenNumAbsences(ArrayList<Record> recordsWithEvenAbsences, int numOfAbsencesToAdd) {
        int numToAdd = numOfAbsencesToAdd;
        ArrayList<Record> changedRecordsNumAbsences = new ArrayList<>();
        changedRecordsNumAbsences = recordsWithEvenAbsences;
        for (int i = 0; i < recordsWithEvenAbsences.size(); i++) {
            int absences = recordsWithEvenAbsences.get(i).getNumOfAbsences();
            changedRecordsNumAbsences.get(i).setNumOfAbsences(absences + numToAdd);
        }
        return changedRecordsNumAbsences;
    }

    private static int sumNumOfAbsences(ArrayList<Record> records) {
        int newNumOfAbsences = records.get(0).getNumOfAbsences() + records.get(2).getNumOfAbsences();
        return newNumOfAbsences;
    }

    private static ArrayList<String> nameYoungestRecord(ArrayList<Record> records) {
        ArrayList<String> answer = new ArrayList<>();
        LocalDate youngestYear = records.get(0).getLocalDate();
        for (int i = 0; i < records.size(); i++) {
            LocalDate year = records.get(i).getLocalDate();
            if (year.isBefore(youngestYear)) {
                youngestYear = year;
            }


        }
        for (int j = 0; j < records.size(); j++) {
            LocalDate year = records.get(j).getLocalDate();
            year = records.get(j).getLocalDate();
            if (year == youngestYear) {
                answer.add(records.get(j).getName());
            }
        }
        return answer;
    }

    private static ArrayList<String> nameLeastNumOfAbsences(ArrayList<Record> records) {
        ArrayList<String> answer = new ArrayList<>();
        int minAbsences = records.get(0).getNumOfAbsences();
        for (int i = 0; i < records.size(); i++) {
            int numOfAbsences = records.get(i).getNumOfAbsences();
            if (minAbsences > numOfAbsences) {
                minAbsences = numOfAbsences;
            }
        }
        for (int j = 0; j < records.size(); j++) {
            int numOfAbsences = records.get(0).getNumOfAbsences();
            if (minAbsences == numOfAbsences) {
                answer.add(records.get(j).getName());
            }
        }

        return answer;
    }


    private static Map<LocalDate, ArrayList<Integer>> absenceDate(ArrayList<LocalDate> dates, ArrayList<Integer> attended) {
        Map<LocalDate, ArrayList<Integer>> answer = new HashMap<>();

        for (int i = 0; i < dates.size(); i++) {

            if (answer.containsKey(dates.get(i))) {
                answer.get(dates.get(i)).add(i);
            } else {
                ArrayList<Integer> intial = new ArrayList<>();
                intial.add(i);
                answer.put(dates.get(i), intial);
            }
        }


        return answer;
    }

    private static Map<LocalDate, Integer> sameAbsenceDate(ArrayList<LocalDate> dates) {
        Map<LocalDate, Integer> answer = new HashMap<>();

        for (int i = 0; i < dates.size(); i++) {

            if (answer.containsKey(dates.get(i))) {
                answer.put(dates.get(i), answer.get(dates.get(i)) + 1);
            } else {
                answer.put(dates.get(i), 1);
            }
        }


        return answer;
    }

    private static ArrayList<Integer> dateIndexes(ArrayList<LocalDate> dates, LocalDate studentDate) {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i) == studentDate) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    private static LocalDate earliestDate(ArrayList<LocalDate> dates) {
        LocalDate latestDate = dates.get(0);
        for (int i = 0; i < dates.size(); i++) {
            if (latestDate.compareTo(dates.get(i)) > 0) {
                latestDate = dates.get(i);
            }

        }
        return latestDate;
    }


    private static long dateRange(LocalDate earliestDate, LocalDate latestDate) {
        long earliest = earliestDate.toEpochDay();
        long latest = latestDate.toEpochDay();
        long answer = latest - earliest;
        return answer;
    }


    private static LocalDate latestDate(ArrayList<LocalDate> dates) {
        LocalDate longestDate = dates.get(0);
        for (int i = 0; i < dates.size(); i++) {
            if (longestDate.compareTo(dates.get(i)) < 0) {
                longestDate = dates.get(i);
            }

        }
        return longestDate;
    }

    private static ArrayList<String> earliestDateName(ArrayList<String> names, LocalDate earliestDate, ArrayList<LocalDate> dates) {
        ArrayList<String> answer = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            if (earliestDate.equals(dates.get(i))) {
                answer.add(names.get(i));
            }
        }
        return answer;
    }

    private static void table(ArrayList<String> nameList, ArrayList<LocalDate> dates, ArrayList<Integer> attended) {
        System.out.println("The dates to go with names are \n Index \t\tName \t\tDate \t\tAbsences");
        for (int i = 0; i < nameList.size(); i = i + 1) {

            System.out.printf("%4d\t %10s \t %10s \t %5d\n", i, nameList.get(i), dates.get(i), attended.get(i));
        }
    }

    private static int minAbsenceNum(ArrayList<Integer> absences) {
        int i = 0;
        int minAbsences = absences.get(0);
        for (i = 0; i < absences.size(); i++) {
            if (minAbsences > absences.get(i)) {
                minAbsences = absences.get(i);
            }

        }
        return minAbsences;
    }

    private static ArrayList<String> minAbsenceNames(ArrayList<Integer> absences, ArrayList<String> nameList) {
        ArrayList<String> answer = new ArrayList<>();
        int minNum = minAbsenceNum(absences);
        for (int i = 0; i < absences.size(); i++) {
            if (minNum == absences.get(i)) {
                answer.add(nameList.get(i));
            }
        }
        return answer;
    }

    private static ArrayList<LocalDate> datesForNamesList(ArrayList<String> nameList, LocalDate currentDate) {
        Random random = new Random();
        ArrayList<LocalDate> answer = new ArrayList<>();
        for (int i = 0; i < nameList.size(); i++) {
            answer.add(currentDate.minusDays(random.nextLong() % dateRange));
        }
        return answer;
    }

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
