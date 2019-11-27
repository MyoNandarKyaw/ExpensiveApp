package com.techhousestudio.expensiveapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techhousestudio.expensiveapp.Database.Income;
import com.techhousestudio.expensiveapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class RecyclerIncomeAdapter extends RecyclerView.Adapter<RecyclerIncomeAdapter.IncomeViewHolder>{

    List<Income> incomeList;


    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IncomeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_income,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeViewHolder holder, int position) {

       /* DateFormat sdf;
        sdf = new SimpleDateFormat("MM/dd/yy");
        String strDate = sdf.format(incomeList.get(position).date);
        holder.tvamount.setText(incomeList.get(position).income_amount);
        holder.tvcategory.setText(incomeList.get(position).income_category);
        holder.tvdate.setText(strDate);*/

    }

    @Override
    public int getItemCount() {
        if(incomeList!=null){

            return incomeList.size();
        }
        else
            return 0;

    }


    public class IncomeViewHolder extends RecyclerView.ViewHolder {
        TextView tvcategory,tvdate,tvamount;
        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvamount=itemView.findViewById(R.id.tvincomeamount);
            tvcategory=itemView.findViewById(R.id.tvincomecategory);
            tvdate=itemView.findViewById(R.id.tvincomeDate);
        }
    }
}
