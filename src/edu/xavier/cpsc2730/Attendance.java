package edu.xavier.cpsc2730;

/**
 * Created by joshua on 1/7/18.
 */

import java.time.LocalDate;
import java.util.ArrayList;

public class Attendance {
    private ArrayList<Record> records = new ArrayList<>();
    int FE = 7;

    public Attendance() {

        ArrayList<Record> records = new ArrayList<>();
    }

    public Attendance(ArrayList<Record> records) {
        this.records = records;

    }


    @Override
    public String toString() {

        return this.records + "\t";
    }

    public static LocalDate currentDate() {
        LocalDate answer = LocalDate.now();
        return answer;
    }

    //get num of absences
    public ArrayList<Integer> getNumOfAbsences() {
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < this.records.size(); i++) {
            int numOfAbsences = this.records.get(i).getNumOfAbsences();
            answer.add(numOfAbsences);
        }
        return answer;
    }

    //get names
    public ArrayList<String> getNames() {
        ArrayList<String> answer = new ArrayList<>();
        for (int i = 0; i < this.records.size(); i++) {
            String studentName;
            studentName = this.records.get(i).getName();
            answer.add(studentName);
        }
        return answer;
    }

    //get Dates
    public ArrayList<LocalDate> getDate() {
        ArrayList<LocalDate> answer = new ArrayList<>();
        for (int i = 0; i < this.records.size(); i++) {
            answer.add(this.records.get(i).getLocalDate());
        }
        return answer;
    }

    public ArrayList<Integer> absencesMoreThan(int X) {
        ArrayList<Integer> absences = new ArrayList<>();
        absences = getNumOfAbsences();
        ArrayList<Integer> absencesCalcHolder = new ArrayList<>();
        for (int i = 0; i < this.records.size(); i++) {
            if (absences.get(i) < X) {
                absencesCalcHolder.add(absences.get(i));
            }
        }
        return absencesCalcHolder;
    }

    public ArrayList<String> studentsMoreThanXNumAbsences(int X) {
        ArrayList<String> answer = new ArrayList<>();
        for (int i = 0; i < this.records.size(); i++) {
            String studentName = this.records.get(i).getName();
            int absence = this.records.get(i).getNumOfAbsences();
            if (absence > X) {
                answer.add(studentName);
            }
        }
        return answer;
    }

    public ArrayList<String> studentsWhoFEd() {
        ArrayList<String> answer = new ArrayList<>();
        for (int i = 0; i < this.records.size(); i++) {
            int absences = this.records.get(i).getNumOfAbsences();
            if (absences >= FE) {
                answer.add(this.records.get(i).getName());
            }
        }
        return answer;
    }

    public ArrayList<Integer> indexPerfectAttendees() {
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < this.records.size(); i++) {
            int absence = this.records.get(i).getNumOfAbsences();
            if (absence == 0) {
                answer.add(i);
            }
        }
        return answer;
    }

    public double percentPerfectAttendees() {
        int numPerfAttendees = 0;
        int numOfStudents = this.records.size();
        double answer = 0;
        for (int i = 0; i < numOfStudents; i++) {
            int absence = this.records.get(i).getNumOfAbsences();
            if (absence == 0) {
                numPerfAttendees = numPerfAttendees + 1;
            }
        }
        if (numPerfAttendees > 0) {
            answer = (numOfStudents / numPerfAttendees) * 100;
        } else {
            System.out.println("no students with perfect attendance");
        }
        return answer;
    }

    public ArrayList<String> dateRangeComparison(long XDaysRange, LocalDate comparisonDate) {
        ArrayList<String> answer = new ArrayList<>();
        ArrayList<LocalDate> dates = new ArrayList<>();
        for (int i = 0; i < this.records.size(); i++) {
            dates.add(this.records.get(i).getLocalDate());
        }
        for (int i = 0; i < this.records.size(); i++) {
            LocalDate studentAbsenceDate = dates.get(i);
            if (studentAbsenceDate.isAfter(comparisonDate.minusDays(XDaysRange)) && studentAbsenceDate.isBefore(comparisonDate.plusDays(XDaysRange))) {
                answer.add(this.records.get(i).getName());
            }
        }
        return answer;
    }

}

