package com.techhousestudio.expensiveapp.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpensiveViewModel extends AndroidViewModel {
    private ExpenseDao expenseDao;
    private IncomeDao incomeDao;
    private UserDao userDao;
    public ExpensiveDatabase expensivedatabase;
    public LiveData<List<Expense>> expense_list;
    public LiveData<List<Income>> income_list;

    public ExpensiveViewModel(@NonNull Application application) {
        super(application);
        expensivedatabase=ExpensiveDatabase.getINSTANCE(getApplication());
        expenseDao=expensivedatabase.expenseDao();
        incomeDao=expensivedatabase.incomeDao();
    }

    public void insertExpense(Expense expense){
        expenseDao.insertExpense(expense);
    }

    public void insertIncome(Income income){
        incomeDao.insertIncome(income);
    }

    public LiveData<List<Income>> getAllIncome(){
        income_list=incomeDao.getallIncome();
        return income_list;
    }

    public LiveData<List<Expense>> getAllExpense(){
        expense_list=expenseDao.getAllExpense();
        return expense_list;
    }

   public Expense findByExpneseId(int id){
        return expenseDao.findItemById(id);
    }

    public Income findByIncomeId(int id){
        return incomeDao.findItemById(id);
    }

   /* public int getIncomeId(){
        return incomeDao.getIncomeId();
    }

    public int getExpenseId(){
        return expenseDao.getExpenseId();
    }*/

    public void insertAllUser(User user){
        userDao.insertUser(user);
    }

    public int getIncomeAmount(){
        return incomeDao.getIncomeAmount();
    }

    public int getExpenseAmount(){return  expenseDao.getExpenseAmount();}

}
