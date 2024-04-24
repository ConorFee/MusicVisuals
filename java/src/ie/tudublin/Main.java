package ie.tudublin;

import C22414306.ConorVisual;
import C22470414.test;
import C22493266.JVisual;
import C22517166.Sound1;
import processing.core.PApplet;

public class Main extends PApplet {

    private long startTime;
    private long currentTime;
    private boolean started1 = false;
    private boolean started2 = false;
    private boolean started3 = false;
    private boolean started4 = false;
    private boolean started5 = false;
    private boolean started6 = false;
    private boolean started7 = false;
    private boolean started8 = false;

    public void startUI() {
        String[] a = { "MAIN" };
        startTime = millis(); // Record the start time
        while (true) {
            currentTime = millis() - startTime; // Calculate current time elapsed

            // Check if it's time to start each visual
            if (currentTime >= 0 && currentTime <= 28000 && !started1) {
                processing.core.PApplet.runSketch(a, new test());
                started1 = true;
            } else if (currentTime > 28000 && currentTime <= 76000 && !started2) {
                processing.core.PApplet.runSketch(a, new ConorVisual());
                started2 = true;
            } else if (currentTime > 76000 && currentTime <= 133000 && !started3) {
                processing.core.PApplet.runSketch(a, new Sound1());
                started3 = true;
            } else if (currentTime > 133000 && currentTime <= 168000 && !started4) {
                processing.core.PApplet.runSketch(a, new JVisual());
                started4 = true;
            } else if (currentTime > 168000 && currentTime <= 196000 && !started5) {
                processing.core.PApplet.runSketch(a, new ConorVisual());
                started5 = true;
            } else if (currentTime > 196000 && currentTime <= 213000 && !started5) {
                processing.core.PApplet.runSketch(a, new Sound1());
                started6 = true;
            } else if (currentTime > 213000 && currentTime <= 238000 && !started5) {
                processing.core.PApplet.runSketch(a, new test());
                started7 = true;
            } else if (currentTime > 238000 && currentTime <= 260000 && !started5) {
                processing.core.PApplet.runSketch(a, new ConorVisual());
                started8 = true;
            } else if (currentTime > 260000 && currentTime <= 281000 && !started5) {
                processing.core.PApplet.runSketch(a, new ConorVisual());
                started8 = true;
            } else if (currentTime > 281000) {
                break; // End the loop when all visuals have been triggered
            }

            delay(1000); // Wait for 1 second before checking again
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}
