package com.chtv.korsoapp;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.databinding.ContesteventViewBinding;

import java.util.List;

/**
 * Created by cregz on 2017.04.25..
 */

public class ContestEventAdapter extends RecyclerView.Adapter<ContestEventViewHolder> {
    ObservableList<ContestEvent> contestEvents;

    ObservableList.OnListChangedCallback<ObservableList<ContestEvent>> callback = new ObservableList.OnListChangedCallback<ObservableList<ContestEvent>>() {
        @Override
        public void onChanged(ObservableList<ContestEvent> contestEvents) {
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(ObservableList<ContestEvent> contestEvents, int i, int i1) {
            notifyItemRangeChanged(i, i1);
        }

        @Override
        public void onItemRangeInserted(ObservableList<ContestEvent> contestEvents, int i, int i1) {
            notifyItemRangeInserted(i, i1);
        }

        @Override
        public void onItemRangeMoved(ObservableList<ContestEvent> contestEvents, int i, int i1, int i2) {
            notifyItemRangeRemoved(i, i2);
            notifyItemRangeRemoved(i1, i2);
        }

        @Override
        public void onItemRangeRemoved(ObservableList<ContestEvent> contestEvents, int i, int i1) {
            notifyItemRangeRemoved(i, i1);
        }
    };
    
    
    @Override
    public ContestEventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ContesteventViewBinding contestBinding = ContesteventViewBinding.inflate(inflater, viewGroup, false);
        return new ContestEventViewHolder(contestBinding);
    }

    @Override
    public void onBindViewHolder(ContestEventViewHolder contestEventViewHolder, int i) {
        ContestEvent item = getItemForPosition(i);
        contestEventViewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return contestEvents== null ? 0 : contestEvents.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private ContestEvent getItemForPosition(int position) {
        return contestEvents== null ? null : contestEvents.get(position);
    }

    public ContestEventAdapter(ObservableList<ContestEvent> contestEvents) {
        this.contestEvents = contestEvents;
        contestEvents.addOnListChangedCallback(callback);
    }
}
