package com.techhousestudio.expensiveapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.techhousestudio.expensiveapp.Database.Expense;
import com.techhousestudio.expensiveapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerExpenseAdapter extends RecyclerView.Adapter<RecyclerExpenseAdapter.ExpenseViewHolder> {

    List<Expense> expenseList;


   /*public RecyclerExpenseAdapter() {
        super(DIFF_CALLBACK);
    }*/

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExpenseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {

        DateFormat sdf;

        sdf = new SimpleDateFormat("MM/dd/yy");
        String strDate = sdf.format(expenseList.get(position).dateTime);
       // String dateTime = format.format(expense.dateTime);
        holder.date.setText(strDate);
        holder.amount.setText(expenseList.get(position).expense_amount);
        holder.category.setText(expenseList.get(position).expense_category);

    }

    @Override
    public int getItemCount() {
        if (expenseList==null)
        {
            return 0;
        }
        else
            return expenseList.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {

        TextView category,date,amount;
        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            category=itemView.findViewById(R.id.tvexpensecategory);
            date=itemView.findViewById(R.id.tvexpenseDate);
            amount=itemView.findViewById(R.id.tvexpenseamount);
        }
    }

    /*public static final DiffUtil.ItemCallback<Expense> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Expense>() {
                @Override
                public boolean areItemsTheSame(Expense oldItem, Expense newItem) {
                    return oldItem.getEid() == newItem.getEid();
                }
                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Expense oldItem, Expense newItem) {
                    return  oldItem == newItem;
                }
            };*/
}