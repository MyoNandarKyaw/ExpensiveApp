package com.techhousestudio.expensiveapp.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.DialogFragment;
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
import com.techhousestudio.expensiveapp.Database.User;
import com.techhousestudio.expensiveapp.R;


public class ExpenseDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    public static String TAG = "FullScreenDialog";
    EditText etExpense,etDescription;
    AppCompatSpinner expense_spinner;
    String selectItem = null;
    MaterialButton btnSave;
    private ExpensiveViewModel expensiveViewModel;
    // private String[] expense_array={"Drinks,Clothing ,Food ,Health ,Education ,Restaurant ,Transport,Fun, Fuel,Others"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_dialog, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etExpense = view.findViewById(R.id.etexpenseamount);
        etDescription=view.findViewById(R.id.etexpensenote);
        expense_spinner = view.findViewById(R.id.expese_spinner);
        btnSave=view.findViewById(R.id.expnseSave);
        expensiveViewModel= ViewModelProviders.of(getActivity()).get(ExpensiveViewModel.class);

        ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.expense_arrays));
        spinneradapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        expense_spinner.setAdapter(spinneradapter);

        expense_spinner.setOnItemSelectedListener(this);

        btnSave.setOnClickListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectItem=adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getActivity(),"Select positon"+selectItem,Toast.LENGTH_LONG).show();

        }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onClick(View view) {
        int amount=Integer.parseInt(etExpense.getText().toString());
        String description=etDescription.getText().toString();
        final Expense expense=new Expense(amount,selectItem,description);
        final User user=new User(selectItem);
       // final ExpensiveDatabase expensiveDatabase=ExpensiveDatabase.getINSTANCE(getContext());
        AppExecutors appExecutors=new AppExecutors();
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                expensiveViewModel.insertExpense(expense);
            }
        });
        Toast.makeText(getContext(),"Save",Toast.LENGTH_LONG).show();



    }
}


