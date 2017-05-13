package com.chtv.korsoapp;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.ViewModels.EventListViewModel;
import com.chtv.korsoapp.databinding.ContesteventViewBinding;

/**
 * Created by cregz on 2017.04.25..
 */

public class ContestEventViewHolder extends RecyclerView.ViewHolder {
    private final ContesteventViewBinding binding;
    private final EventListViewModel viewModel;

    public ContestEventViewHolder(ContesteventViewBinding binding, EventListViewModel viewModel) {
        super(binding.getRoot());
        this.binding=binding;
        this.viewModel = viewModel;
        binding.setViewModel(viewModel);

    }

    public void bind(ContestEvent contestEvent){
        binding.setContestEvent(contestEvent);
        binding.executePendingBindings();
    }

}
