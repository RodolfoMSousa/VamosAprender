package tcc.faculdade.com.vamosaprender.app.activity;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import tcc.faculdade.com.vamosaprender.R;

public class ProfileActivity extends AppCompatActivity {

    TextView titulo, labelNome, labelRecord, labellastScore, jogo1, jogo2, record1, record2, score1, score2;
    private Typeface font;
    SharedPreferences ultimoScore1, ultimoScore2, recordSalvo1, recordSalvo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();
    }

    public void init() {
        titulo = findViewById(R.id.tituloProfile);
        labelNome = findViewById(R.id.labelJogo);
        labelRecord = findViewById(R.id.labelRecord);
        labellastScore = findViewById(R.id.labelUltimoScore);
        jogo1 = findViewById(R.id.nomeJogo1);
        jogo2 = findViewById(R.id.nomeJogo2);
        record1 = findViewById(R.id.record1);
        record2 = findViewById(R.id.record2);
        score1 = findViewById(R.id.score1);
        score2 = findViewById(R.id.score2);

        ultimoScore1 = getSharedPreferences("lastScoreOperacoes", MODE_PRIVATE);
        ultimoScore2 = getSharedPreferences("lastScoreResultado", MODE_PRIVATE);
        recordSalvo1 = getSharedPreferences("scoreGameOperacoes", MODE_PRIVATE);
        recordSalvo2 = getSharedPreferences("highScoreResultado", MODE_PRIVATE);

        font = Typeface.createFromAsset(getAssets(), "fonts/iceland.ttf");
        titulo.setTypeface(font);
        titulo.setTextColor(Color.GREEN);
        labelNome.setTypeface(font);
        labelNome.setTextColor(Color.BLACK);
        labelRecord.setTypeface(font);
        labelRecord.setTextColor(Color.BLACK);
        labellastScore.setTypeface(font);
        labellastScore.setTextColor(Color.BLACK);
        labelNome.setTypeface(font);
        labelNome.setTextColor(Color.BLACK);
        jogo1.setTypeface(font);
        jogo1.setTextColor(Color.BLACK);
        jogo2.setTypeface(font);
        jogo2.setTextColor(Color.BLACK);

        try {

            score1.setText(String.format("%1$d",ultimoScore1.getInt("lastScoreOperacoes", 0)));
        } catch (Exception e) {
            score1.setText(String.format("%1$d",0));
        }
        try {

            score2.setText(String.format("%1$d",ultimoScore2.getInt("lastScoreResultado", 0)));
        } catch (Exception e) {
            score2.setText(String.format("%1$d",0));
        }
        try {

            record1.setText(String.format("%1$d",recordSalvo1.getInt("scoreGameOperacoes", 0)));
        } catch (Exception e) {
            record1.setText(String.format("%1$d",0));
        }
        try {

            record2.setText(String.format("%1$d",recordSalvo2.getInt("highScoreResultado", 0)));
        } catch (Exception e) {
            record2.setText(String.format("%1$d",0));
        }

    }
}