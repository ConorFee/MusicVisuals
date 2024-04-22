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
        size(800, 800);            //adding p3d allows for 3d render  : 1500,1000,P3D
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

        /* 
        colorMode(HSB);
        //colorMode(RGB, 1500,100,255);
        background(0);
        stroke(255);
         
       
            

             
            float h = height /2;
            for(int i = 0; i < b.size() ; i++)
            {
                float hue = map( i , 0, b.size(), 0 , 130);
                //stroke(mouseX, 255, 255);
                stroke(hue, 255, 255);
                //fill(255, 100, 200, 25);
                //fill(mouseX, 100, mouseY, 25);
                //line(mouseX , mouseY, i , h+b.get(i) * h);
                line(i , h + b.get(i) * h, i , h+b.get(i) * h);
                line(i, h, i, h + b.get(i) * 100); // 100 is a scaling factor for visualization
            }
            
            float h = height / 2;
            float w = width / (float) b.size(); // calculates circle spacing
            
            for (int i = 0; i < b.size(); i++) {
                float diameter = abs(b.get(i)) * 200;  // Scale the diameter based on audio level
                float hue = map(i, 0, b.size(), 0, 255);
                fill(hue, 255, 255);
                noStroke();  // Disable stroke for a cleaner look of circles
                ellipse(w * i + w / 2, h, diameter, diameter);  // Draw the circle centered in its segment
             } */

             background(0);
             float avg = 0;
             
             for (int i = 0; i < b.size(); i++){

                avg += abs(b.get(i));

             }

             avg /= b.size();

             float circleSize = map((avg+1),0,1,50,300); //size rangeees


             colorMode(HSB);
             float hue = map(avg,0,1,0,255);
             fill(hue,255,255);
             noStroke();
             ellipse(width / 2, height / 2, circleSize, circleSize);

             // going to attempt to make similar lines to this        https://www.youtube.com/watch?v=E9SD8M_awps
            

             stroke(hue,255,255);
             int numLines = 600;
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

