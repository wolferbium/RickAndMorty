package com.makarov.rickandmorty.app;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import com.makarov.rickandmorty.R;
import com.makarov.rickandmorty.model.CharacterModel;
import com.makarov.rickandmorty.model.CharactersList;
import com.makarov.rickandmorty.presenter.CharacterListPresenter;
import com.makarov.rickandmorty.ui.CharacterAdapter;
import com.makarov.rickandmorty.ui.PaginationScrollListener;

import java.util.Collection;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.viewstate.strategy.alias.Skip;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private RecyclerView charactersRecycleView;
    private CharacterAdapter characterAdapter;

    @InjectPresenter
    CharacterListPresenter presenter;

    public static final String EXTRA_CHARACTER_ID = "com.makarov.rickandmorty.CHARACTER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCharacterAdapter();
        initRecycleView();
    }

    private void initCharacterAdapter() {
        characterAdapter = new CharacterAdapter(this);
        characterAdapter.registerOnCharacterClickCallback(this::onCharacterClick);
    }

    private void initRecycleView() {
        charactersRecycleView = findViewById(R.id.list_characters);
        charactersRecycleView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        charactersRecycleView.setLayoutManager(layoutManager);
        charactersRecycleView.setAdapter(characterAdapter);

        charactersRecycleView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                presenter.updateCharacterList();
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }
        });
    }

    @Override
    public void onCharacterClick(int characterId) {
        Intent intent = new Intent(this, CharacterActivity.class);
        intent.putExtra(EXTRA_CHARACTER_ID, characterId);
        startActivity(intent);
    }

    @Override
    public void update(Collection<CharacterModel> characters) {
        characterAdapter.setData(characters);
    }
}
