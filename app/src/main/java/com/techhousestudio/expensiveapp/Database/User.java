package com.techhousestudio.expensiveapp.Database;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.util.Date;

@Entity(tableName = "user_repo_join",
        primaryKeys = { "userId", "repoId" },
        foreignKeys = {
                @ForeignKey(entity = Income.class,
                        parentColumns = "id",
                        childColumns = "incomeId"),
                @ForeignKey(entity = Expense.class,
                        parentColumns = "eid",
                        childColumns = "expenseId")
        })

public class User {
    public int incomeId;
    public int expenseId;
    public int total_amount;
    public Date date;
    public String history_category;

    public User(int incomeId, int expenseId, int total_amount, Date date, String history_category) {
        this.incomeId = incomeId;
        this.expenseId = expenseId;
        this.total_amount = total_amount;
        this.date = date;
        this.history_category = history_category;
    }

    @Ignore
    public User(int total_amount)
    {
        this.total_amount = total_amount;
        //date=new Date();
    }

    @Ignore
    public User(String history_category) {
        this.history_category = history_category;
    }
}
