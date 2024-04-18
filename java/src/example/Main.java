// Packages must match the folder structure
package example;

public class Main
{

   public void helloProcessing()
   {
       String[] a = {"MAIN"};
       processing.core.PApplet.runSketch( a, new CubeVisual1());
   }
   
   public static void main(String[] args)
   {
       System.out.println("Hello world");
    
       Main m = new Main();

       m.helloProcessing();
   }
   
}

