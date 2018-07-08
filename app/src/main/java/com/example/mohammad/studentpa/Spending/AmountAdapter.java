package com.example.mohammad.studentpa.Spending;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.Entities.SpendingEntity;

import java.util.List;

public class AmountAdapter extends  RecyclerView.Adapter<AmountAdapter.ViewHolder> {
    private Context context;
    private List<SpendingEntity> spendingEntities;

    private static final String TAG = "AmountAdapter";
    public AmountAdapter(Context context, List<SpendingEntity> spendingEntities) {
        this.context = context;
        this.spendingEntities = spendingEntities;
    }

    @NonNull
    @Override
    public AmountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(this.context)
                .inflate(R.layout.spending_listview, parent, false);
        AmountAdapter.ViewHolder holder= new AmountAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AmountAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolderRemind: called.");//log tag

        if (spendingEntities != null) {
            holder.textViewAmt.setText(spendingEntities.get(position).getSpendAmount().toString());
            holder.textViewDet.setText(spendingEntities.get(position).getSpendDetails().toString());
        } else {
            //If data is not ready yet
            holder.textViewDet.setText("No items");
        }
    }

    @Override
    public int getItemCount() {
        return spendingEntities.size();
    }

    public void setItems(List<SpendingEntity> spendingEntities){
        this.spendingEntities = spendingEntities;
        notifyDataSetChanged();
    }

    public SpendingEntity getSpendingAtPosition(int position) {
        return spendingEntities.get(position);
    }

    public List<SpendingEntity> getSpendingEntities() {
        return spendingEntities;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewAmt;
        TextView textViewDet;
        RelativeLayout scheduleLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewAmt = itemView.findViewById(R.id.txtViewSpendAmount);
            textViewDet = itemView.findViewById(R.id.txtViewSpendDetails);
            scheduleLayout = itemView.findViewById(R.id.recycler_view_amounts);
        }
    }
}

