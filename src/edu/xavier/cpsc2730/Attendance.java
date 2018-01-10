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

    //get name
    public ArrayList<String> getName() {
        ArrayList<String> answer = new ArrayList<>();
        answer = this.getName();
        return answer;
    }

    public ArrayList<Integer> absencesMoreThan() {
        ArrayList<Integer> absencesCalcHolder = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getNumOfAbsences() < this.FE) {
                absencesCalcHolder.add(records.get(i).getNumOfAbsences());
            }
        }
        return absencesCalcHolder;
    }

}

