package C22414306;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ie.tudublin.Visual;
//import processing.core.PApplet;

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

    @Override
    public void settings()
    {
        size(1200, 1200, P3D);
        //fullScreen(P3D, SPAN);
    }

    @Override
    public void setup()
    {
        minim = new Minim(this);
        
        ap = minim.loadFile("stayinit.mp3", 1024);
        ap.play(28000);
        /*ap.play(28000);
        if (ap.position() < 28000 || ap.position() > 76000) {
            ap.mute();
        }*/
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }

    float off = 0;
    float smoothedAverage = 0;
    float x = 0;
    public boolean started1 = false;
    public boolean started2 = false;

    @Override
    public void draw()
    {

        background(0);
        //float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        int startTime = millis();
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        
        average= sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        
        //float cx = width / 2;
        //float cy = height / 2;

        switch (mode)
        {
            case 0:

                background(0);
                // Variable declaration and initialization
                //
                float radiusStep = 1;
                double angleStep = 0.1;  
                float angle = 0; 
                float centerX = width / 2; 
                float centerY = height / 2; 

                for (int i = 0; i < ab.size(); i++)
                {
                    // Map audio data to circle size and color
                    //
                    float circleSize = abs(lerpedBuffer[i]) * 500;
                    // Map index to hue
                    //
                    float hue = map(i, 0, ab.size(), 0, 360);
                    float saturation = 255;
                    float brightness = 255; 

                    // Calculate x-coordinate and y-coordinate
                    float x = centerX + cos((float)angle) * (i * radiusStep);
                    float y = centerY + sin((float)angle) * (i * radiusStep); 
            
                    // Set circle color and draw circle
                    stroke(hue, saturation, brightness);
                    fill(hue, saturation, brightness);
                    ellipse(x, y, circleSize, circleSize);
            
                    // Increment angle for next circle
                    angle += angleStep;
                }                
                
                break;   
            
            default:

                break;
        }

        // Resume audio playback when the second track begins
        if (startTime > 76000 && startTime < 168000) {
            ap.pause(); // Pause the audio playback
        }
        
        if (startTime > 168000 && startTime <= 196000 && !started1) {
            ap.play(168000); // Resume audio playback
            started1 = true;
        } else if (startTime > 196000) {
            ap.pause(); // Pause audio playback
        }

        if (startTime > 238000 && startTime <= 260000 && !started2) {
            ap.play(238000); // Resume audio playback
            started2 = true;
        } else if (startTime > 260000) {
            ap.pause(); // Pause audio playback
        }
        
    }

}
