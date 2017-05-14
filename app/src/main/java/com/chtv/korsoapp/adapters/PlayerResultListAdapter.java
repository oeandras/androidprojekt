package com.chtv.korsoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chtv.korsoapp.Models.Player;
import com.chtv.korsoapp.Models.PlayerResult;
import com.chtv.korsoapp.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by cregz on 2017.05.14..
 */

public class PlayerResultListAdapter extends RecyclerView.Adapter<PlayerResultListAdapter.ViewHolder>{

    private List<PlayerResult> results;

    public PlayerResultListAdapter(List<PlayerResult> results){
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.playerresult_view, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        PlayerResult item = results.get(i);

        //Setting text view title
        viewHolder.nameTV.setText(item.getName());
        viewHolder.timeTV.setText((double)item.getTime().getTime() /1000 +"s");

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameTV;
        TextView timeTV;

        public ViewHolder(View itemView) {
            super(itemView);
            this.nameTV = (TextView) itemView.findViewById(R.id.player_name_textview);
            this.timeTV = (TextView) itemView.findViewById(R.id.time_textview);
        }
    }
}
