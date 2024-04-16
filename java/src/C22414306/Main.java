package C22414306;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class Main
{
    
    public void startUI() 
    {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new  MyVisual1());
    }

    public static void main(String[] args) 
    {
        Main main = new Main();
        main.startUI();
    }
               
}
