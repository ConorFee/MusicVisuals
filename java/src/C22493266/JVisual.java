package C22493266;

import java.util.ArrayList;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ie.tudublin.Visual;
import processing.core.PVector;

public class JVisual extends Visual {

    Minim m;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer b;

    float circleSize; // Put circleSize up here as I was having trouble accessing it in the clouds function

    ArrayList<PVector> cloudPositions; // This list stores clicked locations for clouds
    ArrayList<Smoke> smokes = new ArrayList<>();
    int smokeTimer = 0;

    @Override
    public void settings() {
        fullScreen(P3D, SPAN); // Full-screen mode
    }

    @Override
    public void setup() {
        m = new Minim(this);
        ap = m.loadFile("stayinit.mp3");
        ap.play(133000);
        b = ap.mix;

        cloudPositions = new ArrayList<>(); // Initialize list
    }

    public boolean started1 = false;

    @Override
    public void draw() {

        float avg = 0;
        for (int i = 0; i < b.size(); i++) {
            avg += abs(b.get(i));
        }
        avg /= b.size();
        circleSize = map(avg, 0, 1, 50, 300); 

        drawMountains(avg);
        drawMountainsShadow(avg);
        drawFields();
        drawCow(avg, circleSize);
        drawShroom(avg);

        cloudShadow();
        clouds();

        for (int i = smokes.size() - 1; i >= 0; i--) {
            Smoke smoke = smokes.get(i);
            smoke.update();
            smoke.display();
            if (smoke.isDead()) {
                smokes.remove(i);
            }
        }

        if (smokeTimer % 50 == 0) {
            float jointX = width * 0.5f + 50;
            float jointY = height * 0.9f - 29;
            smokes.add(new Smoke(jointX, jointY));
        }
        smokeTimer++;

        int startTime = millis();

        if (startTime > 168000 && startTime < 260000) {
            ap.pause(); 
        }
        
        if (startTime > 260000 && startTime <= 281000 && !started1) {
            ap.play(260000); 
            started1 = true;
        } else if (startTime > 281000) {
            ap.pause(); 
        }

    }

    public void mousePressed() {
        cloudPositions.add(new PVector(mouseX, mouseY)); 
    }

    private void drawFields() {
        fill(70, 249, 255);
        rect(0, height * 0.7f, width, height * 0.3f);
    }

    private void drawMountains(float avg) {
        fill(255, 0, 100);
        beginShape(TRIANGLES);
        float mountainHeightFactor = map(avg, 0, 1, 30, 80); // Dynamic height factor
        for (int i = 0; i < width; i += 30) { // 30 px spacing
            vertex(i, height * 0.7f); // start base point
            vertex(i + 25, height * 0.5f - random(mountainHeightFactor)); // middle point with dynamic height
            vertex(i + 50, height * 0.7f); // end point at base
        }
        endShape();
    }

    private void drawMountainsShadow(float avg) {
        beginShape(TRIANGLES);
        float shadowHeightFactor = map(avg, 0, 1, 40, 100); // variation in shadow
        for (int i = 0; i < width; i += 30) {
            float hue = map(avg, 0, 1, 120, 255);
            fill(hue, 255, 255);
            vertex(i, height * 0.7f);
            vertex(i + 25, height * 0.5f - random(shadowHeightFactor));
            vertex(i + 50, height * 0.6f);
        }
        endShape();
    }

    private void drawCow(float avg, float circleSize) {
        float cowX = width * 0.5f;
        float cowY = height * 0.9f;

        // Body
        fill(255);
        ellipse(cowX, cowY, 100, 150);

        // Ears
        fill(255);
        ellipse(cowX - 22, cowY - 100, 20, 20);
        ellipse(cowX + 22, cowY - 100, 20, 20);

        // Head
        ellipse(cowX, cowY - 60, 65, 85);

        // mouth
        fill(0);
        ellipse(cowX, cowY - 29, circleSize / 2, circleSize / 4);

        // joint
        fill(9, 200, 100); // Brown color for the joint
        noStroke();
        rect(cowX + 10, cowY - 29, 40, 6);
        fill(255, 255, 255);
        ellipse(cowX + 50, cowY - 26, 6, 6);

        // Eyes
        float hue = map(avg, 0, 1, 0, 255);
        fill(hue, 255, 255);
        ellipse(cowX - 20, cowY - 70, 30, 30);
        ellipse(cowX + 20, cowY - 70, 30, 30);
        fill(0);
        ellipse(cowX - 20, cowY - 70, 30, 9);
        ellipse(cowX + 20, cowY - 70, 30, 9);

        // Legs and spots
        fill(0);
        rect(cowX - 45, cowY + 30, 15, 40);
        rect(cowX + 30, cowY + 30, 15, 40);
        ellipse(cowX - 10, cowY, 20, 15);
        ellipse(cowX + 20, cowY + 10, 25, 20);

        // Tail
        stroke(0);
        line(cowX + 50, cowY, cowX + 70, cowY - 20);
        fill(0);
        ellipse(cowX + 70, cowY - 20, 10, 10);
        noStroke();
    }

    void drawShroom(float avg) {
        float shroomX = 200;
        float shroomY = 500;

        // stem of the mushroom
        fill(255);
        rect(shroomX, shroomY, 14, 50);

        // shadow of the mushroom cap
        float hue = map(avg, 0, 1, 00, 255);
        fill(hue, 255, 255);
        arc(shroomX + 8, shroomY, 100, 60, PI, TWO_PI);
        // actual cap
        fill(00, 255, 255);
        arc(shroomX + 8, shroomY, 80, 40, PI, TWO_PI);

        // spots
        fill(255, 0, 255);
        ellipse(shroomX, shroomY - 20, 10, 10);
        ellipse(shroomX - 20, shroomY - 7, 10, 10);
        ellipse(shroomX + 30, shroomY - 15, 10, 10);
    }

    public void clouds() {
        fill(255);
        noStroke();
        int DECRET = 30;

        for (PVector pos : cloudPositions) {
            ellipse(pos.x, pos.y, circleSize - DECRET, circleSize - DECRET);
            ellipse(pos.x - 20, pos.y + 0, circleSize - DECRET, circleSize - DECRET);
            ellipse(pos.x + 5, pos.y - 5, circleSize - DECRET, circleSize - DECRET);
            ellipse(pos.x + 2, pos.y + 5, circleSize - DECRET, circleSize - DECRET);
            ellipse(pos.x + 15, pos.y + 0, circleSize - DECRET, circleSize - DECRET);
            ellipse(pos.x - 10, pos.y + 3, circleSize - DECRET, circleSize - DECRET);
            ellipse(pos.x - 10, pos.y - 3, circleSize - DECRET, circleSize - DECRET);
        }
    }

    public void cloudShadow() {
        noStroke();
        int DECRET = 20;

        for (PVector pos : cloudPositions) {
            fill(0);
            ellipse(pos.x, pos.y, circleSize - DECRET, circleSize - DECRET);
            ellipse(pos.x - 20, pos.y + 0, circleSize - DECRET, circleSize - DECRET);
            ellipse(pos.x + 5, pos.y - 5, circleSize - DECRET, circleSize - DECRET);
            ellipse(pos.x + 2, pos.y + 5, circleSize - DECRET, circleSize - DECRET);
            ellipse(pos.x + 15, pos.y + 0, circleSize - DECRET, circleSize - DECRET);
            ellipse(pos.x - 10, pos.y + 3, circleSize - DECRET, circleSize - DECRET);
            ellipse(pos.x - 10, pos.y - 3, circleSize - DECRET, circleSize - DECRET);
        }
    }

    class Smoke {
        PVector position;
        float size;
        float opacity;

        Smoke(float x, float y) {
            position = new PVector(x, y);
            size = random(5, 10);
            opacity = 255;
        }

        void update() {
            position.y -= random(0.5F, 1.5F);
            size *= 1.03; 
            opacity -= 3; 
        }

        void display() {
            noStroke();
            fill(255, opacity);
            ellipse(position.x, position.y, size, size);
        }

        boolean isDead() {
            return opacity <= 0 || size <= 0;
        }

    }

}
