package com.ulbra.loginsenha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecretActivity extends AppCompatActivity {

    private Button btnVoltar;
    private TextView tvTitulo, tvDescricao;
    private ImageView ivSegredo;

    // Tela que aparece após login bem-sucedido
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);

        // Encontra os elementos na tela
        btnVoltar = findViewById(R.id.btnVoltar);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvDescricao = findViewById(R.id.tvDescricao);
        ivSegredo = findViewById(R.id.ivSegredo);

        // Botão para voltar à tela principal
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecretActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        // Mostra o conteúdo secreto
        mostrarSegredo();
    }

    // Configura o que será mostrado na tela secreta
    private void mostrarSegredo() {
        tvTitulo.setText("Segredo Revelado!");
        tvDescricao.setText("Parabéns! Você desbloqueou o conteúdo secreto.");
    }

}