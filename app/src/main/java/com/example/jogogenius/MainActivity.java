package com.example.jogogenius;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    public ArrayList seqRandom = new ArrayList();
    public ArrayList resUser = new ArrayList();

    public  Button btAzul;
    public  Button btVerde ;
    public  Button btVermelho ;
    public  Button btAmarelo ;
    public  Button btPlacar;
    public  Button btRodada;

    public final Handler handler = new Handler();
    public int tempo = 3000;
    public int placar = 0;
    public int turn = 0;
    public boolean continua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btAmarelo = (Button)findViewById(R.id.btn_amarelo);
        btVermelho = (Button)findViewById(R.id.btn_vermelho);
        btVerde = (Button)findViewById(R.id.btn_verde);
        btAzul = (Button)findViewById(R.id.btn_azul);
        btPlacar = (Button)findViewById((R.id.btPlacar));
        btRodada = (Button)findViewById((R.id.btRodada));

        btPlacar.setText(String.valueOf(placar));
        btRodada.setText(String.valueOf(turn));

    }

    public void geradorRandom(View v){

        btRodada.setText(String.valueOf(turn += 1));
        btPlacar.setText((String.valueOf(placar)));

        Random ram =  new Random();
        int valor = 0;

        while(valor == 0 ){

            valor = ram.nextInt(5);

            if(valor > 0 ){

              seqRandom.add(valor);
              System.out.println("Valor gerador foi: "+valor);

            }
        }

        apertaBotao(seqRandom);
        tempoResposta();



    }

    public void tempoResposta(){

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                  comparaResposta();
            }
        },tempo += 1000);

    }

    public void apertaBotao(ArrayList num){

        int delay = 1000;

        for(int i = 0; i < num.size(); i++){

            if(num.get(i).equals(1)) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btAzul.setBackgroundResource(R.drawable.btn_azul_apertado);
                    }
                },delay+=500);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btAzul.setBackgroundResource(R.drawable.selector_azul);
                    }
                },delay+=500);

            }

            if(num.get(i).equals(2)) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btVerde.setBackgroundResource(R.drawable.btn_verde_apertado);
                    }
                },delay+=500);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btVerde.setBackgroundResource(R.drawable.selector_verde);
                    }
                },delay+=500);
            }

            if(num.get(i).equals(3)) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btVermelho.setBackgroundResource(R.drawable.btn_vermelho_apertado);
                    }
                },delay+=500);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btVermelho.setBackgroundResource(R.drawable.selector_vermelho);
                    }
                },delay+=500);
            }

            if(num.get(i).equals(4)) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btAmarelo.setBackgroundResource(R.drawable.btn_amarelo_apertado);
                    }
                },delay+=500);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btAmarelo.setBackgroundResource(R.drawable.selector_amarelo);
                    }
                },delay+=500);
            }
        }

    }

    public void comparaResposta(){

        if(resUser.size() != seqRandom.size()){
            System.out.println("FIm Fim de jogo");
            continua = false;

            AlertDialog.Builder msg = new AlertDialog.Builder(MainActivity.this);
            msg.setTitle("Perdeu");
            msg.setMessage("Fim de jogo");
            msg.create();
            msg.show();
            resetaJogo();
        }else{

            for(int i = 0; i < seqRandom.size(); i++){

                if(seqRandom.get(i) == resUser.get(i)){
                    placar += 10;
                    btPlacar.setText(String.valueOf(placar));
                    System.out.println("Acertou");
                    continua = true;

                }else{
                    continua = false;

                    AlertDialog.Builder msg = new AlertDialog.Builder(MainActivity.this);
                    msg.setTitle("Tempo");
                    msg.setMessage("Tempo esgotado");
                    msg.create();
                    msg.show();
                    resetaJogo();
                }
            }
        }


        tempo += 1500;

        if(continua == true){
            resUser.clear();
            geradorRandom(getCurrentFocus());
        }
        if(continua == false) {

            AlertDialog.Builder msg = new AlertDialog.Builder(MainActivity.this);
            msg.setTitle("Perdeu");
            msg.setMessage("Sequencia Errada");
            msg.create();
            msg.show();
            resetaJogo();
        }
    }

    public void pegaRes(View v){

        if(v.getId() == btAzul.getId()){
            resUser.add(1);
        }

        if(v.getId() == btVerde.getId()){
            resUser.add(2);
        }

        if(v.getId() == btVermelho.getId()){
            resUser.add(3);
        }

        if(v.getId() == btAmarelo.getId()){
            resUser.add(4);
        }

    }

    public void resetaJogo(){
        resUser.clear();
        seqRandom.clear();
        tempo = 3000;
        placar = 0;
        turn = 0;

    }



}
