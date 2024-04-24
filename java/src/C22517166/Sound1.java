package C22517166;

import ddf.minim.Minim;
import ie.tudublin.Visual;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.analysis.FFT;
import processing.core.PVector;
//import processing.core.PApplet;
//import processing.core.PApplet;
import processing.core.PFont;

public class Sound1 extends Visual {
    
    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;
    FFT fft;

    int cols, rows;
    int scale = 20;
    int w = 2000;
    int h = 1600;

    float[][] terrain;
    float flying = 0;
    PVector currentColor, targetColor;
    int colorChangeFrames = 120;
    int frameCounter;

    PFont font;
    float angle = 0;

    @Override
    public void settings() {
        size(1200, 1200, P3D);
    }

    @Override
    public void setup() {
        colorMode(HSB, 360, 100, 100);
        cols = w / scale;
        rows = h / scale;
        terrain = new float[cols][rows];
        minim = new Minim(this);
        ap = minim.loadFile("stayinit.mp3", 1024);
        if (ap == null) {
            System.err.println("Failed to load 'stayinit.mp3'");
            return; // Exit if file fails to load
        }

        ap.play(76000);
        //ap.mute();

        ab = ap.mix; // Ensure this is set after the player is confirmed to play
        fft = new FFT(ap.bufferSize(), ap.sampleRate());
    
        currentColor = new PVector(random(360), 100, 100);
        targetColor = new PVector(random(360), 100, 100);
        frameCounter = 0;
    
        font = createFont("Arial", 48, true);
        textFont(font);
    }
    
    @Override
    public void draw() {
        
        flying -= 0.1f;
        float yoffset = flying;
        float maxAmp = 0;
        int startTime = millis();

        for (int i = 0; i < ab.size(); i++) {
            maxAmp = max(ab.get(i), maxAmp);
        }

        for (int y = 0; y < rows; y++) {
            float xoffset = 0;
            for (int x = 0; x < cols; x++) {
                float baseHeight = map(noise(xoffset, yoffset), 0, 1, -120, 120);
                terrain[x][y] = baseHeight + (maxAmp * 300);
                xoffset += 0.2f;
            }
            yoffset += 0.2f;
        }

        if (frameCounter++ >= colorChangeFrames) {
            frameCounter = 0;
            currentColor.set(targetColor.x, targetColor.y, targetColor.z);
            targetColor.set(random(360), 100, 100);
        }

        currentColor.lerp(targetColor, 1.0f / colorChangeFrames);
        background(currentColor.x, currentColor.y, currentColor.z);

        translate(width / 2, height / 2, -50); // Adjust the z-coordinate to bring the text in front
        rotateX(PI / 3);
        translate(-w / 2, -h / 2);

        for (int y = 0; y < rows - 1; y++) {
            beginShape(TRIANGLE_STRIP);
            for (int x = 0; x < cols; x++) {
                float z = terrain[x][y];
                float z1 = terrain[x][y + 1];
                fill(random(360), 100, 100);
                stroke(random(360), 100, 80);
                strokeWeight(2);
                vertex(x * scale, y * scale, z);
                vertex(x * scale, (y + 1) * scale, z1);
            }
            endShape();
        }

        pushMatrix();
        translate(width / 2, height / 2 + 150, 50); // Center the text and move it forward
        rotateY(angle); // Rotate around the y-axis
        fill(0, 0, 100); // High visibility white color
        text("stayinit", -textWidth("stayinit") / 2, 0);
        popMatrix();

        angle += 0.05;

        // Resume audio playback when the second track begins
        if (startTime > 133000) {
            ap.pause(); // Pause the audio playback
        }

        // Check if the current time is between 196000 and 213000 milliseconds
        if (millis() > 196000 && millis() < 213000) {
            if (!ap.isPlaying()) { // Check if the audio is not already playing
                ap.play(196000); // Resume audio playback
            }
        } else if (millis() > 213000) {
            ap.pause(); // Pause the audio playback
        }

    }
}
