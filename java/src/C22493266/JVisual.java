package C22493266;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class JVisual extends PApplet {

    Minim m;
    AudioInput ai;
    AudioPlayer ap;
    
    AudioBuffer b;

    @Override
    public void settings() {
        // Set the size of the window
        size(1500, 1000);            //adding p3d allows for 3d render  : 1500,1000,P3D
    }

    @Override
    public void setup() {
        // Setup code goes here
        m = new Minim(this);
        //ai = m.getLineIn(Minim.MONO, width, 44100, 16);
        ap = m.loadFile("stayinit.mp3");
        //ap = m.loadFile("stayinit.mp3");

        ap.play();
        b = ap.mix;
    }

    @Override
    public void draw() {
        colorMode(HSB);
        background(0);
        stroke(255);
        /* 
        float barWidth = width / (float)b.size();

        for(int i = 0; i < b.size(); i++) {
            float x = i * barWidth;
            float barHeight = abs(b.get(i)) * height * 5;
            float hue = map(i, 0, b.size(), 0, 256);

            // Draw the shadow
            fill(128, 178, 128, 100);// Black with low alpha for shadow effect
            line(x + 20, height - barHeight + 5, barWidth, barHeight);

            stroke(255);
            // Draw the main bar
            //fill(hue, 100, 255);
            //line(x, height - barHeight, barWidth, barHeight);
            */
            float h = height /2;
            for(int i = 0; i < b.size() ; i++)
            {
                float hue = map(i , 0, b.size(), 0 , 256);
                stroke(hue, 255, 255);
                line(i, h + b.get(i) * h, i , h+b.get(i) * h);

            }
       
        }
    }

