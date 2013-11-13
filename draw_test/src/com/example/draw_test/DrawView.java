package com.example.draw_test;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class DrawView extends ImageView {

	Paint paint = new Paint();
	
	private float x = 30;
	private float y = 30;
	private float rad = 20;
	int bottom = 80;
	private Handler h;
	private final int FRAME_RATE = 10;
	
	int width = this.getWidth();
	int height = this.getHeight();
	
	int moveside = 5;
	int movelong = 3;
	Random R = new Random();
	 
	public DrawView(Context context) {
		super(context);
		h = new Handler();
		// TODO Auto-generated constructor stub
	}
	
	public DrawView(Context context,AttributeSet attrs) {
		super(context,attrs);
		h = new Handler();
		// TODO Auto-generated constructor stub
	}
	
    private Runnable r = new Runnable() {

        @Override

        public void run() {

                invalidate();

        }

};
	
	  @Override
	    public void onDraw(Canvas canvas) {
	        paint.setColor(Color.BLUE);
	        paint.setStrokeWidth(3);
	        //right += moveside;
	        
	        x += moveside;
	        y += movelong;
	        
	        if(x > 525 || x < 30){
	        	
	        	//right += moveside;
                 paint.setColor(Color.WHITE);
	        	
	        	canvas.drawText(Float.toString(moveside), x - 50, 10, paint);
	        	canvas.drawText(Float.toString(x), x - 50, 30, paint);
	        	moveside *= -1;
	        }
	       // else{
	       // 	moveside *= -1;
	       // }
	        
	       
	        
	        if(y > 810 || y < 30){
	        	//top += movelong;
	        	movelong*= -1;
	        }
	        //else{
	        	
	        	//paint.setColor(Color.GREEN);
	       // }
	        //paint.setStrokeWidth(0);
	        //paint.setColor(Color.CYAN);
	        //canvas.drawRect(33, 60, 77, 77, paint );
	        //paint.setColor(Color.YELLOW);
	        //canvas.drawRect(33, 33, 77, 60, paint );
	        paint.setColor(Color.BLACK);
        	//canvas.drawRect(30, 30, 1000, 1000, paint);
        	paint.setColor(Color.BLUE);
	        canvas.drawCircle(x, y, rad, paint);
	        h.postDelayed(r, FRAME_RATE);
	    }
	  
	  public boolean onTouchEvent (MotionEvent event) {
	        switch (event.getAction()) {
	            case MotionEvent.ACTION_DOWN :
	                x = event.getX();
	                y = event.getY();
	                break;
	        }
	        return true;
	    }
	  
  
}


