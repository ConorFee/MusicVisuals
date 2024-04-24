package C22493266;

import java.util.ArrayList;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ie.tudublin.Visual;
import processing.core.PApplet;
import processing.core.PVector;
//import java.util.ArrayList;

public class JVisual extends Visual {

    Minim m;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer b;

    float circleSize;                                           //put circlesize up here as i was having trouble accessing it in the clouds function

    ArrayList<PVector> cloudPostions;       // this list stores clicked locations for clouds
    ArrayList<Smoke> smokes = new ArrayList<>();
    int smokeTimer = 0;                                      


    @Override
    public void settings() {
        size(800, 600);                                         //window size
    }

    @Override
    public void setup() {
        m = new Minim(this);
        ap = m.loadFile("stayinit.mp3");
        //ap.mute();

        //ai = m.getLineIn(Minim.MONO, width, 44100, 16);
        ap.play();
        b = ap.mix;

        cloudPostions = new ArrayList<>();                      //initialize list

    }

    @Override
    public void draw() {

             
             setGradient(0, 0, width, height, color(137, 255, 180), color(137, 255, 255)); // fades from light to darker blue sky

             float avg = 0; 
             for (int i = 0; i < b.size(); i++){                //avg moved up here so it can be passed into later functions
                avg += abs(b.get(i));   
             }
             avg /= b.size();       //average creation complete at this point, measured sound
             circleSize = map(avg,0,1,50,300);                          //size rangeees


             drawMountainsShadow(avg);
             drawMountains(avg);
             drawFields();
             drawCow(avg, circleSize);
             drawShroom(avg); 

             cloudShadow();
             clouds();          
             circleSize = map(avg,0,1,50,300);                          //size rangeees


             colorMode(HSB);
             float hue = map(avg,0,1,30,200);
             fill(hue,255,255);
             //noStroke();
             ellipse(width / 2, height / 2, circleSize, circleSize);

             // going to attempt to make similar lines to this        https://www.youtube.com/watch?v=E9SD8M_awps
            
             stroke(hue,255,255);
             int numLines = 400;
             float angles =  TWO_PI / numLines;


             for (int i = 0; i < numLines; i++) {
                float lineLength = map(b.get(i % b.size()), -1, 1,0,150);       //https://processing.org/examples/sinecosine.html
                float x1 = width/2 + cos(angles * i) * (circleSize / 2) ;
                float y1 = height/2 + sin(angles * i ) * (circleSize / 2 );
                float x2 = width/2 + cos(angles * i) * (circleSize / 2 + lineLength) ;
                float y2 = height/2 + sin(angles * i ) * (circleSize / 2 + lineLength);
                
                

                line(x1,y1,x2,y2);



             }
             for (int i = smokes.size() - 1; i >= 0; i--) {
                Smoke smoke = smokes.get(i);
                smoke.update();
                smoke.display();
                if (smoke.isDead()) {
                    smokes.remove(i);
                }
            }
        
            
            if (smokeTimer % 50 == 0) {  // timing
                float jointX = width * 0.5f + 50; 
                float jointY = height * 0.9f - 29; 
                smokes.add(new Smoke(jointX, jointY));
            }
            smokeTimer++;
        
        }


        public void mousePressed() {
            cloudPostions.add(new PVector(mouseX,mouseY));                                  //add pos of mouse click
        }



        private void setGradient(int x, int y, float w, float h, int c1, int c2) {          //used for the fading sky, draws lines repeatedly, slightly changing colour as it increments
            noFill();
            for (int i = y; i <= y + h; i++) {
                float inter = map(i, y, y + h, 0, 1);
                int c = lerpColor(c1, c2, inter);                                           //mix the two colours together
                stroke(c);
                line(x, i, x + w, i);
            }
        }

        private void drawFields() {
            fill(70,249,255);
            rect(0, height * 0.7f, width, height * 0.3f) ;
         }



         private void drawMountains(float avg) {
            fill(255, 0, 100); 
            beginShape(TRIANGLES);
            float mountainHeightFactor = map(avg, 0, 1, 30, 80); // Dynamic height factor
            for (int i = 0; i < width; i += 30) { // 30 px spacing
                vertex(i, height * 0.7f); // start base point 
                vertex(i + 25, height * 0.5f - random(mountainHeightFactor)); // mdddle point with dynamic height
                vertex(i + 50, height * 0.7f); // end point at base
            }
            endShape();
        }
        
        private void drawMountainsShadow(float avg) {
            beginShape(TRIANGLES);
            float shadowHeightFactor = map(avg, 0, 1, 40, 100); // variation in shadow
            for (int i = 0; i < width; i += 30) {
                float hue = map(avg, 0, 1, 120, 255);  
                fill(hue, 255, 255);  
                vertex(i, height * 0.7f);
                vertex(i + 25, height * 0.5f - random(shadowHeightFactor));
                vertex(i + 50, height * 0.6f);
            }
            endShape();
        }

        public void clouds() {
            fill(255);
            noStroke();
            int DECRET = 30;            // variable i use to decrease the size of the clouds/cirlces as it uses the same sizing as the sun

            for(PVector pos : cloudPostions){                                         // places cloudcluster at every stored CoOrdinate in cloudpositoins
                ellipse(pos.x, pos.y, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x - 20, pos.y + 0, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x + 5, pos.y - 5, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x + 2, pos.y + 5, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x + 15, pos.y + 0, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x - 10, pos.y + 3, circleSize - DECRET, circleSize - DECRET);
                ellipse(pos.x - 10, pos.y - 3, circleSize - DECRET, circleSize - DECRET);

            }

                                        //these are hard set clouds which I had originally
            //top left clouds
            //ellipse(130, 100, circleSize-DECRET, circleSize-DECRET);
           // ellipse(120, 90, circleSize-DECRET, circleSize-DECRET);
            //ellipse(100, 100, circleSize-DECRET, circleSize-DECRET);
            //ellipse(105, 95, circleSize-DECRET, circleSize-DECRET);
            //ellipse(110, 100, circleSize-DECRET, circleSize-DECRET);

            //top right 

            //ellipse(630, 120, circleSize-DECRET, circleSize-DECRET);
           // ellipse(620, 110, circleSize-DECRET, circleSize-DECRET);
           /// ellipse(600, 120, circleSize-DECRET, circleSize-DECRET);
            //ellipse(605, 115, circleSize-DECRET, circleSize-DECRET);
            //ellipse(610, 120, circleSize-DECRET, circleSize-DECRET);


            // mid
            //ellipse(430, 80, circleSize-DECRET, circleSize-DECRET);
           // ellipse(420, 70, circleSize-DECRET, circleSize-DECRET);
            //ellipse(400, 80, circleSize-DECRET, circleSize-DECRET);
            ///ellipse(405, 75, circleSize-DECRET, circleSize-DECRET);
            //ellipse(410, 80, circleSize-DECRET, circleSize-DECRET);


        }

        public void cloudShadow() {
            //fill(0);
            noStroke();
            int DECRET = 20;

            for(PVector pos : cloudPostions){
                //float hue = random(255);
                //fill(hue, 255,255);
                fill(0);

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
           // ellipse(130, 100, circleSize-DECRET, circleSize-DECRET);
           // ellipse(120, 90, circleSize-DECRET, circleSize-DECRET);
           // ellipse(100, 100, circleSize-DECRET, circleSize-DECRET);
           // ellipse(105, 95, circleSize-DECRET, circleSize-DECRET);
           // ellipse(110, 100, circleSize-DECRET, circleSize-DECRET);

            //top right 

            //ellipse(630, 120, circleSize-DECRET, circleSize-DECRET);
            //ellipse(620, 110, circleSize-DECRET, circleSize-DECRET);
            //ellipse(600, 120, circleSize-DECRET, circleSize-DECRET);
            //ellipse(605, 115, circleSize-DECRET, circleSize-DECRET);
            //ellipse(610, 120, circleSize-DECRET, circleSize-DECRET);

            // mid
            //ellipse(430, 80, circleSize-DECRET, circleSize-DECRET);
            //ellipse(420, 70, circleSize-DECRET, circleSize-DECRET);
            //ellipse(400, 80, circleSize-DECRET, circleSize-DECRET);
            //ellipse(405, 75, circleSize-DECRET, circleSize-DECRET);
            //ellipse(410, 80, circleSize-DECRET, circleSize-DECRET);


        }

        private void drawCow(float avg , float circleSize) {
            float cowX = width * 0.5f;
            float cowY = height * 0.9f;
        
            // Body
            fill(255);
            ellipse(cowX, cowY, 100, 150);
            
            // Ears
            fill(255);
            ellipse(cowX - 22, cowY - 100, 20, 20); 
            ellipse(cowX + 22, cowY - 100, 20, 20); 
            
            // Head
            ellipse(cowX, cowY - 60, 65, 85); 
            
            //mouth
            fill(0);
            ellipse(cowX, cowY-29, circleSize/2, circleSize/4);

            //joint
            fill(9, 200, 100);  // Brown color for the joint
            noStroke();
            rect(cowX + 10, cowY - 29, 40, 6); 
            fill(255,255,255);
            ellipse(cowX + 50, cowY - 26, 6, 6);

            // Eyes
            float hue = map(avg, 0, 1, 0, 255);
            fill(hue, 255, 255);
            ellipse(cowX - 20, cowY - 70, 30, 30);
            ellipse(cowX + 20, cowY - 70, 30, 30);
            fill(0);
            ellipse(cowX - 20, cowY - 70, 30, 9);
            ellipse(cowX + 20, cowY - 70, 30, 9);
            
            // Legs and spots
            fill(0);
            rect(cowX - 45, cowY + 30, 15, 40);
            rect(cowX + 30, cowY + 30, 15, 40);
            ellipse(cowX - 10, cowY, 20, 15);
            ellipse(cowX + 20, cowY + 10, 25, 20);
            
            // Tail
            stroke(0);
            line(cowX + 50, cowY, cowX + 70, cowY - 20);
            fill(0);
            ellipse(cowX + 70, cowY - 20, 10, 10);
            noStroke();


        }

        void drawShroom(float avg) {
            float shroomX = 200; 
            float shroomY = 500; 
            
            // stem of the mushroom
            fill(255); 
            rect(shroomX, shroomY, 14, 50);
            
            // shadow of the mushroom cap
            float hue = map(avg, 0, 1, 00, 255); 
            fill(hue, 255, 255);
            arc(shroomX+8  , shroomY , 100, 60, PI, TWO_PI);
            //actual cap
            fill(00,255,255);
            arc(shroomX+8  , shroomY , 80, 40, PI, TWO_PI);
            
            //spots
            fill(255,0,255);

            ellipse(shroomX,shroomY-20,10,10);
            ellipse(shroomX-20,shroomY-7,10,10);
            ellipse(shroomX+30,shroomY-15,10,10);
            
        }



        class Smoke {
            PVector position;
            float size;
            float opacity;
        
            Smoke(float x, float y) {
                position = new PVector(x, y);
                size = random(5, 10); 
                opacity = 255;  
            }
        
            void update() {
                position.y -= random(0.5F, 1.5F); // smoke rises
                size *= 1.03; // smoke size increase
                opacity -= 3; // smoke fade
            }
        
            void display() {
                noStroke();
                fill(255, opacity);
                ellipse(position.x, position.y, size, size);
            }
        
            boolean isDead() {
                return opacity <= 0 || size <= 0;
            }
        }
    }

