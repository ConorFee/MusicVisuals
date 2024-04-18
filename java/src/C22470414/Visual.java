package C22470414;
import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;

public class Visual extends PApplet {

    Minim minim;
    AudioPlayer player;
    FFT fft;

    float[] smoothedAmplitude;
    float smoothingFactor = 0.9f; // Smoothing factor for the low-pass filter
    float terrainHeight = 100; // Height of the terrain
    float terrainDetail = 10; // Detail of the terrain

    @Override
    public void settings() {
        size(800, 600); // Set the size of the window
    }

    @Override
    public void setup() {
        colorMode(HSB);
        minim = new Minim(this);

        // Load audio file
        player = minim.loadFile("stayinit.mp3", 1024);
        player.play();

        fft = new FFT(player.bufferSize(), player.sampleRate());

        // Initialize the smoothed amplitude array
        smoothedAmplitude = new float[fft.specSize()];
    }

    @Override
    public void draw() {
        background(0);

        // Calculate spectrum
        fft.forward(player.mix);

        // Smooth amplitude values
        for (int i = 0; i < fft.specSize(); i++) {
            float amplitude = fft.getBand(i);
            smoothedAmplitude[i] = lerp(smoothedAmplitude[i], amplitude, smoothingFactor);
        }

        // Draw terrain
        float startX = 0;
        float endX = width;
        float startY = height - terrainHeight;
        float step = terrainDetail;

        beginShape();
        vertex(startX, height);
        for (float x = startX; x < endX; x += step) {
            float index = map(x, startX, endX, 0, fft.specSize() - 1);
            float amplitude = smoothedAmplitude[(int) index];
            float y = startY + map(amplitude, 0, 1, 0, terrainHeight);
            vertex(x, y);
        }
        vertex(endX, height);
        endShape(CLOSE);
    }

    // Add method to set audio player for the sketch
    public void setAudioPlayer(AudioPlayer player) {
        this.player = player;
    }
}

