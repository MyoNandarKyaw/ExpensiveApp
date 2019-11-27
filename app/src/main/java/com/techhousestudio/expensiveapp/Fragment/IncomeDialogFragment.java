package com.techhousestudio.expensiveapp.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.techhousestudio.expensiveapp.AppExecutors;
import com.techhousestudio.expensiveapp.Database.Expense;
import com.techhousestudio.expensiveapp.Database.ExpensiveDatabase;
import com.techhousestudio.expensiveapp.Database.ExpensiveViewModel;
import com.techhousestudio.expensiveapp.Database.Income;
import com.techhousestudio.expensiveapp.R;


public class IncomeDialogFragment extends DialogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static String TAG = "FullScreenDialog";
    EditText etIncome,etDescription;
    AppCompatSpinner income_spinner;
    String selectItem = null;
    MaterialButton btnSave;
    private ExpensiveViewModel incomeViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income_dialog, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etIncome = view.findViewById(R.id.etincomeamount);
        etDescription=view.findViewById(R.id.etincomenote);
        income_spinner = view.findViewById(R.id.income_spinner);
        btnSave=view.findViewById(R.id.incomeSave);
        incomeViewModel= ViewModelProviders.of(getActivity()).get(ExpensiveViewModel.class);

        ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.income_arrays));
        spinneradapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
       income_spinner.setAdapter(spinneradapter);

       income_spinner.setOnItemSelectedListener(this);

        btnSave.setOnClickListener(this);
    }
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }


    @Override
    public void onClick(View view) {

        int amount=Integer.parseInt(etIncome.getText().toString());
        String description=etDescription.getText().toString();










        final Income income=new Income(amount,selectItem);
        //final ExpensiveDatabase expensiveDatabase=ExpensiveDatabase.getINSTANCE(getContext());
        AppExecutors appExecutors=new AppExecutors();

        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                incomeViewModel.insertIncome(income);
            }
        });

        Toast.makeText(getActivity(),"Save",Toast.LENGTH_LONG).show();
        

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectItem=adapterView.getItemAtPosition(i).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
