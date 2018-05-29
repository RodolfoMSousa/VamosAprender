package tcc.faculdade.com.vamosaprender.app.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.faculdade.com.vamosaprender.R;
import tcc.faculdade.com.vamosaprender.app.entidades.Usuario;
import tcc.faculdade.com.vamosaprender.app.retrofit.RetrofitConfig;

public class MainActivity extends AppCompatActivity {

    TextView name;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        Button play =  findViewById(R.id.playButton);
        Button stop =  findViewById(R.id.profileButton);
        db = openOrCreateDatabase("TCC",Context.MODE_PRIVATE, null);

        final SharedPreferences loginArmazenado= getSharedPreferences("loginArmazenado", MODE_PRIVATE);
        final SharedPreferences.Editor editor = loginArmazenado.edit();

        //*****O IF GARENTE QUE A REQUISIÇÃO DE USUARIO SÓ SERÁ FEITA NA PRIMEIRA VEZ QUE USAR O APP
        if(loginArmazenado.getString("nome",null) == null
                || loginArmazenado.getInt("ePrimeira",0 )!= loginArmazenado.getInt("id",0)) {

            Call<Usuario> usuarioCall = new RetrofitConfig()
                    .getLoginService()
                    .getUsuario(loginArmazenado.getInt("id", 0));

            usuarioCall.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    Usuario usuario = response.body();
                    editor.putString("nome", usuario.getNome());
                    editor.putString("sobrenome", usuario.getSobrenome());
                    editor.putInt("ePrimeira",usuario.getUserId());
                    editor.commit();
                    name.setText(loginArmazenado.getString("nome","Aluno")+" "+
                            loginArmazenado.getString("sobrenome",""));
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {

                }
            });
        }else{
            name.setText(loginArmazenado.getString("nome","Aluno")+" "+
                    loginArmazenado.getString("sobrenome",""));

        }
        starAnimations();
    }


    //Função das animações de entrada
    private void starAnimations() {
        final TextView speechPrhases = findViewById(R.id.speechPhrases);
        final ImageView bubble = findViewById(R.id.speechBubble);
        final Animation bubbleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);
        final Animation phraseAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);

        final SharedPreferences loginArmazenado= getSharedPreferences("loginArmazenado", MODE_PRIVATE);

        bubble.setAlpha((float) 1.0);
        bubbleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Resources res = getResources();
                String[] phrases = res.getStringArray(R.array.start_prhases);
                speechPrhases.setText(phrases[0] + " " + loginArmazenado.getString("nome","Aluno"));
                speechPrhases.setAlpha((float) 1.0);
                speechPrhases.startAnimation(phraseAnim);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bubble.startAnimation(bubbleAnim);
    }





}

