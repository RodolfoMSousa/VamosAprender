package tcc.faculdade.com.vamosaprender.app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import tcc.faculdade.com.vamosaprender.R;

public class GameOperacoesResumoActivity extends AppCompatActivity {
    private ImageView tutor,star1,star2,star3,speechBubble,next;
    private Button restartBtn, endGameBtn;
    private TextView newRecordTxt, speechText;
    private String []phrases;
    private Resources res;
    private int score;
    SharedPreferences scoreSalvo;
    SharedPreferences.Editor editor;
    Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_operacoes_resumo);

        tutor = findViewById(R.id.myTutor);
        star1 = findViewById(R.id.estrela1);
        star2 = findViewById(R.id.estrela2);
        star3 = findViewById(R.id.estrela3);
        speechBubble = findViewById(R.id.speechBubble);
        speechText = findViewById(R.id.speechPhrases);
        restartBtn = findViewById(R.id.replayButton);
        endGameBtn = findViewById(R.id.endGameButton);
        newRecordTxt = findViewById(R.id.recordID);
        next = findViewById(R.id.nextId);
        res = getResources();
        phrases = res.getStringArray(R.array.end_game_operacoes);
        restartBtn.setEnabled(false);
        endGameBtn.setEnabled(false);
       // font = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
      //  newRecordTxt.setTypeface(font);

        init();
    }
    private void init(){
        Bundle extra = getIntent().getExtras();
        score = extra.getInt("score");
        scoreSalvo = getSharedPreferences("scoreGameOperacoes", MODE_PRIVATE);
        editor = scoreSalvo.edit();
        int aux;
        if(score <= 250 ){
            star1.setImageDrawable(res.getDrawable(R.drawable.estrelabrilhante));
            star2.setImageDrawable(res.getDrawable(R.drawable.estrelaapagada));
            star3.setImageDrawable(res.getDrawable(R.drawable.estrelaapagada));
            aux = 0;
        }else if(score > 250 && score <= 500){
            star1.setImageDrawable(res.getDrawable(R.drawable.estrelabrilhante));
            star2.setImageDrawable(res.getDrawable(R.drawable.estrelabrilhante));
            star3.setImageDrawable(res.getDrawable(R.drawable.estrelaapagada));
            aux = 1;
        }else {
            star1.setImageDrawable(res.getDrawable(R.drawable.estrelabrilhante));
            star2.setImageDrawable(res.getDrawable(R.drawable.estrelabrilhante));
            star3.setImageDrawable(res.getDrawable(R.drawable.estrelabrilhante));
            aux = 2;
        }

        starAnimations(aux);
    }

    private void starAnimations(final int auxV) {

        final Animation bubbleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);
        final Animation phraseAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);
        final Animation star_2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.estrela_2);
        final Animation star_3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.estrela_3);

        speechBubble.setAlpha((float) 1.0);
        bubbleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                speechText.setVisibility(View.VISIBLE);
                speechBubble.setVisibility(View.VISIBLE);
                tutor.setVisibility(View.VISIBLE);
                star1.setVisibility(View.VISIBLE);
                star2.setVisibility(View.VISIBLE);
                star3.setVisibility(View.VISIBLE);

                speechText.setText(phrases[auxV]);
                speechText.setAlpha((float) 1.0);
                speechText.startAnimation(phraseAnim);
                tutor.startAnimation(phraseAnim);
                star1.startAnimation(phraseAnim);
                star2.startAnimation(star_2);
                star3.startAnimation(star_3);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        speechText.setVisibility(View.GONE);
                        speechBubble.setVisibility(View.GONE);
                        next.setVisibility(View.GONE);
                        if(score <= scoreSalvo.getInt("HighScore",0)) {
                            newRecordTxt.setText(String.format(res.getString(R.string.record),score));
                        }else{
                            newRecordTxt.setText(String.format(res.getString(R.string.new_record),score));
                        }
                        newRecordTxt.setVisibility(View.VISIBLE);
                        restartBtn.setEnabled(true);
                        endGameBtn.setEnabled(true);
                        restartBtn.setVisibility(View.VISIBLE);
                        endGameBtn.setVisibility(View.VISIBLE);
                    }
                });

                setListeners();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        speechBubble.startAnimation(bubbleAnim);
    }

    private void setListeners(){
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(GameOperacoesResumoActivity.this, GameOperacoes.class));
            }
        });

        endGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
