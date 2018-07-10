package tcc.faculdade.com.vamosaprender.app.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tcc.faculdade.com.vamosaprender.R;

public class SelecaoActivity extends AppCompatActivity {

    ImageView jogo1, jogo2;
    TextView jogoText1, jogoText2;
    Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao);

        init();
    }

    public void init(){
        jogo1 = findViewById(R.id.selecaoOperacoes);
        jogo2 = findViewById(R.id.selecaoTrilha);
        jogoText1 = findViewById(R.id.selecaoText1);
        jogoText2 = findViewById(R.id.selecaoText2);
        font = Typeface.createFromAsset(getAssets(), "fonts/iceland.ttf");
        jogoText1.setTypeface(font);
        jogoText2.setTypeface(font);

        setlistenners();
    }
    public void setlistenners(){
        jogo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelecaoActivity.this,GameOperacoes.class));
                finish();
            }
        });

        jogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelecaoActivity.this,GameResultadoFinal.class));
                finish();
            }
        });
    }
}
