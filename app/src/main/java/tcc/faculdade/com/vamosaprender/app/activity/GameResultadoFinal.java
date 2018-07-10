package tcc.faculdade.com.vamosaprender.app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import tcc.faculdade.com.vamosaprender.R;

public class GameResultadoFinal extends AppCompatActivity {

    private ImageView tutor;
    private ImageView next;
    TextView speechPrhases, scoreText, contagem, titulo, textRA, valor, instrucaoText;
    private ImageView bubble;
    private Typeface font;
    int score, i, a, b, c, d, ant, ciclo, highScore;
    Resources res;
    String[] phrases, easyStart, easyInstrucao, hardStart, hardInstrucao;
    SharedPreferences scoreSalvo, loginArmazenado, ultimoScore;
    SharedPreferences.Editor editor;
    private Button instrucaoButton, op1, op2, op3, op4;
    boolean ePrimeira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_resultado_final);

        init();
        starAnimations();
    }

    private void init() {
        scoreSalvo = getSharedPreferences("highScoreResultado", MODE_PRIVATE);
        loginArmazenado = getSharedPreferences("loginArmazenado", MODE_PRIVATE);
        ultimoScore = getSharedPreferences("lastScoreResultado", MODE_PRIVATE);
        speechPrhases = findViewById(R.id.speechPhrasesGame2);
        bubble = findViewById(R.id.speechBubbleGame2R);
        next = findViewById(R.id.nextIdGame2);
        tutor = findViewById(R.id.myTutorGame2R);
        scoreText = findViewById(R.id.scoreGame2);
        score = 0;
        scoreText.setText(String.format("%1$d", score));
        contagem = findViewById(R.id.contagemGame2);
        ciclo = 0;
        res = getResources();
        phrases = res.getStringArray(R.array.game_resultado_final);
        easyStart = res.getStringArray(R.array.easy_start_instrucoes_game_resultado_final);
        easyInstrucao = res.getStringArray(R.array.easy_instrucoes_game_resultado_final);
        hardStart = res.getStringArray(R.array.hard_start_instrucoes_game_resultado_final);
        hardInstrucao = res.getStringArray(R.array.hard_instrucoes_game_resultado_final);
        ePrimeira = true;

        font = Typeface.createFromAsset(getAssets(), "fonts/iceland.ttf");
        titulo = findViewById(R.id.tituloGame2);
        titulo.setTypeface(font);

        textRA = findViewById(R.id.textResultAntGame2);
        textRA.setTypeface(font);
        textRA.setVisibility(View.GONE);
        valor = findViewById(R.id.resultAntGame2);
        valor.setVisibility(View.GONE);
        instrucaoText = findViewById(R.id.textoInstrucaoGame2);

        instrucaoButton = findViewById(R.id.instrucaoGame2);
        op1 = findViewById(R.id.opcao1Game2);
        op2 = findViewById(R.id.opcao2Game2);
        op3 = findViewById(R.id.opcao3Game2);
        op4 = findViewById(R.id.opcao4Game2);
        instrucaoButton.setVisibility(View.GONE);
        instrucaoButton.setEnabled(false);
        instrucaoText.setVisibility(View.GONE);
        op1.setVisibility(View.GONE);
        op1.setEnabled(false);
        op2.setVisibility(View.GONE);
        op2.setEnabled(false);
        op3.setVisibility(View.GONE);
        op3.setEnabled(false);
        op4.setVisibility(View.GONE);
        op4.setEnabled(false);
    }

    private void starAnimations() {

        final Animation bubbleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);
        final Animation phraseAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);


        bubble.setAlpha((float) 1.0);
        bubbleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                i = 0;
                speechPrhases.setText(String.format(phrases[0], loginArmazenado.getString("Nome", "Aluno")));
                speechPrhases.setAlpha((float) 1.0);
                speechPrhases.startAnimation(phraseAnim);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //Evento de click associado ao botao next
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i++;
                        if (i < phrases.length) {
                            speechPrhases.setText(phrases[i]);

                        } else {
                            next.setVisibility(View.GONE);
                            next.setEnabled(false);
                            tutor.setVisibility(View.GONE);
                            tutor.setEnabled(false);
                            speechPrhases.setVisibility(View.GONE);
                            speechPrhases.setEnabled(false);
                            bubble.setVisibility(View.GONE);
                            bubble.setEnabled(false);
                            gameAnimations();
                        }
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bubble.startAnimation(bubbleAnim);
    }

    public void gameAnimations() {
        Animation operacoesAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.operacoes);
        operacoesAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                instrucaoButton.setVisibility(View.VISIBLE);
                instrucaoButton.setEnabled(false);
                instrucaoText.setVisibility(View.VISIBLE);
                instrucaoText.setEnabled(false);
                op1.setVisibility(View.VISIBLE);
                op1.setBackgroundResource(R.drawable.operacoes_bordas);
                op2.setVisibility(View.VISIBLE);
                op2.setBackgroundResource(R.drawable.operacoes_bordas);
                op3.setVisibility(View.VISIBLE);
                op3.setBackgroundResource(R.drawable.operacoes_bordas);
                op4.setVisibility(View.VISIBLE);
                op4.setBackgroundResource(R.drawable.operacoes_bordas);
                ciclo++;
                contagem.setText("" + ciclo);
                geraInstrucao();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                op1.setEnabled(true);
                op2.setEnabled(true);
                op3.setEnabled(true);
                op4.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        instrucaoButton.startAnimation(operacoesAnim);
        instrucaoText.startAnimation(operacoesAnim);
        op1.startAnimation(operacoesAnim);
        op2.startAnimation(operacoesAnim);
        op3.startAnimation(operacoesAnim);
        op4.startAnimation(operacoesAnim);
    }

    public void geraInstrucao() {
        Random random = new Random();
        int level, ins;
        if (ePrimeira) {
            level = random.nextInt(2);
            //início fácil
            if (level == 0) {
                ins = random.nextInt(2);
                //<item>Comece com o quociente de %1$d : %2$d.</item>
                //<item>Comece com o produto de %1$d X %2$d.</item>
                if (ins == 0) {
                    c = random.nextInt(1000) + 1;
                    b = random.nextInt(9) + 1;
                    a = b * c;
                    instrucaoButton.setText(String.format(easyStart[0], a, b));
                    Log.e("1: ", "1");
                } else {
                    a = random.nextInt(9) + 1;
                    b = random.nextInt(100) + 1;
                    c = a * b;
                    instrucaoButton.setText(String.format(easyStart[1], a, b));
                    Log.e("2: ", "2");
                }
                Toast.makeText(getApplicationContext(), "" + c, Toast.LENGTH_SHORT).show();
                geraOpcoes(c);
            }
            //inicio difícil
            else {
                ins = random.nextInt(3);
                //<item>Comece com o dobro do quociente de %1$d : %2$d.</item>
                //<item>Comece com o triplo do quociente de %1$d : %2$d.</item>
                //<item>Comece com o quádruplo do quociente de %1$d : %2$d.</item>
                if (ins == 0) {
                    d = random.nextInt(100) + 1;
                    b = random.nextInt(9) + 1;
                    a = b * d;
                    c = 2 * d;
                    instrucaoButton.setText(String.format(hardStart[0], a, b));
                } else if (ins == 1) {
                    d = random.nextInt(100) + 1;
                    b = random.nextInt(9) + 1;
                    a = b * d;
                    c = 3 * d;
                    instrucaoButton.setText(String.format(hardStart[1], a, b));
                } else if (ins == 2) {
                    d = random.nextInt(100) + 1;
                    b = random.nextInt(9) + 1;
                    a = b * d;
                    c = 4 * d;
                    instrucaoButton.setText(String.format(hardStart[2], a, b));
                }
                Toast.makeText(getApplicationContext(), "" + c, Toast.LENGTH_SHORT).show();
                geraOpcoes(c);
            }
            ePrimeira = false;
        } else {
            ant = c;
            level = random.nextInt(3);
            //level = 1;
            boolean ePrimo = true;
            //sequencia facil
            if (level <= 1) {
                ins = random.nextInt(4);
                //<item>Adicione: %1$d.</item>
                //<item>Subtraia: %1$d</item>
                //<item>Divida por: %1$d.</item>
                //<item>Multiplique por: %1$d.</item>
                if (ins == 0) {
                    b = random.nextInt(1000) + 1;
                    c = c + b;
                    instrucaoButton.setText(String.format(easyInstrucao[ins], b));
                } else if (ins == 1) {
                    if (c <= 1) {
                        b = 0;
                        c = c - b;
                        instrucaoButton.setText(String.format(easyInstrucao[ins], b));
                    } else {
                        b = random.nextInt(c - 1) + 1;
                        c = c - b;
                        instrucaoButton.setText(String.format(easyInstrucao[ins], b));
                    }
                } else if (ins == 2) {
                    d = 2;
                    while (d <= c / 2) {
                        if (c % d == 0) {
                            ePrimo = false;
                            do {
                                b = random.nextInt((int) c / 2) + 1;
                            } while (c % b != 0);
                            c = c / b;
                            instrucaoButton.setText(String.format(easyInstrucao[ins], b));
                            break;
                        }
                        d++;
                    }
                    if (ePrimo) {
                        //c = c / c;
                        b = c;
                        c = 1;
                        instrucaoButton.setText(String.format(easyInstrucao[ins], b));
                    }
                } else {
                    b = random.nextInt(20) + 1;
                    c = c * b;
                    instrucaoButton.setText(String.format(easyInstrucao[ins], b));
                }
            } else {
                //sequencia dificil
                int aux;
                ins = random.nextInt(4);
                //<item>Subtraia produto de %1$d X %2$d.</item>
                //<item>Subtraia quociente de %1$d : %2$d.</item>
                //<item>Adcione produto de %1$d X %2$d.</item>
                //<item>Adicione quociente de %1$d : %2$d.</item>
                if (ins == 0) {
                    do {
                        d = c;
                        a = random.nextInt((int) Math.sqrt(c)) + 1;
                        b = random.nextInt((int) Math.sqrt(c)) + 1;
                        d = a * b;
                    } while (c <= d);
                    c = c - d;
                    instrucaoButton.setText(String.format(hardInstrucao[ins], a, b));
                } else if (ins == 1) {
                    aux = c;
                    do {
                        d = random.nextInt(c) + 1;
                        aux = c - d;
                    } while (aux <= 0);
                    b = random.nextInt(20) + 1;
                    a = b * d;
                    c = c - d;
                    instrucaoButton.setText(String.format(hardInstrucao[ins], a, b));
                } else if (ins == 2) {
                    d = c;
                    a = random.nextInt((int) Math.sqrt(c)) + 1;
                    b = random.nextInt(500) + 1;
                    d = a * b;
                    c = c + d;
                    instrucaoButton.setText(String.format(hardInstrucao[ins], a, b));
                } else {
                    d = random.nextInt(100) + 1;
                    b = random.nextInt(20) + 1;
                    a = b * d;
                    c = c + d;
                    instrucaoButton.setText(String.format(hardInstrucao[ins], a, b));
                }
            }
            Toast.makeText(getApplicationContext(), "" + c, Toast.LENGTH_SHORT).show();
            geraOpcoes(c);
        }
    }

    public void geraOpcoes(int resultado) {
        Random random = new Random();
        int auxResul;
        auxResul = resultado - random.nextInt(25) + 1;
        op1.setText(String.format("%1$d", auxResul));
        auxResul = resultado + random.nextInt(11) + 1;
        op2.setText(String.format("%1$d", auxResul));
        auxResul = resultado - random.nextInt(39) + 1;
        op3.setText(String.format("%1$d", auxResul));
        auxResul = resultado + random.nextInt(50) + 1;
        op4.setText(String.format("%1$d", auxResul));
        switch (random.nextInt(4) + 1) {
            case 1:
                op1.setText(String.format("%1$d", resultado));
                break;
            case 2:
                op2.setText(String.format("%1$d", resultado));
                break;
            case 3:
                op3.setText(String.format("%1$d", resultado));
                break;
            case 4:
                op4.setText(String.format("%1$d", resultado));
                break;
        }
        if (!ePrimeira) {
            valor.setVisibility(View.VISIBLE);
            valor.setText(String.format("%1$d", ant));
            textRA.setVisibility(View.VISIBLE);
        }
        setListenners(resultado);
    }

    public void setListenners(final int resultado) {
        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(op1.getText().toString()) == resultado) {
                    score += 50;
                    scoreText.setText("" + score);
                    if(ciclo == 10){
                        endGame();
                    }
                    gameAnimations();
                } else {
                    score -= 50;
                    if (score < 0) {
                        score = 0;
                    }
                    scoreText.setText("" + score);
                    op1.setBackgroundResource(R.drawable.operacoes_resposta_errada);
                    op1.setEnabled(false);
                }
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(op2.getText().toString()) == resultado) {
                    score += 50;
                    scoreText.setText("" + score);
                    if(ciclo == 10){
                        endGame();
                    }
                    gameAnimations();
                } else {
                    score -= 50;
                    if (score < 0) {
                        score = 0;
                    }
                    scoreText.setText("" + score);
                    op2.setBackgroundResource(R.drawable.operacoes_resposta_errada);
                    op2.setEnabled(false);
                }
            }
        });
        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(op3.getText().toString()) == resultado) {
                    score += 50;
                    scoreText.setText("" + score);
                    if(ciclo == 10){
                        endGame();
                    }
                    gameAnimations();
                } else {
                    score -= 50;
                    if (score < 0) {
                        score = 0;
                    }
                    scoreText.setText("" + score);
                    op3.setBackgroundResource(R.drawable.operacoes_resposta_errada);
                    op3.setEnabled(false);
                }
            }
        });
        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(op4.getText().toString()) == resultado) {
                    score += 50;
                    scoreText.setText("" + score);
                    if(ciclo == 10){
                        endGame();
                    }
                    gameAnimations();
                } else {
                    score -= 50;
                    if (score < 0) {
                        score = 0;
                    }
                    scoreText.setText("" + score);
                    op4.setBackgroundResource(R.drawable.operacoes_resposta_errada);
                    op4.setEnabled(false);
                }
            }
        });
    }

    public void endGame() {
        if (scoreSalvo.getInt("highScoreResultado", 0) <= score) {
            editor = scoreSalvo.edit();
            editor.putInt("highScoreResultado", score);
            editor.apply();
            Toast.makeText(getApplicationContext(), "Novo Record", Toast.LENGTH_SHORT).show();
        }

        editor = ultimoScore.edit();
        editor.putInt("lastScoreResultado",score);
        editor.apply();

        Intent it = new Intent(GameResultadoFinal.this, GameResultadoResumo.class);
        it.putExtra("score", score);
        startActivity(it);
        finish();
    }
}
