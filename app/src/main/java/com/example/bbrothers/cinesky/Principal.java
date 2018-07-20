package com.example.bbrothers.cinesky;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    ImageView imageView;
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
                this.arrFilmes.add(new Filme(
                        "" + x.get("title"),
                        "" + x.get("cover_url")
                ));
            }

            ArrayAdapter aAdapter = new FilmeAdapter(this, arrFilmes);
            gdFilmes.setAdapter(aAdapter);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private class recuperaDados extends AsyncTask<Void, Void, String> {

        String dados = "";
        private ArrayList<Filme> arrFilmes = new ArrayList();


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