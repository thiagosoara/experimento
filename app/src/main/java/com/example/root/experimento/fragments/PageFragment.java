package com.example.root.experimento.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.root.experimento.PokemonDetailsActivity;
import com.example.root.experimento.R;
import com.example.root.experimento.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16/05/17.
 */

public class PageFragment extends Fragment {
    public List<Pokemon> pokemons = new ArrayList<Pokemon>();
    public ArrayAdapter<Pokemon> arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page,container,false);

        ListView lvPokemons = (ListView) view.findViewById(R.id.lv_pokemons);

        arrayAdapter = new ArrayAdapter<Pokemon>(getActivity(),android.R.layout.simple_list_item_1,pokemons);
        lvPokemons.setAdapter(arrayAdapter);

        lvPokemons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getActivity(), PokemonDetailsActivity.class);
                startActivity(it);
            }
        });

        return view;

    }
}
