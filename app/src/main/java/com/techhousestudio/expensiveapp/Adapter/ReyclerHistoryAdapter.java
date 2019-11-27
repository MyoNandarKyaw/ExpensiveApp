package com.techhousestudio.expensiveapp.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techhousestudio.expensiveapp.Database.User;
import com.techhousestudio.expensiveapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class ReyclerHistoryAdapter extends RecyclerView.Adapter<ReyclerHistoryAdapter.HistoryHolder>{

    List<User> userList;


    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new HistoryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_history_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        holder.tvAmount.setText(userList.get(position).total_amount);
        holder.tvCategory.setText(userList.get(position).history_category);

        DateFormat sdf;

         sdf = new SimpleDateFormat("MM/dd/yy");
         String strDate = sdf.format(userList.get(position).date);
         holder.tvDate.setText(strDate);

    }

    @Override
    public int getItemCount() {
       if(userList==null){
           return 0;
       }
       else
           return userList.size();
    }

   public class HistoryHolder extends RecyclerView.ViewHolder{

        TextView tvCategory,tvAmount,tvDate;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            tvCategory=itemView.findViewById(R.id.historty_category);
            tvAmount=itemView.findViewById(R.id.history_amount);
            tvDate=itemView.findViewById(R.id.history_date);

        }
    }
}
