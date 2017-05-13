package com.chtv.korsoapp;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.ViewModels.EventListViewModel;
import com.chtv.korsoapp.databinding.ContesteventViewBinding;

import java.util.List;

import io.realm.RealmCollection;
import io.realm.RealmList;

/**
 * Created by cregz on 2017.04.25..
 */

public class ContestEventAdapter extends RecyclerView.Adapter<ContestEventViewHolder> {
    private final EventListViewModel viewModel;
    RealmList<ContestEvent> contestEvents;
    
    @Override
    public ContestEventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ContesteventViewBinding contestBinding = ContesteventViewBinding.inflate(inflater, viewGroup, false);
        return new ContestEventViewHolder(contestBinding, viewModel);
    }

    @Override
    public void onBindViewHolder(final ContestEventViewHolder contestEventViewHolder, int i) {
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

    public ContestEventAdapter(RealmList<ContestEvent> contestEvents, EventListViewModel viewModel) {
        this.contestEvents = contestEvents;
        this.viewModel = viewModel;
    }
}
