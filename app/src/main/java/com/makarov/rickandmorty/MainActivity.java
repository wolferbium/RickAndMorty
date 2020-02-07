package com.makarov.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    private RecyclerView charactersRecycleView;
    private SwipeRefreshLayout swipeRefresh;
    private CharacterAdapter characterAdapter;

    private boolean isLastPage = false;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //swipeRefresh.findViewById(R.id.swipeRefresh);
        //swipeRefresh.setOnRefreshListener(this);

        charactersRecycleView = findViewById(R.id.list_characters);
        charactersRecycleView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        charactersRecycleView.setLayoutManager(layoutManager);
        characterAdapter = new CharacterAdapter();
        charactersRecycleView.setAdapter(characterAdapter);

        //loadCharacters();
        //characterAdapter.getRequestCharacter();
        characterAdapter.getRequestCharactersPage(currentPage);

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

    }
}
