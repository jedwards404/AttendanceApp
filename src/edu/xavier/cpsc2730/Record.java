package edu.xavier.cpsc2730;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Created by joshua on 1/5/18.
 */
public class Record {
    private String name;
    private int numOfAbsences;
    private LocalDate date;

    public Record() {
        name = "joshua";
        numOfAbsences = 4;
        date = LocalDate.now();
    }

    public Record(String n, int numOfAbsences, LocalDate date) {
        name = n;
        this.numOfAbsences = numOfAbsences;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {

        return this.name + "\t" + this.date + "\t" + this.numOfAbsences;
    }

    public static LocalDate currentDate() {
        LocalDate answer = LocalDate.now();
        return answer;
    }

    //get num of absences
    public int getNumOfAbsences() {
        int answer = this.numOfAbsences;
        return answer;
    }


    public int absenceChanger() {
        this.numOfAbsences = 0;
        for (int i = 0; i < name.length(); i++) {
            numOfAbsences = numOfAbsences + 1;
        }
        return numOfAbsences;
    }

    public LocalDate dateChanger(LocalDate date, long changeDateBy) {
        LocalDate answer = date;
        answer = answer.minusDays(changeDateBy);
        return answer;
    }
}

