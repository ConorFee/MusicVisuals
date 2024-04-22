package C22493266;

import java.util.ArrayList;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PVector;
import java.util.ArrayList;

public class JVisual extends PApplet {

    Minim m;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer b;

    float circleSize;       //put circlesize up here as i was having trouble accessing it in the clouds function

    ArrayList<PVector> cloudPostions;       // this list stores clicked locations for clouds


    @Override
    public void settings() {
        size(800, 600);             //window size
    }

    @Override
    public void setup() {
        m = new Minim(this);
        ap = m.loadFile("stayinit.mp3");
        //ai = m.getLineIn(Minim.MONO, width, 44100, 16);
        ap.play();
        b = ap.mix;

        cloudPostions = new ArrayList<>(); //initialize list

    }

    @Override
    public void draw() {

             
             setGradient(0, 0, width, height, color(137, 255, 180), color(137, 255, 255)); // fades from light to darker blue sky



             drawFields();
             cloudShadow();
             clouds();
             


             float avg = 0; 
             for (int i = 0; i < b.size(); i++){
                avg += abs(b.get(i));   
             }
             avg /= b.size();       //average creation complete at this point, measured sound
             circleSize = map(avg,0,1,50,300); //size rangeees


             colorMode(HSB);
             float hue = map(avg,0,1,30,200);
             fill(hue,255,255);
             noStroke();
             ellipse(width / 2, height / 2, circleSize, circleSize);

             // going to attempt to make similar lines to this        https://www.youtube.com/watch?v=E9SD8M_awps
            


             stroke(hue,255,255);
             int numLines = 20;
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


        public void mousePressed() {
            cloudPostions.add(new PVector(mouseX,mouseY));  //add pos of mouse click
        }



        private void setGradient(int x, int y, float w, float h, int c1, int c2) {          //used for the fading sky, draws lines repeatedly, slightly changing colour as it increments
            noFill();
            for (int i = y; i <= y + h; i++) {
                float inter = map(i, y, y + h, 0, 1);
                int c = lerpColor(c1, c2, inter);       //mix the two colours together
                stroke(c);
                line(x, i, x + w, i);
            }
        }

        private void drawFields() {
            fill(84,179,199);
            rect(0, height * 0.8f, width, height * 0.2f) ;
         }



        public void clouds() {
            fill(255);
            noStroke();
            int DECRET = 30;            // variable i use to decrease the size of the clouds/cirlces as it uses the same sizing as the sun

            for(PVector pos : cloudPostions){
                ellipse(pos.x, pos.y, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x - 20, pos.y + 0, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x + 5, pos.y - 5, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x + 2, pos.y + 5, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x + 15, pos.y + 0, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x - 10, pos.y + 3, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x - 10, pos.y - 3, circleSize - DECRET, circleSize - DECRET);

            }


            //top left clouds
            ellipse(130, 100, circleSize-DECRET, circleSize-DECRET);
            ellipse(120, 90, circleSize-DECRET, circleSize-DECRET);
            ellipse(100, 100, circleSize-DECRET, circleSize-DECRET);
            ellipse(105, 95, circleSize-DECRET, circleSize-DECRET);
            ellipse(110, 100, circleSize-DECRET, circleSize-DECRET);

            //top right 

            ellipse(630, 120, circleSize-DECRET, circleSize-DECRET);
            ellipse(620, 110, circleSize-DECRET, circleSize-DECRET);
            ellipse(600, 120, circleSize-DECRET, circleSize-DECRET);
            ellipse(605, 115, circleSize-DECRET, circleSize-DECRET);
            ellipse(610, 120, circleSize-DECRET, circleSize-DECRET);


            // mid
            ellipse(430, 80, circleSize-DECRET, circleSize-DECRET);
            ellipse(420, 70, circleSize-DECRET, circleSize-DECRET);
            ellipse(400, 80, circleSize-DECRET, circleSize-DECRET);
            ellipse(405, 75, circleSize-DECRET, circleSize-DECRET);
            ellipse(410, 80, circleSize-DECRET, circleSize-DECRET);


        }

        public void cloudShadow() {
            fill(0);
            noStroke();
            int DECRET = 20;

            for(PVector pos : cloudPostions){
                //ellipse(pos.x, pos.y, circleSize - DECRET, circleSize - DECRET);
                //ellipse(pos.x + random(-20, 20), pos.y + random(-20, 20), circleSize - DECRET, circleSize - DECRET);
                //ellipse(pos.x + random(-20, 20), pos.y + random(-20, 20), circleSize - DECRET, circleSize - DECRET);      //this was an attempt to randomize the shape of each cloud when clicked but it reset evey frames
                ellipse(pos.x, pos.y, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x - 20, pos.y + 0, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x + 5, pos.y - 5, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x + 2, pos.y + 5, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x + 15, pos.y + 0, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x - 10, pos.y + 3, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x - 10, pos.y - 3, circleSize - DECRET, circleSize - DECRET);

            }



            //top left clouds
            ellipse(130, 100, circleSize-DECRET, circleSize-DECRET);
            ellipse(120, 90, circleSize-DECRET, circleSize-DECRET);
            ellipse(100, 100, circleSize-DECRET, circleSize-DECRET);
            ellipse(105, 95, circleSize-DECRET, circleSize-DECRET);
            ellipse(110, 100, circleSize-DECRET, circleSize-DECRET);

            //top right 

            ellipse(630, 120, circleSize-DECRET, circleSize-DECRET);
            ellipse(620, 110, circleSize-DECRET, circleSize-DECRET);
            ellipse(600, 120, circleSize-DECRET, circleSize-DECRET);
            ellipse(605, 115, circleSize-DECRET, circleSize-DECRET);
            ellipse(610, 120, circleSize-DECRET, circleSize-DECRET);

            // mid
            ellipse(430, 80, circleSize-DECRET, circleSize-DECRET);
            ellipse(420, 70, circleSize-DECRET, circleSize-DECRET);
            ellipse(400, 80, circleSize-DECRET, circleSize-DECRET);
            ellipse(405, 75, circleSize-DECRET, circleSize-DECRET);
            ellipse(410, 80, circleSize-DECRET, circleSize-DECRET);


        }


    }

