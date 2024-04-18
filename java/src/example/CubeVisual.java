package example;

import ie.tudublin.Visual;

public class CubeVisual extends Visual
{
    boolean moreCubes = false;

    public void settings()
    {
        size(800, 800, P3D);
        println("CWD: " + System.getProperty("user.dir"));
    }

    public void keyPressed()
    {
        if (key == ' ')
        {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();
        }
        if (key == '1')
        {
            moreCubes = ! moreCubes;
        }
    }

    public void setup() 
    {
        colorMode(HSB);
        noCursor();
        setFrameSize(256);
        startMinim();
        loadAudio("stayinit.mp3");
    }

    float smoothedBoxSize = 0;
    float angle = 0;

    public void draw()
    {
        calculateAverageAmplitude();
        background(0);
        noFill();
        lights();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        camera(0, 0, 0, 0, 0, -1, 0, 1, 0);
        translate(0, 0, -250);

        float boxSize = 50 + (getAmplitude() * 300);
        smoothedBoxSize = lerp(smoothedBoxSize, boxSize, 0.2f);

        float rotationSpeed = 0.05f + getAmplitude() * 0.1f;  // Dynamically adjust rotation speed
        angle += rotationSpeed;

        if (moreCubes)
        {
            pushMatrix();
            translate(-100, 0, 0);
            rotateY(angle);
            rotateX(angle);
            box(smoothedBoxSize);
            popMatrix();

            pushMatrix();
            translate(100, 0, 0);
            rotateY(angle);
            rotateX(angle);
            strokeWeight(5);
            box(smoothedBoxSize);
            popMatrix();
        }
        else
        {
            rotateY(angle);
            rotateX(angle);
            strokeWeight(5);
            box(smoothedBoxSize);
        }
    }
}
