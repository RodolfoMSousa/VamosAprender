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

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.faculdade.com.vamosaprender.R;
import tcc.faculdade.com.vamosaprender.app.entidades.Aluno;
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

                    Gson gson = new Gson();
                    final Login login = new Login();
                    login.setUserName(userName.getText().toString());
                    login.setSenha(password.getText().toString());
                    String json = gson.toJson(login);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

                    Call<Aluno> call = new RetrofitConfig()
                            .getLoginService()
                            .getLogin(requestBody);


                    call.enqueue(new Callback<Aluno>() {
                        @Override
                        public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                            try {
                                if (response.isSuccessful()) {
                                    Aluno l = response.body();
                                    Log.e("Autenticado", "login autenticado");
                                    Log.e("NOME: ", l.getNome());
                                    //Salvando o Login e senha no SharedPreferences
                                    editor.putString("userName", userName.getText().toString());
                                    editor.putString("senha", password.getText().toString());
                                    editor.putString("Nome", l.getNome());
                                    editor.putInt("turma", l.getTurmaId());
                                    //***IF PRA GARANTIR QUE É A PRIMEIRA VEZ
                                    editor.putInt("id", l.getAlunoid());
                                    if(loginArmazenado.getInt("ePrimeira",0) != loginArmazenado.getInt("id",444)){
                                        editor.putInt("ePrimeira",4444);
                                    }

                                    editor.commit();

                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    finish();
                                } else {
                                    Log.e("Falha ao autenticar", "Nome de usuário ou senha incorretos");
                                    Toast.makeText(getApplicationContext(), "Nome de usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception ex) {
                                Log.e("ERRO OnResponseLogin ", ex.getMessage());
                                Toast.makeText(getApplicationContext(), "POR FAVOR VERIFIQUE OS DADOS", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<Aluno> call, Throwable t) {
                            Log.e("CEPService   ", "Erro ao buscar o Login:" + t.getMessage());
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
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
