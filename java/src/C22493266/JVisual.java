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
        size(800, 600);            //adding p3d allows for 3d render  : 1500,1000,P3D
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

             background(137,255,240);
             float avg = 0;
             
             for (int i = 0; i < b.size(); i++){

                avg += abs(b.get(i));

             }

             avg /= b.size();

             float circleSize = map((avg),0,1,50,300); //size rangeees


             colorMode(HSB);
             float hue = map(avg,0,1,20,200);
             fill(hue,255,255);
             noStroke();
             ellipse(width / 2, height / 2, circleSize, circleSize);

             // going to attempt to make similar lines to this        https://www.youtube.com/watch?v=E9SD8M_awps
            

             stroke(hue,255,255);
             int numLines = 199;
             float angles =  TWO_PI / numLines;
             for (int i = 0; i < numLines; i++) {
                float lineLength = map(b.get(i % b.size()), -1, 1,0,150);       //https://processing.org/examples/sinecosine.html
                float x1 = width/2 + cos(angles * i) * (circleSize / 2) ;
                float y1 = height/2 + sin(angles * i ) * (circleSize / 2 );
                float x2 = width/2 + cos(angles * i) * (circleSize / 2 + lineLength) ;
                float y2 = height/2 + sin(angles * i ) * (circleSize / 2 + lineLength);

                line(x1,y1,x2,y2);

             }
        }
    }

