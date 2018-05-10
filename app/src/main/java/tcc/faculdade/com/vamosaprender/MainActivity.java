package tcc.faculdade.com.vamosaprender;

import android.content.res.Resources;
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

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = (Button) findViewById(R.id.playButton);
        Button stop = (Button) findViewById(R.id.profileButton);
        final TextView speechPrhases = (TextView) findViewById(R.id.speechPhrases);
        final ImageView bubble = (ImageView) findViewById(R.id.speechBubble);
        final Animation bubbleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);
        final Animation phraseAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bubble.setAlpha((float) 1.0);


                bubbleAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Resources res = getResources();
                        String[] phrases = res.getStringArray(R.array.start_prhases);
                        speechPrhases.setText(phrases[0]);
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
        });


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bubble.clearAnimation();
            }
        });

    }
}
