package com.techhousestudio.expensiveapp.Database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "income")
public class Income {

  @PrimaryKey(autoGenerate = true)
   private   int id;

    public int income_amount;
    public String  income_category;
    public Date date;
    public int total_amount;

    public Income(int id, int income_amount, String income_category, Date month, int total_amount) {
        this.id = id;
        this.income_amount = income_amount;
        this.income_category = income_category;
        this.date= month;
        this.total_amount = total_amount;
    }


    @Ignore
    public Income(int income_amount, String income_category, int total_amount) {
        this.income_amount = income_amount;
        this.income_category = income_category;
        this.date = new Date();
        this.total_amount = total_amount;
    }

    @Ignore
    public Income(int income_amount, String income_category) {
        this.income_amount = income_amount;
        this.income_category = income_category;
        this.date=new Date();
    }


   @Ignore
    public Income(int total_amount) {
        this.total_amount = total_amount;
    }

    public Income() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
