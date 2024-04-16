package C22414306;

import ddf.minim.AudioPlayer;
import ddf.minim.AudioBuffer;
import ie.tudublin.*;

public class MyVisual1 extends Visual
{

    private AudioPlayer ap;
    private AudioBuffer ab;

    int mode = 0;
    // Flag to track if audio is playing
    //
    //private boolean isPlaying = false;

    public void keyPressed() 
    {
        if (key >= '0' && key <= '9')
        {
			mode = key - '0';
		}
		if (keyCode == ' ') 
        {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
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
        colorMode(HSB);
        
    }

    public void draw() 
    {
        
    }

}
