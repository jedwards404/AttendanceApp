package edu.xavier.cpsc2730;

import java.time.LocalDate;

/**
 * Created by joshua on 1/5/18.
 */
public class Record {
    private String name;
    private int numOfAbsences;
    private LocalDate date;

    @Override
    public String toString() {

        return this.name + "\t" + this.date + "\t" + this.numOfAbsences;
    }
}

