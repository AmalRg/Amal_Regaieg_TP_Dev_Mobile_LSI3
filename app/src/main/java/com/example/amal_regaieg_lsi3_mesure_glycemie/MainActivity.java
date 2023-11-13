package com.example.amal_regaieg_lsi3_mesure_glycemie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvage, TvRes;
    private SeekBar sbAge;
    private RadioButton rbtOui, rbtNon;
    private EditText ValMes;
    private Button btnConsulter;

    @Override
    // onCreate = main
    protected void onCreate(Bundle savedInstanceState) {    //Bundle sauvegrade l'etat de l'app en arriere plan
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);         // contentView -> fichier.xml // R contient des sous classes : elle est une classe finale constante
                                                 // layout -> classe fnale contient les indentifients de toutes les interfaces de l'app
        init();     // initialisation

        // Action sur SeekBat : listener du SeeBar
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.i("Information", "onProgressChanged" + progress);
                tvage.setText(getString(R.string.age) + " " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculer(v);
            }
        });
    }


    public void calculer(View v) {
        int age;
        float valeurMesuree;
        boolean verifAge = false, verifValeur = false;

        if (sbAge.getProgress() != 0) {
            verifAge = true;
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Veuillez saisir votre âge !", Toast.LENGTH_SHORT).show();
                }
            }, 0); // Délai de 0 millisecondes, affiche immédiatement
        }

        if (!ValMes.getText().toString().isEmpty()) {
            verifValeur = true;
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Veuillez saisir une valeur mesurée valide !", Toast.LENGTH_LONG).show();
                }
            }, 1000); // Délai de 1000 millisecondes (1 seconde) avant d'afficher le Toast
        }

        if(verifAge && verifValeur) {
            age = sbAge.getProgress();
            valeurMesuree = Float.valueOf(ValMes.getText().toString());

            if(rbtOui.isChecked()) {    // il est a jeun
                if(age >= 13) {
                    if (valeurMesuree < 5.0)
                        TvRes.setText("Le niveau de glycèmie est bas !");
                    else if (valeurMesuree >= 5.0 && valeurMesuree <= 7.2)
                        TvRes.setText("Le niveau de glycèmie est normal !");
                    else
                        TvRes.setText("Le niveau de glycèmie est élevé !");
                }
                else
                    if(age >= 6 && age <= 12) {
                        if (valeurMesuree < 5.0)
                            TvRes.setText("Le niveau de glycèmie est bas !");
                        else
                            if (valeurMesuree >= 5.0 && valeurMesuree <= 10.0)
                                TvRes.setText("Le niveau de glycèmie est normal !");
                        else
                            TvRes.setText("Le niveau de glycèmie est élevé !");
                    }
                else
                    if(valeurMesuree < 5.50)
                        TvRes.setText("Le niveau de glycèmie est bas !");
                    else
                        if(valeurMesuree >= 5.5 && valeurMesuree <= 10.0)
                            TvRes.setText("Le niveau de glycèmie est normal !");
                        else
                            TvRes.setText("Le niveau de glycèmie est élevé !");
                }
                else
                    if(valeurMesuree <= 10.5)
                        TvRes.setText("Le niveau de glycèmie est normal !");
                    else
                        TvRes.setText("Le niveau de glycèmie est élevé !");
            }
        }

    private void init() {
        tvage = (TextView) findViewById(R.id.tvage);
        sbAge = (SeekBar) findViewById(R.id.sbAge);
        rbtOui = (RadioButton) findViewById(R.id.rbtOui);
        rbtNon = (RadioButton) findViewById(R.id.rbtNon);
        ValMes = (EditText) findViewById(R.id.ValMes);
        btnConsulter = (Button) findViewById(R.id.btnConsulter);
        TvRes = (TextView) findViewById(R.id.TvRes);
    }

}