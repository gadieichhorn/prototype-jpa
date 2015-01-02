/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rds.prototype.data.jpa;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Gadi
 */
@Embeddable
public class Stamp implements Serializable {

    @Basic()
    @Column(name = "stamp_milliseconds", nullable = false)
    private long milliseconds = System.currentTimeMillis();

    @Basic()
    @Column(name = "stamp_year", nullable = false)
    private int year;

    public Stamp(int year) {
        this.year = year;
    }

    public Stamp() {
        Calendar c = Calendar.getInstance();
        //Set time in milliseconds
        c.setTimeInMillis(milliseconds);
        this.year = c.get(Calendar.YEAR);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

}
