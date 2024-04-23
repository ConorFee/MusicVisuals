// Packages must match the folder structure
package C22517166;

public class Main
{

   public void helloProcessing()
   {
       String[] a = {"MAIN"};

       processing.core.PApplet.runSketch( a, new Sound1());
       
   }
   
   public static void main(String[] args)
   {
       System.out.println("Hello world");
    
       Main m = new Main();

       m.helloProcessing();
   }
   
}