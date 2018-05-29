package tcc.faculdade.com.vamosaprender.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.faculdade.com.vamosaprender.R;
import tcc.faculdade.com.vamosaprender.app.entidades.Login;
import tcc.faculdade.com.vamosaprender.app.retrofit.RetrofitConfig;

public class LoginActivity extends AppCompatActivity {

    public SQLiteDatabase db;

    EditText userName;
    EditText password;
    Button login;
    Button novoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.userNameId);
        password = findViewById(R.id.passwordId);
        login = findViewById(R.id.loginId);
        novoUsuario = findViewById(R.id.BotaoNovoUsuarioId);

        final SharedPreferences loginArmazenado = getSharedPreferences("loginArmazenado", MODE_PRIVATE);
        final SharedPreferences.Editor editor = loginArmazenado.edit();

        userName.setText(loginArmazenado.getString("userName",""));
        password.setText(loginArmazenado.getString("senha",""));


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userName.getText().toString().equals("")
                        && !password.getText().toString().equals("")) {
                    Call<Login> call = new RetrofitConfig()
                            .getLoginService()
                            .getLogin(userName.getText().toString(),
                                    password.getText().toString());



                    call.enqueue(new Callback<Login>() {
                        @Override
                        public void onResponse(Call<Login> call, Response<Login> response) {
                            try {
                                Login l = response.body();
                                Log.e("RESPOSTA: ", l.getUserName());
                                if (l != null) {
                                    Log.e("Autenticado", "login autenticado");

                                    //Salvando o Login e senha no SharedPreferences
                                    editor.putString("userName", userName.getText().toString());
                                    editor.putString("senha", password.getText().toString());
                                    editor.putString("Nome", null);
                                    editor.putString("Sobrenome", null);
                                    //***IF PRA GARANTIR QUE É A PRIMEIRA VEZ
                                    editor.putInt("id", l.getUsuarioId());
                                    if(loginArmazenado.getInt("ePrimeira",0) != loginArmazenado.getInt("id",444)){
                                        editor.putInt("ePrimeira",4444);
                                    }
                                    editor.commit();

                                    //**********AQUI CHAMO O BANCO
                                    try {
                                        db = openOrCreateDatabase("TCC", Context.MODE_PRIVATE, null);
                                        //db.execSQL("DROP TABLE login");
                                        db.execSQL("CREATE TABLE IF NOT EXISTS login " +
                                                "(usuarioId INTEGER, userName VARCHAR(11) PRIMARY KEY, senha VARCHAR(30),dataLogin TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)");
                                        // db.execSQL("INSERT INTO login(usuarioId, userName, senha) VALUES ('" + l.getUsuarioId() + "','" + l.getUserName() + "','" + l.getSenha() + "')");
                                        db.execSQL("INSERT INTO login(usuarioId, userName, senha) SELECT " + l.getUsuarioId() + ",'" + l.getUserName() + "','" + l.getSenha() + "'" +
                                                " WHERE NOT EXISTS(SELECT 1 FROM login WHERE usuarioId = " + l.getUsuarioId() + ")");

                                        //Proxima Activity
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                        finish();

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Log.e("Falha ao autenticar", "Nome de usuário ou senha incorretos");
                                }
                            } catch (Exception ex) {
                                Log.e("ERRO OnResponseLogin ", ex.getMessage());
                                Toast.makeText(getApplicationContext(), "POR FAVOR VERIFIQUE OS DADOS", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<Login> call, Throwable t) {
                            Log.e("CEPService   ", "Erro ao buscar o Login:" + t.getMessage());
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "POR FAVOR PREENCHA TODOS OS CAMPOS", Toast.LENGTH_SHORT).show();
                }

            }
        });


        novoUsuario.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, NewUserActivity.class));

            }
        });


    }


}
