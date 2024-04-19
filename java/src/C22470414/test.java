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
        player = minim.loadFile("stayinit.mp3", 1024);
        player.play();

        fft = new FFT(player.bufferSize(), player.sampleRate());

        // Initialize the smoothed amplitude array
        smoothedAmplitude = new float[fft.specSize()];

        // Set initial size of the primary X shape to fit the window
        baseXSize = width / 12; // Start smaller
        baseYSize = height / 12;
        minSize = width / 15; // Adjusted minimum size
        maxSize = width / 6; // Adjusted maximum size

        // Set initial thickness of the primary X shape
        thickness = 10; // Adjusted thickness

        // Set initial size of the secondary X shape
        secondaryBaseXSize = baseXSize / 4; // Start smaller
        secondaryBaseYSize = baseYSize / 4;
        secondaryMinSize = baseXSize / 5; // Adjusted minimum size
        secondaryMaxSize = baseXSize / 3; // Adjusted maximum size

        // Set initial thickness of the secondary X shape
        secondaryThickness = 5; // Adjusted thickness

        // Set background color to black
        background(0);
    }

    