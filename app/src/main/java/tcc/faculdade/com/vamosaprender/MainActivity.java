package tcc.faculdade.com.vamosaprender;

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
        final TextView name = (TextView) findViewById(R.id.name);
        final ImageView bubble = (ImageView) findViewById(R.id.speechBubble);
        final Animation bub = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);
        final Animation bub2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bubble_main);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bubble.setAlpha((float) 1.0);


                bub.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Toast.makeText(getApplicationContext(), "deu", Toast.LENGTH_LONG).show();
                        name.startAnimation(bub2);                   }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                bubble.startAnimation(bub);



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
