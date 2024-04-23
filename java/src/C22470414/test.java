package C22470414;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;

public class test extends PApplet 
{

    Minim minim;
    AudioPlayer player;
    FFT fft;

    float[] smoothedAmplitude;
    float smoothingFactor = 0.9f; // Smoothing factor for the low-pass filter
    float xSize, ySize , thickness; // Size of the primary X shape
    float baseXSize, baseYSize , minSize ,maxSize; // Base size of the primary X shape
    float secondaryXSize, secondaryYSize , secondaryThickness; // Size of the secondary X shape
    float secondaryBaseXSize, secondaryBaseYSize , secondaryMinSize , secondaryMaxSize; // Base size of the secondary X shape
    int colorChangeSpeed = 5; // Speed of color change
    int colorOffset = 0; // Offset for color change
    float lastSecondaryXSize, lastSecondaryYSize; // Store previouS secondary X shape size

    @Override
    public void settings() 
    {
        size(800, 600); // Set the size of the window
    }

    @Override
    public void setup() 
    {
        colorMode(RGB, 255);
        minim = new Minim(this);

        // Load audio file
        player = minim.loadFile("stayinit.mp3");
        player.play();

        fft = new FFT(player.bufferSize(), player.sampleRate());

        // Initialize the smoothed amplitude array
        smoothedAmplitude = new float[fft.specSize()];

        //size of the primary X shape to fit the window
        baseXSize = width / 12; // start size
        baseYSize = height / 12;
        minSize = width / 15; // minimum size
        maxSize = width / 6; // maximum size
        thickness = 10;

        // Set initial size of the secondary X shape
        secondaryBaseXSize = baseXSize / 4; // Start sszie
        secondaryBaseYSize = baseYSize / 4;
        secondaryMinSize = baseXSize / 5; //minimum size
        secondaryMaxSize = baseXSize / 3; //maximum size
        secondaryThickness = 5;//thickness

        // Set background color to black
        background(0);
    }

    @Override
    public void draw() 
    {
        // Calculate spectrum
        fft.forward(player.mix);

        // Smooth amplitude values
        for (int i = 0; i < fft.specSize(); i++) 
        {
            float amplitude = fft.getBand(i);
            smoothedAmplitude[i] = lerp(smoothedAmplitude[i], amplitude, smoothingFactor);
        }

        // Calculate maximum amplitude
        float maxAmplitude = max(smoothedAmplitude);

        //primary X shape size based on the music amplitude
        xSize = map(maxAmplitude, 0, 1, minSize, maxSize);
        ySize = xSize;

        // Ensure each corner of the primary X shape is always visible
        xSize = min(xSize, width / 2);
        ySize = min(ySize, height / 2);

        //thickness of the primary X shape
        thickness = map(maxAmplitude, 0, 1, 5, 20); //thickness

        // secondary X shape size based on the primary X shape size
        secondaryXSize = map(maxAmplitude, 0, 1, secondaryMinSize, secondaryMaxSize);
        secondaryYSize = secondaryXSize;

        // Ensure each corner of the secondary X shape is always visible
        secondaryXSize = min(secondaryXSize, width / 2);
        secondaryYSize = min(secondaryYSize, height / 2);

        //additional smoothing to secondary X shape size
        secondaryXSize = lerp(lastSecondaryXSize, secondaryXSize, 0.05f); 
        secondaryYSize = lerp(lastSecondaryYSize, secondaryYSize, 0.05f); 

        // Store current secondary X shape size for smoothing
        lastSecondaryXSize = secondaryXSize;
        lastSecondaryYSize = secondaryYSize;

        //thickness of the secondary X shape
        secondaryThickness = map(maxAmplitude, 0, 1, 2, 8); // Adjusted thickness

        // Draw primary X shape
        translate(width / 2, height / 2);
        drawXShape(-xSize, -ySize, xSize, ySize, thickness);

        // Draw secondary X shape
        drawXShape(-secondaryXSize / 2, -secondaryYSize / 2, 
        secondaryXSize / 2, secondaryYSize / 2, secondaryThickness);


        //color offset for primary X shape
        colorOffset += colorChangeSpeed;
    }

    // Method to draw X shape
    void drawXShape(float x1, float y1, float x2, float y2, float thickness) 
    {
        // Set stroke weight
        strokeWeight(thickness);

        // Set stroke color based on RGB cycling
        int r, g, b;
        if (thickness == secondaryThickness) 
        {
            // Secondary X shape color: blue, white, pink
            int colorOffsetMod = colorOffset % 765; // Ensure colorOffset stays within 0-765 range
            if (colorOffsetMod < 255) 
            {
                r = 0;
                g = colorOffsetMod;
                b = 255;
            } 
            else if (colorOffsetMod < 510) 
            {
                r = 255;
                g = 255;
                b = 255;
            } 
            else 
            {
                r = 255;
                g = 255 - (colorOffsetMod - 510);
                b = 255;
            }
        } 
        else 
        {
            // Primary X shape color
            int colorOffsetMod = colorOffset % 510; // Ensure colorOffset stays within 0-510 range
            if (colorOffsetMod < 255) 
            {
                r = 255 - colorOffsetMod;
                g = colorOffsetMod;
                b = 255;
            } 
            else 
            {
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
    
} 

    