package tcc.faculdade.com.vamosaprender.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tcc.faculdade.com.vamosaprender.R;
import tcc.faculdade.com.vamosaprender.app.entidades.Score;
import tcc.faculdade.com.vamosaprender.app.entidades.Usuario;
import tcc.faculdade.com.vamosaprender.app.retrofit.RetrofitConfig;

public class MainActivity extends AppCompatActivity {

    public static Boolean bancoSincronizado;
    SQLiteDatabase db;
    RequestBody requestBody = null;

    SharedPreferences loginArmazenado;
    SharedPreferences.Editor editor;

    private Resources res;
    private String[] phrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = getResources();
        phrases = res.getStringArray(R.array.start_prhases);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button play = findViewById(R.id.replayButton);
        Button profile = findViewById(R.id.endGameButton);
        db = openOrCreateDatabase("TCC", Context.MODE_PRIVATE, null);


        bancoSincronizado = false;

        loginArmazenado = getSharedPreferences("loginArmazenado", MODE_PRIVATE);
        editor = loginArmazenado.edit();

        starAnimations(phrases);
        //   sincronizaBanco(bancoSincronizado);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent());
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameResultadoFinal.class));
            }
        });
    }


    //Função das animações de entrada
    private void starAnimations(final String phrases[]) {
        final TextView speechPrhases = findViewById(R.id.speechPhrases);
        final ImageView bubble = findViewById(R.id.speechBubble);
        final Animation bubbleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);
        final Animation phraseAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);

        final SharedPreferences loginArmazenado = getSharedPreferences("loginArmazenado", MODE_PRIVATE);

        bubble.setAlpha((float) 1.0);
        bubbleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                speechPrhases.setText(String.format(phrases[1], loginArmazenado.getString("Nome", "Aluno")));
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

    //Implemento as ações ao retornar de algum jogo
    @Override
    protected void onRestart() {
        super.onRestart();
        phrases = res.getStringArray(R.array.main_screen_onRestart);
        starAnimations(phrases);
        sincronizaBanco(bancoSincronizado);
    }

    public void sincronizaBanco(Boolean bancoSinc) {

        if (!bancoSinc) {
            Gson gson = new Gson();
            Response<Score> responseScore = null;
            Score score;
            List<Score> scorelist = new ArrayList<>();
            try {
                Cursor cursor = db.rawQuery("SELECT * FROM score WHERE alunoId = " +
                        loginArmazenado.getInt("id", 0), null);
                cursor.moveToFirst();
                while (cursor.getCount() > cursor.getPosition()) {
                    Log.e("alunoId: ", "" + cursor.getInt(cursor.getColumnIndex("alunoId")));
                    Log.e("jogoId: ", "" + cursor.getInt(cursor.getColumnIndex("jogoId")));
                    Log.e("pontuacao: ", "" + cursor.getInt(cursor.getColumnIndex("pontuacao")));
                    Log.e("categoriaId: ", "" + cursor.getInt(cursor.getColumnIndex("categoriaId")));
                    score = new Score();
                    score.setAlunoId(cursor.getInt(cursor.getColumnIndex("alunoId")));
                    score.setCategoriaId(cursor.getInt(cursor.getColumnIndex("categoriaId")));
                    score.setJogoId(cursor.getInt(cursor.getColumnIndex("jogoId")));
                    score.setPontuacao(cursor.getInt(cursor.getColumnIndex("pontuacao")));
                    scorelist.add(score);
                    cursor.moveToNext();
                }

            } catch (Exception e) {
                Log.e("catch sincroniza", "deu ruim");
                e.printStackTrace();
            }
            //Faço as preparações e a requisição
            if (!scorelist.isEmpty()) {
                String json = gson.toJson(scorelist);
                requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

                Call<List<Score>> call = new RetrofitConfig()
                        .setScoreListService()
                        .setScoreList(requestBody);
                Log.e("1", " 1");


                call.enqueue(new Callback<List<Score>>() {
                    @Override
                    public void onResponse(Call<List<Score>> call, Response<List<Score>> response) {
                        try {
                            if(response.isSuccessful()) {
                                Log.e("Inserido", "Scores inseridos com sucesso ");
                                bancoSincronizado = true;
                                db.execSQL("DROP TABLE score");
                                Log.e("7", "77");
                            }else{
                                Log.e("Resposta: ","Resposta para salvar os score que estavam no banco interno falhou");
                            }
                        } catch (Exception e) {
                            Log.e("3", " 3");
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Score>> call, Throwable t) {
                        Log.e("2", " 2");
                        Log.e("Failure", "Ao sincronizar novo score ");
                    }
                });
            }
        }

    }
}

