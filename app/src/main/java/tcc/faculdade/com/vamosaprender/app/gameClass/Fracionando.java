package tcc.faculdade.com.vamosaprender.app.gameClass;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Fracionando extends View implements Runnable{

    private boolean update;
    private int i;
    private Paint paint;

    public Fracionando(Context context) {
        super(context);
        init();

    }

    public void update(){
        if(update){
            i++;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("valor do i: "+i,500,200,paint);

    }

    public void init(){
        update = true;
        i = 0;
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(80);


    }

    @Override
    public void run() {
        while(true){
            try {
                update();
                postInvalidate();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
