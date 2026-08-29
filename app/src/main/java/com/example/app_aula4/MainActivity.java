package com.example.app_aula4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button btadd;
    public List<Nota> listaNotas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView rvNota = findViewById(R.id.rvNota);
        rvNota.setLayoutManager(new LinearLayoutManager(this));

        AdapterNota adapter = new AdapterNota(listaNotas);
        rvNota.setAdapter(adapter);


        btadd = findViewById(R.id.btadd);
        btadd.setOnClickListener(v ->{
            View tela = LayoutInflater.from(this)
                    .inflate(R.layout.tela_adicionar,null,false);
            EditText campotitulo = tela.findViewById(R.id.campotitulo);
            EditText campodescricao = tela.findViewById(R.id.campodescricao);
            new MaterialAlertDialogBuilder(this)
                    .setTitle("adicionar nota")
                    .setView(tela)
                    .setNegativeButton("Cancelar",null)
                    .setNegativeButton("Adicionar",(dialog,which) ->{
                        Nota item = new Nota(campotitulo.getText().toString(),
                                             campodescricao.getText().toString());
                       listaNotas.add(item);
                    }).show();
        });

    }
}