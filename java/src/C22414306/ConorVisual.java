package C22414306;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ie.tudublin.Visual;

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
        //fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; 
        
        ap = minim.loadFile("heartless.mp3", 1024);
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
            
                // Calculate overall average value
                float total1 = 0;

                for(int i = 0; i < ab.size(); i++) 
                {
                    total1 += abs(ab.get(i));
                }
                
                float overallAverage = total1 / (float) ab.size();
                
                // Draw central circle
                float hue = map(overallAverage, 0, 1, 0, 360); // Map average value to hue
                float saturation = 255; // Saturation set to maximum
                float brightness = 255; // Brightness set to maximum
                noFill(); // Set fill to none for the central circle
                stroke(hue, saturation, brightness); // Set stroke color using HSB color mode
                float radius = overallAverage * halfH * 2; // Scale radius based on overall average
                ellipse(width/2, height/2, radius, radius); // Draw central circle at the center of the canvas
                
                break;    
        
            default:

                break;
        }

    }

}
