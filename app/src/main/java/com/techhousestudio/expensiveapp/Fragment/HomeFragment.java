package com.techhousestudio.expensiveapp.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.techhousestudio.expensiveapp.AppExecutors;
import com.techhousestudio.expensiveapp.Database.Expense;
import com.techhousestudio.expensiveapp.Database.ExpensiveViewModel;
import com.techhousestudio.expensiveapp.Database.Income;
import com.techhousestudio.expensiveapp.Database.User;
import com.techhousestudio.expensiveapp.R;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Templates;


public class HomeFragment extends Fragment {
    Toolbar toolbar;
    PieChart pieChart;
    ExpensiveViewModel viewModel;
    LiveData<List<Expense>> expense_list;
    LiveData<List<Income>> income_list;
    Income income;
    Expense expense;
    int income_id,expense_id,income_amount,expense_amount;
    static int remaining_amount=0;
    TextView tvincome,tvexpense,tvremaining;

   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pieChart=view.findViewById(R.id.pie_chart);
        tvincome=view.findViewById(R.id.income_amount);
        tvexpense=view.findViewById(R.id.expense_amount);
        tvremaining=view.findViewById(R.id.remaining);
        viewModel= ViewModelProviders.of(this).get(ExpensiveViewModel.class);

        expense_list=viewModel.getAllExpense();
        income_list=viewModel.getAllIncome();

       /* income_id=viewModel.getIncomeId();
        expense_id=viewModel.getExpenseId();

        income=viewModel.findByIncomeId(income_id);
        expense=viewModel.findByExpneseId(expense_id);*/





        AppExecutors executors=new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                income_amount=viewModel.getIncomeAmount();
                expense_amount=viewModel.getExpenseAmount();
                /*remaining_amount=income_amount-expense_amount;

                 User user=new User(remaining_amount);

                viewModel.insertAllUser(user);*/
            }
        });
        Toast.makeText(getActivity(),"Success",Toast.LENGTH_LONG).show();

        tvincome.setText(income_amount+"");
        tvexpense.setText(expense_amount+"");
        tvremaining.setText(remaining_amount+"");



        //Pie Chart
        List<PieEntry> incomes=new ArrayList<>();
        incomes.add(new PieEntry(200,"Jan"));
        incomes.add(new PieEntry(300,"Feb"));

        PieDataSet dataSet=new PieDataSet(incomes,"Months");
        PieData pieData=new PieData(dataSet);

        pieChart.setData(pieData);

        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setSliceSpace(2f);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(10f);
        dataSet.setSliceSpace(5f);


    }
}
