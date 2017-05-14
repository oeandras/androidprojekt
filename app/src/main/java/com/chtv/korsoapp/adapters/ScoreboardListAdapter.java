package com.chtv.korsoapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chtv.korsoapp.Models.Scoreboard;
import com.chtv.korsoapp.R;
import com.chtv.korsoapp.events.ScoreBoardSelected;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by cregz on 2017.05.14..
 */

public class ScoreboardListAdapter extends RecyclerView.Adapter<ScoreboardListAdapter.ViewHolder> {

    private List<Scoreboard> scoreboards;

    public ScoreboardListAdapter(List<Scoreboard> scoreboards){
        this.scoreboards = scoreboards;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scoreboard_view, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        Scoreboard item = scoreboards.get(i);

        //Setting text view title
        viewHolder.scoreboardName.setText(item.getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition();
                EventBus.getDefault().post(new ScoreBoardSelected((scoreboards.get(pos))));
            }
        });
    }

    @Override
    public int getItemCount() {
        return scoreboards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView scoreboardName;

        public ViewHolder(View itemView) {
            super(itemView);
            this.scoreboardName = (TextView) itemView.findViewById(R.id.scoreboard_name_textview);
        }
    }
}
