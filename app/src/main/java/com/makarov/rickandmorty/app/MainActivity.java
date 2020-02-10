package com.makarov.rickandmorty.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;

import com.makarov.rickandmorty.R;
import com.makarov.rickandmorty.ui.CharacterAdapter;
import com.makarov.rickandmorty.ui.PaginationScrollListener;

public class MainActivity extends AppCompatActivity {

    private RecyclerView charactersRecycleView;
    private SwipeRefreshLayout swipeRefresh;
    private CharacterAdapter characterAdapter;

    private boolean isLastPage = false;
    private int currentPage = 1;

    public static final String EXTRA_CHARACTER_ID = "com.makarov.rickandmorty.CHARACTER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        charactersRecycleView = findViewById(R.id.list_characters);
        charactersRecycleView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        charactersRecycleView.setLayoutManager(layoutManager);
        characterAdapter = new CharacterAdapter();
        charactersRecycleView.setAdapter(characterAdapter);

        if (CharacterAdapter.isEmpty()) {
            characterAdapter.getRequestCharactersPage(currentPage);
        }
        charactersRecycleView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                currentPage++;
                characterAdapter.getRequestCharactersPage(currentPage);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return false;
            }
        });

        characterAdapter.registerOnCharacterClickCallback(this::onCharacterClick);
    }

    public void onCharacterClick(int characterPosition) {
        Intent intent = new Intent(this, CharacterInfo.class);
        intent.putExtra(EXTRA_CHARACTER_ID, characterPosition);
        startActivity(intent);
    }
}
