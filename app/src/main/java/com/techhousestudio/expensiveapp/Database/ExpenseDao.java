package com.techhousestudio.expensiveapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface  ExpenseDao {

    @Insert()
    void insertExpense(Expense expense);

    @Insert()
    void insertAllExpense(List<Expense> expenses);

    @Update()
    void updateExpense(Expense expense);

    @Delete()
    void deleteExpense(Expense expense);

    @Query("SELECT * from expense")
    LiveData<List<Expense>> getAllExpense();

    @Query("Select * From expense Where eid = :id")
    Expense findItemById(int id);

    @Query("Select eid from expense")
    int getExpenseId();

    @Query("Select expense_amount From expense")
    int getExpenseAmount();
}
