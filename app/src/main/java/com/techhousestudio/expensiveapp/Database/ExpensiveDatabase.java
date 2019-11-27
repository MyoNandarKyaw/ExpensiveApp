package com.techhousestudio.expensiveapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {Expense.class,Income.class}, version = 2,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ExpensiveDatabase extends RoomDatabase {


    public abstract ExpenseDao expenseDao();
    public abstract IncomeDao incomeDao();
    private static ExpensiveDatabase INSTANCE;

    public static ExpensiveDatabase getINSTANCE(Context context) {

        if (INSTANCE == null) {
            synchronized (ExpensiveDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ExpensiveDatabase.class, "note_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
