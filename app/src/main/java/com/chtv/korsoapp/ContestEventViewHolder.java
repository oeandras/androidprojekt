package com.chtv.korsoapp;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.databinding.ContesteventViewBinding;

/**
 * Created by cregz on 2017.04.25..
 */

public class ContestEventViewHolder extends RecyclerView.ViewHolder {
    private final ContesteventViewBinding binding;

    public ContestEventViewHolder(ContesteventViewBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }

    public void bind(ContestEvent contestEvent){
        binding.setContestEvent(contestEvent);
        binding.executePendingBindings();
    }
}
