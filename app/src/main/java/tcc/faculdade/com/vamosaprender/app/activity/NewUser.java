package tcc.faculdade.com.vamosaprender.app.activity;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import tcc.faculdade.com.vamosaprender.R;
import tcc.faculdade.com.vamosaprender.app.entidades.Aluno;
import tcc.faculdade.com.vamosaprender.app.entidades.Login;
import tcc.faculdade.com.vamosaprender.app.entidades.Usuario;
import tcc.faculdade.com.vamosaprender.app.retrofit.RetrofitConfig;

public class NewUser extends AppCompatActivity {

    EditText novoNomeId;
    EditText novoSobrenomeId;
    EditText novoUsuId;
    EditText novaSenhaId;
    Button criarNovoUsu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        novoNomeId = findViewById(R.id.novoNomeId);
        novoSobrenomeId = findViewById(R.id.novoSobrenomeId);
        novoUsuId = findViewById(R.id.novoUusId);
        novaSenhaId = findViewById(R.id.novaSenhaId);
        criarNovoUsu = findViewById(R.id.criarNovoUsu);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        criarNovoUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Aluno aluno = null;
                Gson gson = new Gson();
                Usuario u = new Usuario();
                Response<Aluno> a = null;
                Login login = new Login();
                Response<Login> l = null;

                RequestBody requestBody = null;

                if (!novoNomeId.getText().toString().equals("")
                        && !novoSobrenomeId.getText().toString().equals("")
                        && !novoUsuId.getText().toString().equals("")
                        && !novaSenhaId.getText().toString().equals("")) {


                    //Insere um novo Usuario/Aluno
                    u.setNome(novoNomeId.getText().toString());
                    u.setSobrenome(novoSobrenomeId.getText().toString());

                    String json = gson.toJson(u);
                    requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

                    Call<Aluno> call = new RetrofitConfig()
                            .getLoginService()
                            .setAluno(requestBody);
                    try {
                        a = call.execute();
                        aluno = a.body();
                        Log.e("", a.message().toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("anem", "Deu ruim");
                    }


                    //Insere um novo Login
                    login.setUserName(novoUsuId.getText().toString());
                    login.setSenha(novaSenhaId.getText().toString());
                    login.setUsuarioId(aluno.getUsuarioId());
                    json = gson.toJson(login);
                    requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
                    Call<Login> callLogin = new RetrofitConfig()
                            .getLoginService()
                            .setLogin(requestBody);
                    try {
                        l = callLogin.execute();
                        login = l.body();
                        if (login == null)
                            Log.e("OPA", "Deu BOM");
                    } catch (IOException ex) {
                        Log.e("OPA", "Deu REUIMDEMAIS");
                    }

                    if (login == null) {
                        Toast.makeText(getApplicationContext(), "POR FAVOR ESCOLHA OUTRO NOME DE USUARIO", Toast.LENGTH_SHORT).show();
                        login = new Login();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "POR FAVOR PREENCHA TODOS OS CAMPOS", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


}
