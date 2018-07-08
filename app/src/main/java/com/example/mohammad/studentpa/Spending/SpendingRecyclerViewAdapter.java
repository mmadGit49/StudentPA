package com.example.mohammad.studentpa.Spending;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.Entities.SpendingEntity;

import java.util.List;

public class SpendingRecyclerViewAdapter extends RecyclerView.Adapter<SpendingRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "SpendingRecycvAdapter";

    private List<SpendingEntity> spendingEntities;
    private Context context;

    public SpendingRecyclerViewAdapter(Context context, List<SpendingEntity> spendingEntities) {
        this.spendingEntities = spendingEntities;
        this.context = context;
    }

    @NonNull
    @Override
    public SpendingRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_spending_display, parent, false);
        SpendingRecyclerViewAdapter.ViewHolder holder= new SpendingRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");//log tag

        holder.textViewSpendDate.setText(spendingEntities.get(position).getSpendDate());
        holder.textViewSpendTotal.setText(spendingEntities.get(spendingEntities.size()-1).getSpendTotal());

        holder.spendingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On item click, start spending taker activity
                Intent spendIntent= new Intent(context, TakeSpendingItems.class);
                context.startActivity(spendIntent);

                Toast.makeText(context, "" + spendingEntities.size(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void setItems(List<SpendingEntity> spendingEntities){
        this.spendingEntities = spendingEntities;
        notifyDataSetChanged();
    }

    public SpendingEntity getSpendingAtPosition(int position) {
        return spendingEntities.get(position);
    }

    @Override
    public int getItemCount() {
        return spendingEntities.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewSpendDate;
        TextView textViewSpendTotal;
        LinearLayout spendingLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewSpendDate = itemView.findViewById(R.id.textViewSpendDate);
            textViewSpendTotal = itemView.findViewById(R.id.textViewSpendTotal);
            spendingLayout = itemView.findViewById(R.id.recycler_spending_display);
        }
    }
}
