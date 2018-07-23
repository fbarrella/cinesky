package com.example.bbrothers.cinesky;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    TextView showText;
    GridView gdFilmes;
    ArrayList<Filme> arrFilmes = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        gdFilmes = (GridView) findViewById(R.id.gdFilmes);

        recuperaDados rd = new recuperaDados();
        rd.execute();

    }

    public void receive(String f){

        try{
            JSONArray jsonArr = new JSONArray(f);

            for(int i = 0; i<jsonArr.length(); i++){

                JSONObject x = (JSONObject) jsonArr.get(i);

                JSONArray backUrls = (JSONArray) x.get("backdrops_url");
                ArrayList<String> arrBackdrops = new ArrayList();

                if(backUrls.length() > 0){
                    for(int j = 0; j<backUrls.length(); j++){
                        arrBackdrops.add(backUrls.get(j).toString());
                    }
                }else{
                    arrBackdrops.add("none");
                }



                this.arrFilmes.add(new Filme(
                        x.get("title").toString(),
                        x.get("overview").toString(),
                        x.get("duration").toString(),
                        x.get("release_year").toString(),
                        x.get("cover_url").toString(),
                        arrBackdrops,
                        x.get("id").toString()
                ));
            }

            ArrayAdapter aAdapter = new FilmeAdapter(this, arrFilmes);
            gdFilmes.setAdapter(aAdapter);

            gdFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(Principal.this, ItemDetails.class);

                    intent.putExtra("title", arrFilmes.get(i).getTitle());
                    intent.putExtra("overview", arrFilmes.get(i).getOverview());
                    intent.putExtra("duration", arrFilmes.get(i).getDuration());
                    intent.putExtra("release_year", arrFilmes.get(i).getRelease_year());
                    intent.putStringArrayListExtra("backdrops_url", arrFilmes.get(i).getBackdrops_url());

                    startActivity(intent);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private class recuperaDados extends AsyncTask<Void, Void, String> {

        String dados = "";

        @Override
        protected String doInBackground(Void... voids) {

            try{

                String receive = "";

                URL apiUrl = new URL("https://sky-exercise.herokuapp.com/api/Movies");
                HttpURLConnection urlConn = (HttpURLConnection) apiUrl.openConnection();
                InputStream inputFilmes = urlConn.getInputStream();
                BufferedReader buffRead = new BufferedReader(new InputStreamReader(inputFilmes));

                while(receive != null){
                    receive = buffRead.readLine();
                    dados += receive;
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            return dados;
        }

        @Override
        protected void onPostExecute(String filmes) {
            super.onPostExecute(filmes);

            Principal.this.receive(filmes);
        }

    }

}