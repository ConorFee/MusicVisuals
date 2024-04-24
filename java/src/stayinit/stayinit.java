package stayinit;

import processing.core.PApplet;
import ddf.minim.Minim;
import ddf.minim.AudioPlayer;
import C22517166.Sound1;
import C22414306.ConorVisual;
import C22493266.JVisual;
import C22470414.test;

public class stayinit extends PApplet {
    Minim minim;
    AudioPlayer ap;
    int mode = 1;

    Sound1 oisin;
    ConorVisual conor;
    JVisual jake;
    test mark;

    public void settings() {
        size(1200, 1200, P3D);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("stayinit.mp3", 1024);
        if (ap != null) {
            ap.play();
        } else {
            println("Failed to load audio file!");
            // Handle error or exit
        }

        // Ensure that all visualizations are instantiated here
        oisin = new Sound1();  // Create instances
        conor = new ConorVisual();
        jake = new JVisual();
        mark = new test();

        //oisin.initialize();  // Initialize immediately after instantiation
        //conor.initialize();
        //jake.initialize();
        //mark.initialize();
    }

    public void draw() {
        background(0);
        switch(mode) {
            case 1:
                oisin.draw();
                break;
            case 2:
                conor.draw();
                break;
            case 3:
                jake.draw();
                break;
            case 4:
                mark.draw();
                break;
        }
    }

    public void keyPressed() {
        if (key >= '1' && key <= '4') {
            mode = key - '0';
        }
    }
}
