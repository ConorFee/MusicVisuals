package C22517166;

import ie.tudublin.Visual;

public class Sound1 extends Visual
{
    boolean moreCubes = false;

    public void settings()
    {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
        if (key == '1')
        {
            moreCubes = ! moreCubes;
        }
    }

   public void setup()
   {
       minim = new Minim(this);
       // Uncomment this to use the microphone
       // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
       // ab = ai.mix; 
       ap = minim.loadFile("stayinit.mp3", 1024);
       ap.play();
       ab = ap.mix;
       colorMode(HSB);

       y = height / 2;
       smoothedY = y;

       lerpedBuffer = new float[width];
   }

   float off = 0;
   float smoothedAverage = 0;
   float x = 0;

   public void draw()
   {
       background(0);
       float halfH = height / 2;
       float average = 0;
       float sum = 0;
       off += 1;
       // Calculate sum and average of the samples
       // Also lerp each element of buffer;
       for(int i = 0 ; i < ab.size() ; i ++)
       {
           sum += abs(ab.get(i));
           lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
       }
       
       average= sum / (float) ab.size();

       smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
       
       float cx = width / 2;
       float cy = height / 2;

       switch (mode)
       {
           case 0:

               background(0);
               strokeWeight(2);

               for(int i = 0 ; i < ab.size() ; i ++)
               {
                   float c = map(i, 0, ab.size(), 0, 255);
                   stroke(c, 255, 255);
                   lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
                   float s = lerpedBuffer[i] * halfH;
                   line(i, halfH - s, halfH + s, i);
               }
               
               float total = 0;

               for(int i = 0 ; i < ab.size() ; i ++)
               {
                   total += abs(ab.get(i));
               }
               
               average = total / (float) ab.size();
               lerpedAverage = lerp(lerpedAverage, average, 0.1f);
               
               break;

           case 1:

               background(0);
               stroke(255);
               for (int i = 0; i < ab.size(); i ++) 
               {
                   float c = map(i, 0, ab.size(), 0, 255); 
                   stroke(c, 255, 255);
                   float sample = ab.get(i) * (height / 2); 
                   line(i, height / 2 - sample, i, (height / 2) + sample); 
                   
                   sum += abs(ab.get(i)); 
               }

               average = sum / ab.size();
               smoothedAverage = lerp(off, average * 400, 0.1f);
               break;
           
           case 2:
           
               for (int i = 0; i < ab.size(); i ++)
               {
                   float sample = ab.get(i) * halfH;
                   stroke(map(i, 0, ab.size(), 0, 255), 255, 255);
                   //line(i, halfH + sample, i, halfH - sample); 
               
                   lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
               
                   sample = lerpedBuffer[i] * width * 2;    
                   stroke(map(i, 0, ab.size(), 0, 255), 255, 255);
                   line(0, i, sample, i); 
                   line(width, i, width - sample, i); 
                   line(i, 0, i, sample); 
                   line(i, height, i, height - sample);
               }
               break;

           case 3:
           
               float sum3 = 0;

               // Calculate sum of absolute values of audio samples
               //
               for (int i = 0; i < ab.size(); i++) {
                   sum3 += abs(ab.get(i));
               }
           
               // Calculate average of absolute values
               //
               average = sum3 / ab.size();
           
               // Apply lerping for smoothing
               //
               lerpedAverage = lerp(lerpedAverage, average, 0.1f);
           
               // Map lerpedAverage to color and draw a colored circle
               //
               noStroke();
               fill(map(lerpedAverage, 0, 1, 0, 255), 255, 255);
               float circleSize = lerpedAverage * 1500; // Adjust the multiplier for size
               ellipse(width / 2, height / 2, circleSize, circleSize);
               break;

           
           case 4:
               
               float sum4= 0;

               // Calculate sum of absolute values of audio samples
               //
               for (int i = 0; i < ab.size(); i++) {
                   sum4 += abs(ab.get(i));
               }
           
               // Calculate average of absolute values
               //
               average = sum4 / ab.size();
           
               // Apply lerping for smoothing
               //
               lerpedAverage = lerp(lerpedAverage, average, 0.1f);
           
               // Map lerpedAverage to color and draw a colored square
               //
               noStroke();
               fill(map(lerpedAverage, 0, 1, 0, 255), 255, 255);
               float squareSize = lerpedAverage * 1500; // Adjust the multiplier for size
               rectMode(CENTER);
               rect(width / 2, height / 2, squareSize, squareSize);
               break;
           
           case 5:

               break;
               
           
           
       }
       


       
       // Other examples we made in the class
       /*
       stroke(255);
       fill(100, 255, 255);        
       
       circle(width / 2, halfH, lerpedA * 100);

       circle(100, y, 50);
       y += random(-10, 10);
       smoothedY = lerp(smoothedY, y, 0.1f);        
       circle(200, smoothedY, 50);
       */

   }        
}


