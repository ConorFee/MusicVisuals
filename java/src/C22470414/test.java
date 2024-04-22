package C22470414;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;

public class test extends PApplet {

    Minim minim;
    AudioPlayer player;
    FFT fft;

    float[] smoothedAmplitude;
    float smoothingFactor = 0.9f; // Smoothing factor for the low-pass filter
    float xSize, ySize; // Size of the primary X shape
    float baseXSize, baseYSize; // Base size of the primary X shape
    float minSize, maxSize; // Minimum and maximum size of the primary X shape
    float thickness; // Thickness of the primary X shape
    float secondaryXSize, secondaryYSize; // Size of the secondary X shape
    float secondaryBaseXSize, secondaryBaseYSize; // Base size of the secondary X shape
    float secondaryMinSize, secondaryMaxSize; // Minimum and maximum size of the secondary X shape
    float secondaryThickness; // Thickness of the secondary X shape
    int colorChangeSpeed = 5; // Speed of color change
    int colorOffset = 0; // Offset for color change
    float lastSecondaryXSize, lastSecondaryYSize; // Store previous secondary X shape size

    @Override
    public void settings() {
        size(800, 600); // Set the size of the window
    }

    @Override
    public void setup() {
        colorMode(RGB, 255);
        minim = new Minim(this);

        // Load audio file
        player = minim.loadFile("stayinit.mp3");
        player.play();

        fft = new FFT(player.bufferSize(), player.sampleRate());

        // Initialize the smoothed amplitude array
        smoothedAmplitude = new float[fft.specSize()];

        // Set initial size of the primary X shape to fit the window
        baseXSize = width / 12; // start size
        baseYSize = height / 12;
        minSize = width / 15; // Adjusted minimum size
        maxSize = width / 6; // Adjusted maximum size

        // Set initial thickness of the primary X shape
        thickness = 10; // Adjusted thickness

        // Set initial size of the secondary X shape
        secondaryBaseXSize = baseXSize / 4; // Start sszie
        secondaryBaseYSize = baseYSize / 4;
        secondaryMinSize = baseXSize / 5; //minimum size
        secondaryMaxSize = baseXSize / 3; //maximum size

        // Set initial thickness of the secondary X shape
        secondaryThickness = 5; //thickness

        // Set background color to black
        background(0);
    }

    @Override
    public void draw() {
        // Calculate spectrum
        fft.forward(player.mix);

        // Smooth amplitude values
        for (int i = 0; i < fft.specSize(); i++) {
            float amplitude = fft.getBand(i);
            smoothedAmplitude[i] = lerp(smoothedAmplitude[i], amplitude, smoothingFactor);
        }

        // Calculate maximum amplitude
        float maxAmplitude = max(smoothedAmplitude);

        //primary X shape size based on the music amplitude
        xSize = map(maxAmplitude, 0, 1, minSize, maxSize);
        ySize = map(maxAmplitude, 0, 1, minSize, maxSize);

        // Ensure each corner of the primary X shape is always visible
        xSize = min(xSize, width / 2);
        ySize = min(ySize, height / 2);

        //thickness of the primary X shape
        thickness = map(maxAmplitude, 0, 1, 5, 20); // Adjusted thickness

        // secondary X shape size based on the primary X shape size
        secondaryXSize = map(maxAmplitude, 0, 1, secondaryMinSize, secondaryMaxSize);
        secondaryYSize = map(maxAmplitude, 0, 1, secondaryMinSize, secondaryMaxSize);

        // Ensure each corner of the secondary X shape is always visible
        secondaryXSize = min(secondaryXSize, width / 2);
        secondaryYSize = min(secondaryYSize, height / 2);

        // Apply additional smoothing to secondary X shape size
        secondaryXSize = lerp(lastSecondaryXSize, secondaryXSize, 0.05f); // Adjust smoothing factor here
        secondaryYSize = lerp(lastSecondaryYSize, secondaryYSize, 0.05f); // Adjust smoothing factor here

        // Store current secondary X shape size for smoothing
        lastSecondaryXSize = secondaryXSize;
        lastSecondaryYSize = secondaryYSize;

        //thickness of the secondary X shape
        secondaryThickness = map(maxAmplitude, 0, 1, 2, 8); // Adjusted thickness

        // Draw primary X shape
        translate(width / 2, height / 2);
        drawXShape(-xSize, -ySize, xSize, ySize, thickness);

        // Draw secondary X shape if primary X shape is large
        if (xSize > maxSize * 0.8) {
            drawXShape(-secondaryXSize / 2, -secondaryYSize / 2, 
                        secondaryXSize / 2, secondaryYSize / 2, secondaryThickness);
        }

        //color offset for primary X shape
        colorOffset += colorChangeSpeed;
    }

    // Method to draw X shape
    void drawXShape(float x1, float y1, float x2, float y2, float thickness) {
        // Set stroke weight
        strokeWeight(thickness);

        // Set stroke color based on RGB cycling
        int r, g, b;
        if (thickness == secondaryThickness) {
            // Secondary X shape color: blue, white, pink
            int colorOffsetMod = colorOffset % 765; // Ensure colorOffset stays within 0-765 range
            if (colorOffsetMod < 255) {
                r = 0;
                g = colorOffsetMod;
                b = 255;
            } else if (colorOffsetMod < 510) {
                r = 255;
                g = 255;
                b = 255;
            } else {
                r = 255;
                g = 255 - (colorOffsetMod - 510);
                b = 255;
            }
        } else {
            // Primary X shape color
            int colorOffsetMod = colorOffset % 510; // Ensure colorOffset stays within 0-510 range
            if (colorOffsetMod < 255) {
                r = 255 - colorOffsetMod;
                g = colorOffsetMod;
                b = 255;
            } else {
                r = 0;
                g = 510 - colorOffsetMod;
                b = colorOffsetMod - 255;
            }
        }
        stroke(r, g, b);

        // Draw X shape
        line(x1, y1, x2, y2);
        line(x2, y1, x1, y2);
    }
    
    // Add method to set audio player for the sketch
    public void setAudioPlayer(AudioPlayer player) {
        this.player = player;
    }

    public static void main(String[] args) {
        PApplet.main("C22470414.test");
    }
} 

    