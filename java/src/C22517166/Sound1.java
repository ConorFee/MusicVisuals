package C22517166;

import ie.tudublin.Visual;
import ddf.minim.AudioBuffer;
//import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT; // Include FFT class for frequency analysis

public class Sound1 extends Visual {
    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;
    FFT fft;  // Fast Fourier Transform for frequency analysis

    int cols, rows;
    int scale = 20;
    int w = 2000;
    int h = 1600;

    float[][] terrain;
    float flying = 0;

    public void settings() {
        size(1200, 1200, "processing.opengl.PGraphics3D");
    }

    public void keyPressed() {
        if (key == ' ') {
            ap.cue(0);
            ap.play();
        }
    }

    public void setup() {
        colorMode(HSB);
        cols = w / scale;
        rows = h / scale;
        terrain = new float[cols][rows];
        println("CWD: " + System.getProperty("user.dir"));
        noCursor();
        minim = new Minim(this);
        ap = minim.loadFile("stayinit.mp3", 1024);
        ab = ap.mix;
        fft = new FFT(ap.bufferSize(), ap.sampleRate());
        ap.play();
    }

    public void draw() {
      flying -= 0.1f;
      float yoffset = flying;
      float maxAmp = 0; // Variable to store maximum amplitude in the current frame
    
      // Update the maximum amplitude for the current frame
      for (int i = 0; i < ab.size(); i++) {
        maxAmp = max(ab.get(i), maxAmp);
      }
    
      for (int y = 0; y < rows; y++) {
        float xoffset = 0;
        for (int x = 0; x < cols; x++) {
          float baseHeight = map(noise(xoffset, yoffset), 0, 1, -120, 120);
          terrain[x][y] = baseHeight + (maxAmp * 300); // Modulate height with audio amplitude
          xoffset += 0.2f;
        }
        yoffset += 0.2f;
      }
    
      background(random(360), 100, 100);
      translate(width/2, height/2+150);
      rotateX(PI/3);
      translate(-w/2, -h/2);
    
      colorMode(HSB, 360, 100, 100); // Use full color spectrum for vibrant transitions
      for (int y = 0; y < rows - 1; y++) {
        beginShape(TRIANGLE_STRIP);
        for (int x = 0; x < cols; x++) {
          float z = terrain[x][y]; 
          float z1 = terrain[x][y + 1];
    
          // Assign completely random colors for each vertex
          fill(random(360), 100, 100);
          stroke(random(360), 100, 80); // Use random colors for stroke too
          strokeWeight(2);
    
          vertex(x * scale, y * scale, z);
          vertex(x * scale, (y + 1) * scale, z1);
        }
        endShape();
      }
    }
    
}
