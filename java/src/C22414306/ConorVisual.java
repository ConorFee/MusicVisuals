package C22414306;

import ddf.minim.AudioPlayer;
import ie.tudublin.*;

public class ConorVisual extends Visual
{

    private AudioPlayer ap;

    int mode = 0;

    public void keyPressed() 
    {
        if (key == ' ') 
        {
            getAudioPlayer().play();
        }
	}

    public void settings()
    {
        size(1024, 1000, P3D);
    }

    public void setup()
    {
        startMinim();
        loadAudio("stayinit.mp3");

        startListening();
        
    }

    public void draw() 
    {
        
    }

}