package C22414306;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ie.tudublin.*;

public class ConorVisual extends Visual
{

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
    float lerpedAverage = 0;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;


    public void keyPressed() 
    {
        if (key >= '0' && key <= '9')
        {
			mode = key - '0';
		}
		if (keyCode == ' ') 
        {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
	}

    public void settings()
    {
        size(1024, 1000, P3D);
    }

    public void setup()
    {

        minim = new Minim(this);
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
        // set colour to black
        //
        background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // loop through each sample in the audio buffer
        //
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        // calculate the average of the sampples
        //
        average= sum / (float) ab.size();
        // Smooth the average value over time using the lerp function
        //
        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        // calculate the centre point of the visualisation
        //
        float cx = width / 2;
        float cy = height / 2;

        switch (mode) 
        {
            case 1:

                background(0);
                strokeWeight(2);
            
                // Calculate overall average value
                float total1 = 0;

                for(int i = 0; i < ab.size(); i++) 
                {
                    total1 += abs(ab.get(i));
                }
                
                float overallAverage = total1 / (float) ab.size();
                // Map average value to hue
                //
                float hue = map(overallAverage, 0, 1, 0, 360);
                
                float saturation = 255; 
                float brightness = 255; 
                // Set fill to none for the central circle
                //
                noFill();
                // Set stroke color using HSB color mode
                //
                stroke(hue, saturation, brightness); 
                float radius = overallAverage * halfH * 2;
                // Draw central circle at the center of the canvas
                //
                ellipse(width/2, height/2, radius, radius);

                break;
        
            default:
                break;
        }

   }

}