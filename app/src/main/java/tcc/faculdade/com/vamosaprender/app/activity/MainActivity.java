package tcc.faculdade.com.vamosaprender.app.activity;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import tcc.faculdade.com.vamosaprender.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play =  findViewById(R.id.playButton);
        Button stop =  findViewById(R.id.profileButton);

        starAnimations();
    }


    //Função das animações de entrada
    private void starAnimations() {
        final TextView speechPrhases = findViewById(R.id.speechPhrases);
        final ImageView bubble = findViewById(R.id.speechBubble);
        final Animation bubbleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);
        final Animation phraseAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);

        bubble.setAlpha((float) 1.0);
        bubbleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Resources res = getResources();
                String[] phrases = res.getStringArray(R.array.start_prhases);
                speechPrhases.setText(phrases[0] + " " + getString(R.string.profile_name).split(" ", 0));
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

