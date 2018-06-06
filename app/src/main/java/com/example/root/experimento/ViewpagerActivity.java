package com.example.root.experimento;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.root.experimento.fragments.PageFragment;
import com.example.root.experimento.model.Pokemon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class ViewpagerActivity extends AppCompatActivity {

    private PageFragmentAdaper adaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        String[] titles = {"Pagina 1", "Pagina 2"};
        adaper = new PageFragmentAdaper(getSupportFragmentManager(), titles );
        viewPager.setAdapter(adaper);

        new AsyncTask<Void, Void, List<Pokemon>>() {

            public ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                dialog = ProgressDialog.show(ViewpagerActivity.this,"Aguarde...","listando pokemons.",true,true);
            }

            @Override
            protected List<Pokemon> doInBackground(Void... params) {
                //Uma requisição a API Pokemon
                List<Pokemon> pokemons = null;

                try {
                    URL url = new URL("https://pokeapi.co/api/v2/pokemon");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.connect();

                    Scanner scanner = new Scanner(connection.getInputStream());
                    String json = scanner.next();

                    Gson gson = new Gson();
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    Type type = new TypeToken<List<Pokemon>>(){}.getType();
                    pokemons = gson.fromJson(jsonArray.toString(),type);



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return pokemons;
            }

            @Override
            protected void onPostExecute(List<Pokemon> pokemons) {
                dialog.dismiss();

                PageFragment fragment1 = (PageFragment) adaper.getItem(0);
                PageFragment fragment2 = (PageFragment) adaper.getItem(1);
                fragment1.pokemons = pokemons.subList(0,10);
                fragment2.pokemons = pokemons.subList(11,20);

                adaper.notifyDataSetChanged();

                fragment1.arrayAdapter.notifyDataSetChanged();
                fragment2.arrayAdapter.notifyDataSetChanged();

            }
        }.execute();



    }
}
