package com.techhousestudio.expensiveapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.techhousestudio.expensiveapp.Adapter.RecyclerIncomeAdapter;
import com.techhousestudio.expensiveapp.AppExecutors;
import com.techhousestudio.expensiveapp.Database.Expense;
import com.techhousestudio.expensiveapp.Database.ExpensiveViewModel;
import com.techhousestudio.expensiveapp.Database.Income;
import com.techhousestudio.expensiveapp.R;

import java.util.List;


public class IncomeFragment extends Fragment implements View.OnClickListener {

    FloatingActionButton fab;
    RecyclerView recyclerView;
    ExpensiveViewModel incomeViewModel;
    LiveData<List<Income>> income;
    static int total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab=view.findViewById(R.id.income_floating);
        incomeViewModel= ViewModelProviders.of(this).get(ExpensiveViewModel.class);
        income=incomeViewModel.getAllIncome();

        AppExecutors executors=new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                int amount=incomeViewModel.getIncomeAmount();
                total+=amount;
                final Income incomes=new Income(total);
                incomeViewModel.insertIncome(incomes);

            }
        });













        fab.setOnClickListener(this);
        recyclerView=view.findViewById(R.id.income_recycler);
        final RecyclerIncomeAdapter incomeAdapter=new RecyclerIncomeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(incomeAdapter);


        income.observe(this, new Observer<List<Income>>() {
            @Override
            public void onChanged(List<Income> incomes) {
                incomeAdapter.setIncomeList(incomes);

            }
        });

    }

    @Override
    public void onClick(View view) {
        IncomeDialogFragment incomeDialogFragment=new IncomeDialogFragment();
        incomeDialogFragment.show(getFragmentManager(),"income_dialog");
    }
}
