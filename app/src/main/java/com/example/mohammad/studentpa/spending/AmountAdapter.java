package com.example.mohammad.studentpa.spending;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mohammad.studentpa.R;
import com.example.mohammad.studentpa.db_classes.entities.SpendingEntity;

import java.util.List;

public class AmountAdapter extends  RecyclerView.Adapter<AmountAdapter.ViewHolder> {
    private Context context;
    private List<SpendingEntity> spendingEntities;

    private static final String TAG = "AmountAdapter";
    AmountAdapter(Context context, List<SpendingEntity> spendingEntities) {
        this.context = context;
        this.spendingEntities = spendingEntities;
    }

    @NonNull
    @Override
    public AmountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spending_listview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmountAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolderRemind: called.");//log tag
        if (spendingEntities != null) {
            holder.textViewDate.setText(spendingEntities.get(position).getSpendDate());
            String amount = Double.toString(spendingEntities.get(position).getSpendAmount());
            holder.textViewAmt.setText(amount);
            holder.textViewDet.setText(spendingEntities.get(position).getSpendDetails());
        } else {
            //If data is not ready yet
            holder.textViewDet.setText(R.string.null_notes);
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewAmt;
        TextView textViewDet;
        TextView textViewDate;
        LinearLayout scheduleLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewSpendDateView);
            textViewAmt = itemView.findViewById(R.id.txtViewSpendAmount);
            textViewDet = itemView.findViewById(R.id.txtViewSpendDetails);
            scheduleLayout = itemView.findViewById(R.id.recycler_view_amounts);
        }
    }
}

