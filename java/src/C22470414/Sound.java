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
        println("Setting up...");
        m = new Minim(this);
        ap = m.loadFile("stayinit.mp3");
        if (ap == null) {
            println("Failed to load audio file.");
        } else {
            println("Audio file loaded successfully.");
            ap.play();
        }
        b = ap.mix;
        smoothedData = new float[b.size()];
    }
    // Define variables to control circle pulsation
float minDiameter = 100; // Minimum diameter of the circle
float maxDiameter = 1000; // Maximum diameter of the circle
float pulsationSpeed = 0.05f; // Speed of pulsation

@Override
public void draw() {
    colorMode(HSB);
    background(0);

    // Update and smooth the data
    for (int i = 0; i < b.size(); i++) {
        smoothedData[i] = lerp(smoothedData[i], b.get(i), 0.1f); // Interpolate each sample
    }

    // Draw shapes based on smoothed data and map color to frequency
    for (int i = 0; i < b.size() - 1; i += 10) { // Draw shapes every 10 samples
        // Calculate the amplitude of the audio at this sample
        float amplitude = smoothedData[i];

        // Map amplitude to size of the shape
        float size = map(amplitude, 0, 1, minDiameter, maxDiameter); // Map amplitude to shape size

        // Calculate the frequency index for this sample
        int freqIndex = (int) map(i, 0, b.size(), 0, 1024);
        float freq = map(freqIndex, 0, 1024, 20, 20000); // Map frequency index to actual frequency

        // Map frequency to hue
        float hue = map(freq, 20, 20000, 0, 256); // Map frequency to hue

        // Set fill color
        fill(hue, 255, 255);

        // Draw shapes with stereoscope effect
        float xLeft = map(i, 0, b.size(), width / 4, width / 2); // Map x-coordinate for left side
        float xRight = map(i, 0, b.size(), width / 2, 3 * width / 4); // Map x-coordinate for right side
        float y = height / 2; // Center vertically
        
        // Draw rectangles on left side
        rectMode(CENTER);
        rect(xLeft, y, size, size);

        // Draw ellipses on right side
        ellipseMode(CENTER);
        ellipse(xRight, y, size, size);
    }
}


}
