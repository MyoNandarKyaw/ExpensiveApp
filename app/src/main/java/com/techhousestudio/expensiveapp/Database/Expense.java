package com.techhousestudio.expensiveapp.Database;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "expense")
public class Expense {


    @PrimaryKey(autoGenerate = true)
    public int eid=0;

    public int expense_amount;
    public String expense_category;
    public String description;
    public Date dateTime;
    public int total_amount;

    public Expense(int eid, int expense_amount, String expense_category, String description, Date dateTime, int total_amount) {
        this.eid = eid;
        this.expense_amount = expense_amount;
        this.expense_category = expense_category;
        this.description = description;
        this.dateTime = dateTime;
        this.total_amount = total_amount;
    }

    @Ignore
    public Expense(int expense_amount, String expense_category) {
        this.expense_amount = expense_amount;
        this.expense_category = expense_category;
        this.dateTime=new Date();

    }

    @Ignore
    public Expense(int expense_amount, String expense_category, String description) {
        this.expense_amount = expense_amount;
        this.expense_category = expense_category;
        this.description = description;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    @Ignore
    public Expense(){}

}
