# Music Visualiser Project

Names: Oisin Cruise, Conor Fee, Mark Langan, Jake Walsh

Student Number: C22517166, C22414306, C22470414, C22493266

Song: stayinit by Fred again



# Description of the assignment
4 visulas baded on the song stayinit

# Instructions

# How it works
For Marks(C22470414) Visual:
Class Declaration: This is the main class of the program, named "test". It extends a class called "Visual", indicating it's probably part of a larger system for visuals.
Variables: Various variables are declared to hold data like audio amplitude, sizes, colors, and timing information.
settings(): This method sets up the size and rendering mode of the window.
setup(): This method initializes the program. It sets up audio processing, loads an audio file, initializes arrays, sets initial values for visual elements, and sets the background color.
draw(): This method is the main loop of the program. It checks if an introductory animation is done. If not, it runs the introduction. If the intro is done, it processes the audio, calculates amplitudes, adjusts the visual elements based on the music, draws the visuals, and manages audio playback timing.
intro(): This method handles the introduction animation, displaying text on the screen with fading effects.
drawXShape(): This method draws X shapes on the screen. It adjusts the thickness and color of the lines based on parameters passed to it.
The program essentially listens to music, analyzes its characteristics, and creates visual patterns based on that analysis. It also includes an introduction animation at the beginning.
# What I am most proud of in the assignment

# Markdown Tutorial

This is *emphasis*

This is a bulleted list

- Item
- Item

This is a numbered list

1. Item
1. Item

This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings
#### Headings
##### Headings

This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

This is an image using a relative URL:

![An image](images/p8.png)

This is an image using an absolute URL:

![A different image](https://bryanduggandotorg.files.wordpress.com/2019/02/infinite-forms-00045.png?w=595&h=&zoom=2)

This is a youtube video:

[![YouTube](http://img.youtube.com/vi/J2kHSSFA4NU/0.jpg)](https://www.youtube.com/watch?v=J2kHSSFA4NU)

This is a table:

| Heading 1 | Heading 2 |
|-----------|-----------|
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |
|Some stuff | Some more stuff in this column |

