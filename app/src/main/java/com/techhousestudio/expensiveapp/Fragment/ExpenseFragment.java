package com.techhousestudio.expensiveapp.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.techhousestudio.expensiveapp.Adapter.RecyclerExpenseAdapter;
import com.techhousestudio.expensiveapp.Database.Expense;
import com.techhousestudio.expensiveapp.Database.ExpensiveViewModel;
import com.techhousestudio.expensiveapp.R;

import java.util.List;


public class ExpenseFragment extends Fragment implements View.OnClickListener {

    MaterialButton btnSave;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    ExpensiveViewModel expensiveViewModel;
    LiveData<List<Expense>> expense;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_menu:
               ExpenseDialogFragment expenseDialogFragment = new ExpenseDialogFragment();
               expenseDialogFragment.show(getFragmentManager(),"expense_dialog");

        }
        return true;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fab=view.findViewById(R.id.expense_floating);
        recyclerView=view.findViewById(R.id.expense_recycler);

        expensiveViewModel= ViewModelProviders.of(getActivity()).get(ExpensiveViewModel.class);
        expense=expensiveViewModel.getAllExpense();
        final RecyclerExpenseAdapter expenseAdapter=new RecyclerExpenseAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(expenseAdapter);
        fab.setOnClickListener(this);


        expense.observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(List<Expense> expenses) {
                expenseAdapter.setExpenseList(expenses);
            }
        });


    }

    @Override
    public void onClick(View view) {
        ExpenseDialogFragment expenseDialogFragment = new ExpenseDialogFragment();
        expenseDialogFragment.show(getFragmentManager(),"expense_dialog");

    }
}
