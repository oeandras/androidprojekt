package com.chtv.korsoapp.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.R;

import java.util.List;


/**
 * Created by cregz on 2017.05.14..
 */

public class SessionListAdapter extends RecyclerView.Adapter <SessionListAdapter.ViewHolder>{

    private List<ContestSession> sessions;

    public SessionListAdapter(List<ContestSession> sessions){
        this.sessions = sessions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contestsession_view, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ContestSession item = sessions.get(i);

        //Setting text view title
        viewHolder.sessionNameTV.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView sessionNameTV;

        public ViewHolder(View itemView) {
            super(itemView);
            this.sessionNameTV = (TextView) itemView.findViewById(R.id.session_name_textview);
        }
    }
}
