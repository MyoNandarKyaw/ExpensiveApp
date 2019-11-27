package com.techhousestudio.expensiveapp.Database;

import android.support.v4.app.INotificationSideChannel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IncomeDao {

    @Query("SELECT * from income")
    LiveData<List<Income>>  getallIncome();

    @Query("Select * From income Where id = :id")
    Income findItemById(int id);

    @Query("Select id from income")
    int getIncomeId();


    @Delete()
    void deleteIncome(Income income);

    @Update()
    void updateIncome(Income income);

    @Insert()
    void insertIncome(Income income);

    @Insert()
    void insertAllIncome(List<Income> incomes);

    @Query("Select income_amount From income")
    int getIncomeAmount();



}
