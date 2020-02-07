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

public class CharacterAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;

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

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == characterList.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        //holder.bind(characterList.get(position));
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public void addLoading() {
        isLoaderVisible = true;
        getRequestCharactersPage(2);
    }

    public void removeLoading() {
        isLoaderVisible = false;
    }

    class CharacterViewHolder extends BaseViewHolder {
        private ImageView image;
        private TextView name;
        private TextView species;

        private long position;

        public CharacterViewHolder(View characterView) {
            super(characterView);
            image = characterView.findViewById(R.id.character_image);
            name = characterView.findViewById(R.id.character_name);
            species = characterView.findViewById(R.id.character_species);
        }

        //public void bind(CharacterModel character){
        public void onBind(int position) {
            this.position = position;
            CharacterModel character = characterList.get(position);
            name.setText(character.getName());
            species.setText(character.getSpecies());
            Glide.with(image).load(character.getImage()).into(image);
        }
    }

    class ProgressHolder extends BaseViewHolder {

        public ProgressHolder(View progressView) {
            super(progressView);
        }
    }
}
