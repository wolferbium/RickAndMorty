package com.makarov.rickandmorty.ui;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makarov.rickandmorty.network.NetworkService;
import com.makarov.rickandmorty.R;
import com.makarov.rickandmorty.model.CharacterModel;
import com.makarov.rickandmorty.model.PageModel;

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
    public OnCharacterClick onCharacterClick;

    private static List<CharacterModel> characterList = new ArrayList<>();

    //private View.OnClickListener onCharacterClickListener;

    public void registerOnCharacterClickCallback(OnCharacterClick onCharacterClick) {
        this.onCharacterClick = onCharacterClick;
    }

    public void setCharacterList(Collection<CharacterModel> characters){
        characterList.addAll(characters);
        notifyDataSetChanged();
    }

    public void addCharacter(CharacterModel character){
        characterList.add(character);
        notifyDataSetChanged();
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
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public static CharacterModel getCharacter(int index) {
        return characterList.get(index);
    }

    public static boolean isEmpty() {
        return characterList.isEmpty();
    }

    class CharacterViewHolder extends BaseViewHolder {
        private ImageView image;
        private TextView name;
        private TextView species;

        private ConstraintLayout constraintLayout;

        private long position;

        public CharacterViewHolder(View characterView) {
            super(characterView);
            image = characterView.findViewById(R.id.character_image);
            name = characterView.findViewById(R.id.character_name);
            species = characterView.findViewById(R.id.character_species);
            constraintLayout = characterView.findViewById(R.id.item_character);

            //characterView.setTag(this);
            //characterView.setOnClickListener(onCharacterClickListener);
        }

        //public void bind(CharacterModel character){
        public void onBind(int position) {
            this.position = position;
            CharacterModel character = characterList.get(position);
            name.setText(character.getName());
            species.setText(character.getSpecies());
            Glide.with(image).load(character.getImage()).into(image);

            constraintLayout.setOnClickListener( view -> {
                if (getAdapterPosition() != -1) {
                    onCharacterClick.onCharacterClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnCharacterClick {
        void onCharacterClick(int characterPosition);
    }
}
