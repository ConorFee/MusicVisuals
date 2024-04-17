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

        // Draw lines based on smoothed spectrum
        float centerX = width / 2;
        float centerY = height / 2;
        float maxRadius = min(width, height) * 0.4f; // Maximum radius for the spiral
        float angleIncrement = TWO_PI / fft.specSize(); // Angle increment for each spectrum band
        float angle = 0;

        for (int i = 0; i < fft.specSize(); i++) {
            // Calculate amplitude
            float amplitude = smoothedAmplitude[i];

            // Map amplitude to line length
            float length = map(amplitude, 0, 1, 0, maxRadius);

            // Map amplitude to hue with different ranges for blue and yellow-red colors
            float hue;
            if (amplitude < 0.5) {
                hue = map(amplitude, 0, 0.5f, 180, 240); // Map amplitude to blue colors (180-240)
            } else {
                hue = map(amplitude, 0.5f, 1, 30, 60); // Map amplitude to yellow-red colors (30-60)
            }

            // Calculate endpoint of the line
            float x = centerX + length * cos(angle);
            float y = centerY + length * sin(angle);

            // Set stroke color
            stroke(hue, 255, 255);

            // Draw line
            line(centerX, centerY, x, y);

            // Increment angle for the next spectrum band
            angle += angleIncrement;
        }
    }

    // Add method to set audio player for the sketch
    public void setAudioPlayer(AudioPlayer player) {
        this.player = player;
    }
}
