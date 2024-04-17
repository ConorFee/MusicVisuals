package C22414306;

public class Main
{
    
    public void startUI() 
    {   
        String[] a = { "MAIN" };
        
        processing.core.PApplet.runSketch(a, new  ConorVisual());
    }

    public static void main(String[] args) 
    {
        Main m = new Main();

        m.startUI();
    }
               
}
