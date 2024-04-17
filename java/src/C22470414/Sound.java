package C22470414;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Sound extends PApplet {

    Minim m;
    AudioPlayer ap;
    AudioBuffer b;

    float[] smoothedData; // Array to store smoothed values

    @Override
    public void settings() {
        size(800, 600);
    }

    @Override
    public void setup() {
        m = new Minim(this);
        ap = m.loadFile("stayinit.mp3");
        ap.play();
        b = ap.mix;

        smoothedData = new float[b.size()]; // Create an array of the same size as the buffer
    }

    @Override
    public void draw() {
        colorMode(HSB);
        background(0);
        stroke(255);

        // Smooth the data
        for (int i = 0; i < b.size(); i++) {
            smoothedData[i] = lerp(smoothedData[i], b.get(i), 0.1f); // Interpolate each sample
        }

        float h = height / 2;

        // Draw smoothed data
        for (int i = 0; i < b.size() - 1; i++) {
            float hue = map(i, 0, b.size(), 0, 256);
            stroke(hue, 255, 255);
            line(i, h + smoothedData[i + 1] * h, i + 1,h + smoothedData[i] * h);
        }
    }
}
