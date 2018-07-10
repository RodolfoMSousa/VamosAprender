package tcc.faculdade.com.vamosaprender.app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import tcc.faculdade.com.vamosaprender.R;

public class GameOperacoes extends AppCompatActivity {

    private ImageView tutor;
    private ImageView next;
    private TextView speechPrhases, titulo, scoreText, contagem;
    private ImageView bubble;
    private Typeface font;
    Resources res;
    String[] phrases;
    private int i, ciclo, score, highScore, acertos, erros, jogoId;
    private Button ex, op1, op2, op3, op4;
    SharedPreferences scoreSalvo, loginArmazenado;
    SharedPreferences ultimoScore;
    SharedPreferences.Editor editor;
    public SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_operacoes);
        init();
        starAnimations();
    }

    private void init() {
        jogoId = 1;
        scoreSalvo = getSharedPreferences("scoreGameOperacoes", MODE_PRIVATE);
        loginArmazenado = getSharedPreferences("loginArmazenado", MODE_PRIVATE);
        ultimoScore = getSharedPreferences("lastScoreOperacoes", MODE_PRIVATE);
        editor = scoreSalvo.edit();
        speechPrhases = findViewById(R.id.speechPhrases);
        bubble = findViewById(R.id.speechBubbleGame2R);
        next = findViewById(R.id.nextId);
        tutor = findViewById(R.id.myTutorGame2R);
        font = Typeface.createFromAsset(getAssets(), "fonts/iceland.ttf");
        titulo = findViewById(R.id.tituloGame);
        titulo.setTypeface(font);
        titulo.setTextColor(Color.BLACK);
        scoreText = findViewById(R.id.score);
        score = 0;
        scoreText.setText("" + score);
        contagem = findViewById(R.id.contagem);
        highScore = i = 0;
        acertos = erros = ciclo = 0;
        res = getResources();
        phrases = res.getStringArray(R.array.game_operacoes);
        ex = findViewById(R.id.containeroOperacaoId);
        op1 = findViewById(R.id.opcao1);
        op2 = findViewById(R.id.opcao2);
        op3 = findViewById(R.id.opcao3);
        op4 = findViewById(R.id.opcao4);
        ex.setVisibility(View.GONE);
        ex.setEnabled(false);
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
                ex.setVisibility(View.VISIBLE);
                ex.setEnabled(false);
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
                geraOperacoes();
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
        ex.startAnimation(operacoesAnim);
        op1.startAnimation(operacoesAnim);
        op2.startAnimation(operacoesAnim);
        op3.startAnimation(operacoesAnim);
        op4.startAnimation(operacoesAnim);

    }

    private void geraOperacoes() {
        Random random = new Random();
        int operacao, operando1, operando2, resultado, auxResul, operando3;
        //caso menos que 6 é o primeiro cilco, entao vem as mais fáceis
        if (ciclo < 6) {
            operacao = random.nextInt(2);
            //caso 0 é positivo
            if (operacao == 0) {
                operando1 = random.nextInt(10000);
                operando2 = random.nextInt(1000);
                resultado = operando1 + operando2;
                ex.setText(operando1 + " + " + operando2);
                auxResul = resultado - random.nextInt(20) + 1;
                op1.setText("" + auxResul);
                auxResul = resultado - random.nextInt(20) + 1;
                op2.setText("" + auxResul);
                auxResul = resultado - random.nextInt(20) + 1;
                op3.setText("" + auxResul);
                auxResul = resultado - random.nextInt(20) + 1;
                op4.setText("" + auxResul);
                switch (random.nextInt(4) + 1) {
                    case 1:
                        op1.setText("" + resultado);
                        break;
                    case 2:
                        op2.setText("" + resultado);
                        break;
                    case 3:
                        op3.setText("" + resultado);
                        break;
                    case 4:
                        op4.setText("" + resultado);
                        break;
                }
                //caso operando se 1 e portanto negativo
            } else {
                operando1 = random.nextInt(1000);
                operando2 = random.nextInt(1000);
                if (operando1 <= operando2) {
                    resultado = operando2 - operando1;
                    ex.setText(operando2 + " - " + operando1);
                    auxResul = resultado + random.nextInt(10) + 1;
                    op1.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(20) + 1;
                    op2.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(20) + 1;
                    op3.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(10) + 1;
                    op4.setText("" + auxResul);
                    switch (random.nextInt(4) + 1) {
                        case 1:
                            op1.setText("" + resultado);
                            break;
                        case 2:
                            op2.setText("" + resultado);
                            break;
                        case 3:
                            op3.setText("" + resultado);
                            break;
                        case 4:
                            op4.setText("" + resultado);
                            break;
                    }
                } else {
                    resultado = operando1 - operando2;
                    ex.setText(operando1 + " - " + operando2);
                    auxResul = resultado + random.nextInt(30) + 1;
                    op1.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(25) + 1;
                    op2.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(45) + 1;
                    op3.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(20) + 1;
                    op4.setText("" + auxResul);
                    switch (random.nextInt(4) + 1) {
                        case 1:
                            op1.setText("" + resultado);
                            break;
                        case 2:
                            op2.setText("" + resultado);
                            break;
                        case 3:
                            op3.setText("" + resultado);
                            break;
                        case 4:
                            op4.setText("" + resultado);
                            break;
                    }
                }
            }
            setListenners(resultado);


        } else if (ciclo > 5 && ciclo < 11) {

            operacao = random.nextInt(2);
            //caso 0 multiplicação
            if (operacao == 0) {
                operando1 = random.nextInt(1000);
                operando2 = random.nextInt(9) + 1;
                resultado = operando1 * operando2;
                ex.setText(operando1 + " * " + operando2);
                auxResul = resultado - random.nextInt(90) + 1;
                op1.setText("" + auxResul);
                auxResul = resultado - random.nextInt(45) + 1;
                op2.setText("" + auxResul);
                auxResul = resultado - random.nextInt(20) + 1;
                op3.setText("" + auxResul);
                auxResul = resultado - random.nextInt(60) + 1;
                op4.setText("" + auxResul);
                switch (random.nextInt(4) + 1) {
                    case 1:
                        op1.setText("" + resultado);
                        break;
                    case 2:
                        op2.setText("" + resultado);
                        break;
                    case 3:
                        op3.setText("" + resultado);
                        break;
                    case 4:
                        op4.setText("" + resultado);
                        break;
                }
                //caso seja divisão
            } else {
                resultado = random.nextInt(500) + 10;
                operando2 = random.nextInt(9) + 1;
                operando1 = resultado * operando2;
                ex.setText(operando1 + " : " + operando2);
                auxResul = resultado + random.nextInt(43) + 1;
                op1.setText("" + auxResul);
                auxResul = resultado + random.nextInt(45) + 1;
                op2.setText("" + auxResul);
                auxResul = resultado + random.nextInt(20) + 1;
                op3.setText("" + auxResul);
                auxResul = resultado + random.nextInt(60) + 1;
                op4.setText("" + auxResul);
                switch (random.nextInt(4) + 1) {
                    case 1:
                        op1.setText("" + resultado);
                        break;
                    case 2:
                        op2.setText("" + resultado);
                        break;
                    case 3:
                        op3.setText("" + resultado);
                        break;
                    case 4:
                        op4.setText("" + resultado);
                        break;
                }
            }
            setListenners(resultado);
        } else if (ciclo > 10 && ciclo < 16) {
            operacao = random.nextInt(2);
            //if 0 multiplicação
            if (operacao == 0) {

                operacao = random.nextInt(2);
                if (operacao == 0) {
                    operando1 = random.nextInt(9) + 1;
                    operando2 = random.nextInt(9) + 1;
                    operando3 = random.nextInt(9) + 1;
                    resultado = operando1 * operando2 * operando3;
                    ex.setText(operando1 + " * " + operando2 + " * " + operando3);

                    auxResul = resultado + random.nextInt(8) + 1;
                    op1.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(9) + 1;
                    op2.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(7) + 1;
                    op3.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(6) + 1;
                    op4.setText("" + auxResul);
                    switch (random.nextInt(4) + 1) {
                        case 1:
                            op1.setText("" + resultado);
                            break;
                        case 2:
                            op2.setText("" + resultado);
                            break;
                        case 3:
                            op3.setText("" + resultado);
                            break;
                        case 4:
                            op4.setText("" + resultado);
                            break;
                    }
                } else {
                    int divisore[] = new int[100];
                    int con = 0, a;
                    resultado = random.nextInt(9) + 1;
                    operando3 = random.nextInt(9) + 1;
                    auxResul = resultado * operando3;
                    for (int c = 1; c <= auxResul; c++) {
                        if (auxResul % c == 0) {
                            divisore[con] = c;
                            con++;
                        }
                    }
                    a = random.nextInt(con);
                    operando2 = divisore[a];
                    operando1 = auxResul / operando2;

                    ex.setText(operando1 + " * " + operando2 + " : " + operando3);

                    auxResul = resultado + random.nextInt(8) + 1;
                    op1.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(5) + 1;
                    op2.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(7) + 1;
                    op3.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(6) + 1;
                    op4.setText("" + auxResul);
                    switch (random.nextInt(4) + 1) {
                        case 1:
                            op1.setText("" + resultado);
                            break;
                        case 2:
                            op2.setText("" + resultado);
                            break;
                        case 3:
                            op3.setText("" + resultado);
                            break;
                        case 4:
                            op4.setText("" + resultado);
                            break;
                    }
                }
            }
            //Começou com divisão
            else {

                operacao = random.nextInt(2);
                if (operacao == 0) {
                    auxResul = random.nextInt(9) + 1;
                    operando2 = random.nextInt(9) + 1;
                    operando1 = auxResul * operando2;

                    operando3 = random.nextInt(9) + 1;
                    resultado = auxResul * operando3;
                    ex.setText(operando1 + " : " + operando2 + " * " + operando3);

                    auxResul = resultado + random.nextInt(8) + 1;
                    op1.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(5) + 1;
                    op2.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(7) + 1;
                    op3.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(6) + 1;
                    op4.setText("" + auxResul);
                    switch (random.nextInt(4) + 1) {
                        case 1:
                            op1.setText("" + resultado);
                            break;
                        case 2:
                            op2.setText("" + resultado);
                            break;
                        case 3:
                            op3.setText("" + resultado);
                            break;
                        case 4:
                            op4.setText("" + resultado);
                            break;
                    }
                } else {
                    resultado = random.nextInt(9) + 1;
                    operando3 = random.nextInt(9) + 1;
                    auxResul = resultado * operando3;
                    operando2 = random.nextInt(9) + 1;
                    operando1 = auxResul * operando2;

                    ex.setText(operando1 + " : " + operando2 + " : " + operando3);

                    auxResul = resultado + random.nextInt(8) + 1;
                    op1.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(5) + 1;
                    op2.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(7) + 1;
                    op3.setText("" + auxResul);
                    auxResul = resultado + random.nextInt(6) + 1;
                    op4.setText("" + auxResul);
                    switch (random.nextInt(4) + 1) {
                        case 1:
                            op1.setText("" + resultado);
                            break;
                        case 2:
                            op2.setText("" + resultado);
                            break;
                        case 3:
                            op3.setText("" + resultado);
                            break;
                        case 4:
                            op4.setText("" + resultado);
                            break;
                    }
                }
            }
            setListenners(resultado);
        }
    }


    public void setListenners(final int resultado) {
        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(op1.getText().toString()) == resultado) {
                    acertos++;
                    score += 50;
                    scoreText.setText("" + score);
                    if (ciclo == 15) {
                        endGame();
                    }
                    gameAnimations();
                } else {
                    erros++;
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
                    acertos++;
                    score += 50;
                    scoreText.setText("" + score);
                    if (ciclo == 15) {
                        endGame();
                    }
                    gameAnimations();
                } else {
                    erros++;
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
                    acertos++;
                    score += 50;
                    scoreText.setText("" + score);
                    if (ciclo == 15) {
                        endGame();
                    }
                    gameAnimations();
                } else {
                    erros++;
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
                    acertos++;
                    score += 50;
                    scoreText.setText("" + score);
                    if (ciclo == 15) {
                        endGame();
                    }
                    gameAnimations();
                } else {
                    erros++;
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
        if (scoreSalvo.getInt("scoreGameOperacoes", 0) <= score) {
            editor.putInt("scoreGameOperacoes", score);
            editor.apply();
            Toast.makeText(getApplicationContext(), "Novo Record", Toast.LENGTH_SHORT).show();
        }

        editor = ultimoScore.edit();
        editor.putInt("lastScoreOperacoes",score);
        editor.apply();

        Intent it = new Intent(GameOperacoes.this, GameOperacoesResumoActivity.class);
        it.putExtra("score", score);
        startActivity(it);
        finish();

    }

    /*public void salvaScore(){
        SharedPreferences usuarioSalvo = getSharedPreferences("loginArmazenado", MODE_PRIVATE);;

        try {
            db = openOrCreateDatabase("TCC", Context.MODE_PRIVATE, null);
            //db.execSQL("DROP TABLE login");
            db.execSQL("CREATE TABLE IF NOT EXISTS score " +
                    "(scoreId INTEGER PRIMARY KEY AUTOINCREMENT, jogoId INTEGER , alunoId INTEGER, pontuacao INTEGER, dataCadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, categoriaId INTEGER)");
            // db.execSQL("INSERT INTO login(usuarioId, userName, senha) VALUES ('" + l.getUsuarioId() + "','" + l.getUserName() + "','" + l.getSenha() + "')");
            db.execSQL("INSERT INTO score(jogoId, alunoId, pontuacao, categoriaId) VALUES ('" + jogoId + "','" + usuarioSalvo.getInt("id",0) + "','" + score +  "','"+ 1 +"')");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
