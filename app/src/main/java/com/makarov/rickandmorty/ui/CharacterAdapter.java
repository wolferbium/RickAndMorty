package com.makarov.rickandmorty.ui;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makarov.rickandmorty.app.MainActivity;
import com.makarov.rickandmorty.R;
import com.makarov.rickandmorty.model.CharacterModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public OnCharacterClick onCharacterClick;
    public MainActivity activity;

    List<CharacterModel> characters = new ArrayList<>();

    public void registerOnCharacterClickCallback(OnCharacterClick onCharacterClick) {
        this.onCharacterClick = onCharacterClick;
    }

    public CharacterAdapter(MainActivity activity) {
        this.activity = activity;
    }

    public void setData(Collection<CharacterModel> characters) {
        this.characters.addAll(characters);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    class CharacterViewHolder extends BaseViewHolder {
        private ImageView image;
        private TextView name;
        private TextView species;

        private ConstraintLayout constraintLayout;

        public CharacterViewHolder(View characterView) {
            super(characterView);
            image = characterView.findViewById(R.id.character_image);
            name = characterView.findViewById(R.id.character_name);
            species = characterView.findViewById(R.id.character_species);
            constraintLayout = characterView.findViewById(R.id.item_character);
        }

        public void onBind(int position) {
            CharacterModel character = characters.get(position);
            name.setText(character.getName());
            species.setText(character.getSpecies());
            Glide.with(image).load(character.getImage()).into(image);

            constraintLayout.setOnClickListener( view -> {
                if (getAdapterPosition() != -1) {
                    onCharacterClick.onCharacterClick(characters.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnCharacterClick {
        void onCharacterClick(CharacterModel character);
    }
}
