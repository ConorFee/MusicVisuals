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
        
    private PApplet testVisual;
    private PApplet conorVisual;
    private PApplet sound1Visual;
    private PApplet jVisual;

    public void startUI() {
        String[] a = { "MAIN" };
        startTime = millis(); // Record the start time
        
        // Initialize instances for each visual
        testVisual = new test();
        conorVisual = new ConorVisual();
        sound1Visual = new Sound1();
        jVisual = new JVisual();
        
        while (true) {
            currentTime = millis() - startTime; // Calculate current time elapsed

            // Check if it's time to start each visual
            if (currentTime >= 0 && currentTime <= 28000 && !started1) {
                processing.core.PApplet.runSketch(a, testVisual);
                started1 = true;
            } else if (currentTime > 28000 && currentTime <= 76000 && !started2) {
                processing.core.PApplet.runSketch(a, conorVisual);
                started2 = true;
            } else if (currentTime > 76000 && currentTime <= 133000 && !started3) {
                processing.core.PApplet.runSketch(a, sound1Visual);
                started3 = true;
            } else if (currentTime > 133000 && currentTime <= 168000 && !started4) {
                processing.core.PApplet.runSketch(a, jVisual);
                started4 = true;
            } else if (currentTime > 280000) {
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
