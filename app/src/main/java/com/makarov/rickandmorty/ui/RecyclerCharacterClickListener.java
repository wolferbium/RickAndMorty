package com.makarov.rickandmorty.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.View;

public class RecyclerCharacterClickListener {

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListner;

    public RecyclerCharacterClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        onItemClickListner = listener;

    }
}
