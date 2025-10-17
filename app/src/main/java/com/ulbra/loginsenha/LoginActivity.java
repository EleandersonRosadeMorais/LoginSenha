package com.ulbra.loginsenha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtSenha;
    Button btnLogar;
    DBHelper db;

    // Configura a tela quando abre
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);

        // Prepara campos de email, senha e botão
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogar = findViewById(R.id.btnLogar);

        // Quando clica no botão Logar
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtEmail.getText().toString();
                String password = edtSenha.getText().toString();

                // Verifica se campos estão preenchidos
                if (username.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email não inserido", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Senha não inserida", Toast.LENGTH_SHORT).show();
                } else {
                    // Consulta banco para validar login
                    String res = db.validarLogin(username, password);
                    if (res.equals("OK")) {
                        Toast.makeText(LoginActivity.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        // Se login ok, vai para tela secreta
                        startActivity(new Intent(LoginActivity.this, SecretActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Email ou senha incorretos!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}