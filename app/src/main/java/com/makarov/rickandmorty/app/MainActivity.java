package com.makarov.rickandmorty.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.makarov.rickandmorty.R;
import com.makarov.rickandmorty.model.CharacterModel;
import com.makarov.rickandmorty.model.CharactersList;
import com.makarov.rickandmorty.presenter.CharacterListPresenter;
import com.makarov.rickandmorty.ui.CharacterAdapter;
import com.makarov.rickandmorty.ui.PaginationScrollListener;

import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    private RecyclerView charactersRecycleView;
    private CharacterAdapter characterAdapter;

    private CharacterListPresenter presenter;
    public static final String EXTRA_CHARACTER= "com.makarov.rickandmorty.CHARACTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPresenter();
        initCharacterAdapter();
        initRecycleView();

        presenter.updateCharacterList();
    }

    private void initPresenter() {
        presenter = new CharacterListPresenter(new CharactersList());
        presenter.attachView(this);
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

    public void onCharacterClick(CharacterModel character) {
        Intent intent = new Intent(this, CharacterView.class);
        intent.putExtra(EXTRA_CHARACTER, character);
        startActivity(intent);
    }

    public void update(Collection<CharacterModel> characters) {
        characterAdapter.setData(characters);
    }
}
