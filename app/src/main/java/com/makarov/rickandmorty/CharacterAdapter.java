package com.makarov.rickandmorty;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<CharacterModel> characterList = new ArrayList<>();

    public void setCharacterList(Collection<CharacterModel> characters){
        characterList.addAll(characters);
        notifyDataSetChanged();
    }

    public void clearCharacterList(){
        characterList.clear();
        notifyDataSetChanged();
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.bind(characterList.get(position));
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatar;
        private TextView name;
        private TextView type;

        public CharacterViewHolder(View characterView) {
            super(characterView);
            //avatar = characterView.findViewById(R.id.character_avatar);
            name = characterView.findViewById(R.id.character_name);
            type = characterView.findViewById(R.id.character_type);

        }

        public void bind(CharacterModel character){
            name.setText(character.getName());
            type.setText(character.getType());
        }
    }
}
