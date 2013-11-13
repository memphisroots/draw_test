package com.example.live_wall;
import com.example.live_wall.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;


public class LiveWallpaperService extends WallpaperService  {
	  float x,y,r;
	  int moveside,movelong;
	  Paint paint = new Paint();
	 
      
      public void onCreate()
      {
              super.onCreate();
      }

      public void onDestroy()
      {
              super.onDestroy();
      }

      public Engine onCreateEngine()
      {
              return new MyWallpaperEngine();
      }

      class MyWallpaperEngine extends Engine
      {

              private final Handler handler = new Handler();
              private final Runnable drawRunner = new Runnable() {
                  @Override
                  public void run() {
                      draw();
                  }
              };
              private boolean visible = true;
              public Bitmap image1,backgroundImage;

              MyWallpaperEngine()
              {
                       // get the fish and background image references
                      image1 = BitmapFactory.decodeResource(getResources(),R.drawable.fish);
                      backgroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.background);
                      x=30; // initialize x position
                      y=30;  // initialize y position
                      r=20;
                      moveside = 2;
                      movelong = 5;
                     
              }


              public void onCreate(SurfaceHolder surfaceHolder)
              {
                      super.onCreate(surfaceHolder);
              }

              @Override
              public void onVisibilityChanged(boolean visible)
              {
                      this.visible = visible;
                      // if screen wallpaper is visible then draw the image otherwise do not draw
                      if (visible)
                      {
                          handler.post(drawRunner);
                      }
                      else
                      {
                          handler.removeCallbacks(drawRunner);
                      }
              }

              @Override
              public void onSurfaceDestroyed(SurfaceHolder holder)
              {
                      super.onSurfaceDestroyed(holder);
                      this.visible = false;
                      handler.removeCallbacks(drawRunner);
              }

              public void onOffsetsChanged(float xOffset, float yOffset, float xStep, float yStep, int xPixels, int yPixels)
              {
                      draw();
              }

              void draw()
              {
                      final SurfaceHolder holder = getSurfaceHolder();
       
                      Canvas c = null;
                      try
                      {
                              c = holder.lockCanvas();
                              // clear the canvas
                              c.drawColor(Color.BLACK);
                              if (c != null)
                              {
                                      // draw the background image
                                      //c.drawBitmap(backgroundImage, 0, 0, null);
                                      // draw the fish
                                     // c.drawBitmap(image1, x,y, null);
                                      // get the width of canvas
                                      int width=c.getWidth();
                                      paint.setColor(Color.BLUE);
                          	          paint.setStrokeWidth(3);
                          	          
                          	          
                          	           x += moveside;
                        	           y += movelong;
                        	           r += 0.3;
                        	           
                                      // if x crosses the width means  x has reached to right edge
                                      if(x>500 || x < 30)
                                      {  
                                              // assign initial value to start with
                                              moveside *= -1;
                                              r = 20;
                                      }
                                      if(y>800 || y< 30){
                                    	  movelong*=-1;
                                    	  r= 20;
                                      }
                                      // change the x position/value by 1 pixel
                                      c.drawCircle(x, y, r, paint);
                              }
                       }
                      finally
                      {
                              if (c != null)
                                     holder.unlockCanvasAndPost(c);
                      }

                      handler.removeCallbacks(drawRunner);
                      if (visible)
                      {
                                handler.postDelayed(drawRunner, 10); // delay 10 mileseconds
                      }   

              }
      }
}
