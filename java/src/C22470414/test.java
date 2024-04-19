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

    