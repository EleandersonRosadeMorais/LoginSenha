package com.ulbra.loginsenha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {

    EditText edtNome, edtEmail, edtSenha, edtConfirmarSenha, edtTelefone, edtNascimento;
    Button btnRegistrar;
    DBHelper db;

    // Configura tela de registro
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DBHelper(this);

        // Encontra todos os campos do formulário
        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        edtConfirmarSenha = findViewById(R.id.edtConfirmarSenha);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtNascimento = findViewById(R.id.edtNascimento);
        btnRegistrar = findViewById(R.id.btnLogar);

        // Quando clica em Registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();
                String confirmarSenha = edtConfirmarSenha.getText().toString();

                // Validações dos campos
                if (email.isEmpty()) {
                    Toast.makeText(RegistrarActivity.this, "Email é obrigatório", Toast.LENGTH_SHORT).show();
                } else if (senha.isEmpty() || confirmarSenha.isEmpty()) {
                    Toast.makeText(RegistrarActivity.this, "Preencha a senha e confirmação", Toast.LENGTH_SHORT).show();
                } else if (!senha.equals(confirmarSenha)) {
                    Toast.makeText(RegistrarActivity.this, "Senhas não conferem", Toast.LENGTH_SHORT).show();
                } else {
                    // Tenta criar usuário no banco
                    long res = db.criarUtilizador(email, senha);
                    if (res > 0) {
                        Toast.makeText(RegistrarActivity.this, "Registro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        // Se criou com sucesso, volta para tela principal
                        startActivity(new Intent(RegistrarActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegistrarActivity.this, "Erro ao registrar usuário", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}