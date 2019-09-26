package com.farhanshahoriar.costmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransListAdapter extends RecyclerView.Adapter<TransListAdapter.TransViewHolder> {

    public ArrayList<TransInfo> transInfos = null;
    public Context mContext;
    public TransListAdapter(Context context){
        mContext = context;
    }

    @Override
    public void onBindViewHolder( TransViewHolder transViewHolder, int pos) {
        transViewHolder.dateTextView.setText(transInfos.get(pos).getDate());
        transViewHolder.descripTextView.setText(transInfos.get(pos).getDescrip());

        transViewHolder.amoutTextView.setText(Integer.toString(transInfos.get(pos).getAmount()));
        transViewHolder.userTextView.setText(transInfos.get(pos).getUsername());
    }

    @Override
    public int getItemCount() {
        if(transInfos == null)return 0;
        else return transInfos.size();
    }

    public class TransViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView dateTextView;
        TextView descripTextView;
        TextView amoutTextView;
        TextView userTextView;

        public TransViewHolder(View view){
            super(view);

            dateTextView =  view.findViewById(R.id.tv_tran_date);
            descripTextView = view.findViewById(R.id.tv_tran_info);
            amoutTextView = view.findViewById(R.id.tv_amount);
            userTextView = view.findViewById(R.id.tv_user);
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            int pos = getAdapterPosition();
            //Toast.makeText(mContext,"clicked "+pos,Toast.LENGTH_SHORT).show();

            return false;
        }

    }
    @Override
    public TransViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.trans_item, viewGroup,false);
        return new TransViewHolder(view);
    }

    public void setData(ArrayList<TransInfo> infolist){
        transInfos = infolist;
        notifyDataSetChanged();
    }
}

