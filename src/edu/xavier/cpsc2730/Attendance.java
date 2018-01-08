package edu.xavier.cpsc2730;

/**
 * Created by joshua on 1/7/18.
 */

import java.time.LocalDate;
import java.util.ArrayList;

public class Attendance {
    private ArrayList<Record> records;

    public Attendance() {
        records = records;
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
    public ArrayList<Integer> getNumOfAbsences(ArrayList<Record> records) {
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            int numOfAbsences = records.get(i).getNumOfAbsences();
            answer.add(numOfAbsences);
        }
        return answer;
    }

}

