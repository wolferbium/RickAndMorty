package com.makarov.rickandmorty;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.pdf.PdfDocument;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<CharacterModel> characterList = new ArrayList<>();

    public void setCharacterList(Collection<CharacterModel> characters){
        characterList.addAll(characters);
        notifyDataSetChanged();
    }

    public void addCharacter(CharacterModel character){
        characterList.add(character);
        notifyDataSetChanged();
    }

    public void clearCharacterList(){
        characterList.clear();
        notifyDataSetChanged();
    }

    public void getRequestCharacter() {

        NetworkService.getInstance()
                .getJSONApi()
                .getCharacterWithID(1)
                .enqueue(new Callback<CharacterModel>() {
                    @Override
                    public void onResponse(Call<CharacterModel> call, Response<CharacterModel> response){
                        CharacterModel character = response.body();

                        addCharacter(character);
                    }

                    @Override
                    public void onFailure(Call<CharacterModel> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void getRequestCharactersPage(int page) {
        Log.w("RequestCharactersPage", "Start");
        NetworkService.getInstance()
                .getJSONApi()
                .getCharactersPage(page)
                .enqueue(new Callback<PageModel>() {
                    @Override
                    public void onResponse(Call<PageModel> call, Response<PageModel> response) {
                        PageModel page = response.body();

                        setCharacterList(page.getResults());
                        Log.w("RequestCharactersPage", "Items: " + getItemCount());
                    }

                    @Override
                    public void onFailure(Call<PageModel> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
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
        private ImageView image;
        private TextView name;
        private TextView species;

        public CharacterViewHolder(View characterView) {
            super(characterView);
            image = characterView.findViewById(R.id.character_image);
            name = characterView.findViewById(R.id.character_name);
            species = characterView.findViewById(R.id.character_species);

        }

        public void bind(CharacterModel character){
            name.setText(character.getName());
            species.setText(character.getSpecies());
            Glide.with(image).load(character.getImage()).into(image);
        }
    }
}
