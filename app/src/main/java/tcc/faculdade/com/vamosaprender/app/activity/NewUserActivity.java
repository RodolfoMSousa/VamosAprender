package tcc.faculdade.com.vamosaprender.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.faculdade.com.vamosaprender.R;
import tcc.faculdade.com.vamosaprender.app.entidades.Aluno;
import tcc.faculdade.com.vamosaprender.app.entidades.Login;
import tcc.faculdade.com.vamosaprender.app.entidades.Usuario;
import tcc.faculdade.com.vamosaprender.app.retrofit.RetrofitConfig;

public class NewUserActivity extends AppCompatActivity {

    EditText novoNomeId;
    EditText novoEmail;
    EditText novoUsuId;
    EditText novaSenhaId;
    Button criarNovoUsu;
    TextView nome, email, login, senha;
    private Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        font = Typeface.createFromAsset(getAssets(), "fonts/iceland.ttf");
        novoNomeId = findViewById(R.id.novoNomeId);
        novoEmail = findViewById(R.id.novoEmail);
        novoUsuId = findViewById(R.id.novoUusId);
        novaSenhaId = findViewById(R.id.novaSenhaId);
        criarNovoUsu = findViewById(R.id.criarNovoUsu);
        nome = findViewById(R.id.textNome);
        email = findViewById(R.id.textEmail);
        login = findViewById(R.id.textLogin);
        senha = findViewById(R.id.textSenha);

        nome.setTypeface(font);
        email.setTypeface(font);
        login.setTypeface(font);
        senha.setTypeface(font);

        SharedPreferences loginArmazenado = getSharedPreferences("loginArmazenado", MODE_PRIVATE);
        final SharedPreferences.Editor editor = loginArmazenado.edit();


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        criarNovoUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!novoNomeId.getText().toString().equals("")
                        && !novoEmail.getText().toString().equals("")
                        && !novoUsuId.getText().toString().equals("")
                        && !novaSenhaId.getText().toString().equals("")) {

                    Aluno aluno = new Aluno();
                    Gson gson = new Gson();
                    aluno.setNome(novoNomeId.getText().toString());
                    aluno.setEmail(novoEmail.getText().toString());
                    aluno.setLogin(novoUsuId.getText().toString());
                    aluno.setSenha(novaSenhaId.getText().toString());
                    String json = gson.toJson(aluno);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

                    Call<Aluno> call = new RetrofitConfig()
                            .getLoginService()
                            .setLogin(requestBody);

                    call.enqueue(new Callback<Aluno>() {
                        @Override
                        public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                            if (response.isSuccessful()) {
                                Aluno alunoResposta = response.body();

                                String []nome = alunoResposta.getNome().split(" ");
                                //Salvando o Login e senha no SharedPreferences
                                editor.putString("userName", novoUsuId.getText().toString());
                                editor.putString("senha", novaSenhaId.getText().toString());
                                editor.putString("Nome", nome[0]);
                                editor.putInt("turma", alunoResposta.getTurmaId());
                                editor.putInt("id", alunoResposta.getAlunoid());
                                editor.commit();

                                startActivity(new Intent(NewUserActivity.this,MainActivity.class));
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),"Usuario já criado",Toast.LENGTH_SHORT).show();
                                Log.e("Erro Novo Usuario",response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<Aluno> call, Throwable t) {
                            Log.e("Erro Novo Usuario","falhou");
                            Toast.makeText(getApplicationContext(),"TENTE NOVAMENTE MAIS TARDE!",Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "POR FAVOR PREENCHA TODOS OS CAMPOS", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


}
