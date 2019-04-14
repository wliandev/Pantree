package com.watermelons.pantree;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Food implements Serializable, Comparable<Food>{
    private String name;
    private Date dateBought;
    private long daysOld;

    public Food(String name){
        this.name = name;
        this.dateBought = Calendar.getInstance().getTime();
    }

    public void updateAge(){
        Date today = Calendar.getInstance().getTime();
        daysOld = Math.abs(today.getTime()-dateBought.getTime());
    }

    @Override
    public int compareTo(Food f){
        return (int) ((int)f.daysOld-this.daysOld);
    }

    public String getName() {
        return name;
    }

    public long getAge() {
        return daysOld;
    }
}