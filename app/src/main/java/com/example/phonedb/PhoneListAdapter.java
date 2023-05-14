package com.example.phonedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneListAdapter extends
        RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder> {
    private List<Phone> mPhoneList;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListener mOnItemClickListener;
    interface OnItemClickListener
    {
        void onItemClickListener(Phone element);
    }
    public PhoneListAdapter(Context context) {
        mOnItemClickListener = (OnItemClickListener)context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mPhoneList = null;
    }

    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_line = mLayoutInflater.inflate(R.layout.phone_vh,parent,false);
        return new PhoneViewHolder(view_line);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        holder.textViewProducent.setText(mPhoneList.get(position).getProducent());
        holder.textViewModel.setText(mPhoneList.get(position).getModel());
    }

    @Override
    public int getItemCount() {
        if (mPhoneList != null)
            return mPhoneList.size();
        return 0;
    }
    public void setPhoneList(List<Phone> elementList) {
        this.mPhoneList = elementList;
        notifyDataSetChanged();
    }
    public class PhoneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView textViewProducent;
        public TextView textViewModel;

        int ocena;
        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProducent = itemView.findViewById(R.id.textViewProducent);
            textViewModel = itemView.findViewById(R.id.textViewModel);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            mOnItemClickListener.onItemClickListener(mPhoneList.get(position));
        }
    }
}
