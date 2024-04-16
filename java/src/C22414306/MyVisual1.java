package C22414306;

import ddf.minim.AudioPlayer;
import ie.tudublin.*;

public class MyVisual1 extends Visual
{

    private AudioPlayer audioPlayer;

    public void keyPressed() 
    {
        
	}

    public void settings()
    {
        size(1024, 1000, P3D);
    }

    public void setup()
    {
        startMinim();
        loadAudio("stayinit.mp3");
        
        audioPlayer = getAudioPlayer();
    }

    public void draw() 
    {
        
    }

}
