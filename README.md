# Music Visualiser Project

Names: Oisin Cruise, Conor Fee, Mark Langan, Jake Walsh

Student Number: C22517166, C22414306, C22470414, C22493266

Song: stayinit by Fred again



# Description of the assignment
4 visulas baded on the song stayinit
-Interactive X design, 

# Instructions

# How it works
For Marks(C22470414) Visual:
Class Declaration: This is the main class of the program, named "test". It extends a class called "Visual".
Variables: Various variables are declared to hold data like audio amplitude, sizes, colors, and timing information.
settings(): This method sets up the size and rendering mode of the window.
setup(): This method initializes the program. It sets up audio processing, loads an audio file, initializes arrays, sets initial values for visual elements.
draw(): This method is the main loop of the program. It checks if an introductory animation is done. If not, it runs the introduction. If the intro is done, it processes the audio, calculates amplitudes, adjusts the visual elements based on the music, draws the visuals, and manages audio playback timing.
intro(): This method handles the introduction animation, displaying text on the screen with fading effects.
drawXShape(): This method draws X shapes on the screen. It adjusts the thickness and color of the lines based on parameters passed to it.
The program essentially listens to music, analyzes its characteristics, and creates visual patterns based on that analysis. It also includes an introduction animation at the beginning.

For ConorVisual : C22414306

![An image](images/ConorVisual.png)

ConorVisual.java uses the Minim library for audio processing and the Processing library for rendering graphics. This visualizer creates a dynamic spiral of circles that react to the music being played.
The ConorVisual class extends the Visual class from the ie.tudublin package, inheriting its properties and methods. The key components of this class include: *Audio Processing, Display Setup and Drawing and Visualisation.*
For the Audio Processing, The Minim library is used to handle audio input and playback. An AudioPlayer object (ap) is initialised to load and play an MP3 file ("stayinit.mp3").
The audio buffer (ab) is retrieved from the audio player to access real-time audio data.
For the Display setup, The settings method configures the display to be full-screen with 3D rendering enabled. The setup method initializes the Minim instance, loads the audio file, and sets up the color mode and initial buffer values.
For the Drawing and Visualisation, The draw method is the core rendering loop that executes repeatedly. It processes the audio data and updates the visual representation. Audio data is smoothed and stored in *lerpedBuffer* to create a more fluid visual effect.
The visual mode (mode 0, switch statement used for testing purposes) generates a spiral of circles. This involves: Mapping audio data to circle sizes and colors. Calculating the position of each circle using polar coordinates (angle and radius). Drawing circles with colors mapped to the hue spectrum, creating a vibrant, responsive spiral.
The spiral visualization is achieved through a loop that iterates over the audio buffer:
Radius and Angle Calculation: Each circle's position is determined by incrementing the radius (radiusStep) and angle (angleStep).
Color Mapping: Each circle's color is determined by mapping its index to the hue spectrum, ensuring a diverse range of colors.
Circle Drawing: The ellipse function is used to draw each circle at the calculated position, with its size proportional to the audio buffer's amplitude.
This setup results in a dynamic, colorful spiral of circles that pulsate and change in response to the music, providing an engaging visual experience synchronized with the audio.


# What I am most proud of in the assignment
(c22470141)Being able to use my knowlegde of java to create art, based on music and not just answer questions on a paper.

# Stayinit Music Visual Video

[![YouTube](http://img.youtube.com/vi/6M4VD_6KQxA/0.jpg)](https://youtu.be/6M4VD_6KQxA)

