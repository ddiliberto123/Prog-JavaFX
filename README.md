# Prog-JavaFX
Group Term Project for 420-203-RE

Group Members:
-Daniel Diliberto
-Samuel Marziliano

## Presentation: https://docs.google.com/presentation/d/1TSuybud0eG9NEw1A5J1b-Ou7nbwAyAeCA-jcfqRL7rg/edit

---

# User Manual:


### Overview

&nbsp;This project was made in order to plot the orbits of planets around the sun. The orbit of these bodies are calculated using mechanic's kinematic formulas in conjuction with Euler's method. Put simply, the orbits demonstrated through the program are not exact, but are good approximations.

## Table of Contents
1. [Utilization](#Utilization)
2. [Step by Step Guide](#Step-by-step-guide) <p>
  a) [The Menu Bar](#The-menu-bar) <p>
  b) [The left panel](#The-left-panel) <p> 
  c) [The Chart](#The-chart)
3. [Troubleshooting](#troubleshooting)

### Utilization
&nbsp;Before exploring how to create and edit presets, the user must understand how the program works. The program takes in 5 values:
  1. The Name of the planet you'd like to create
  2. The mass of the planet you'd like to create
  3. The distance from the sun the planet initially starts from
  4. The initial velocity of the planet in both x, y directions.
  5. The year trajectory
      a) The year trajectory refers to the amount of time the program projects the planet's orbit will be.
  
### Step By Step Guide

&nbsp;Once confronted by the menu screen, clicking the start button will send the user to the main screen. Once there, the user has the following options to select from:

#### The Menu Bar:
  There are three options to choose from: 
    1. File: Clicking on close, closes the program.
    2. Edit: Clicking on clear all presets, clears all the presets.
    3. Help: Sends you to the "About" page which further explains characteristics of the program.

#### [The left panel](https://github.com/ddiliberto123/Prog-JavaFX/assets/114122493/cb095d09-ecd2-4200-8a2f-e49e68bb40fe):

&nbsp;The left portion of the screen displays the potential data the user may input. Clicking on "Planet Presets" will engage a drop down menu allowing for the user to click on the planet names they'd like to generate into the chart. <p>
    When a planet is drawn inside the chart, a checkmark will be visible and the planet's respective name and colour will be displayed below the chart. <p>
&nbsp;Clicking on the planet names within the "Planet Presets" drop down will additionally auto-fill the data fields with the data from the planet that was most recently selected. The user may modify this data, while keeping the planet name the same in order to override the already existing information. Otherwise, if the user would like to create a new planet, they can put their own respective data and planet name. 

First get to the main screen by clicking the start button on the main menu that will appear on launch of the program.


Once on the main screen, press the Planet Preset box and check a box that is next to the name of the desired orbit in order to display specific orbits.


If a new preset is to be generated then first fill out the name text box, then give the distance in meters and mass kilograms. Then input the initial x and y velocities and then the save preset button should be enabled. If not, check that you have inputted only numbers in the value boxes and that the name text box is not blank.


After that the new preset should be visible when the Planet Preset box is pressed, to display it press the check box next to its name.



UI Description


Home screen


Start button: brings the user to the main screen of the application


Exit button: closes the application



Main screen


Menu Bar -> File -> Close: Closes the application


Menu Bar -> File -> Clear All Presets: Gets rid of all existing presets, including the automatically generated ones.


Menu Bar -> About -> Brings the user to the about page with information regarding the project. In order to return to the main screen go into the menu bar and select file then click the back button.  


Planet Preset box: This is the selection method to load and generate orbits. When the drop down menu is pressed all the presets that have been saved are seen. They can be plotted by checking the box next to their names. This will display all orbits that have been checked off.


Name Text Box: This the box that will display the name of the selected preset or the name that will be given to the new preset.


Mass Text Box: This the box that will display the mass of the selected preset in kilograms or the mass that will be given to the new preset.


Distance Text Box: This the box that will display the distance in meters of the selected preset from the sun or the distance that will be given to the new preset.


Initial Velocity Text Boxes: These two boxes will display the x and y velocities in meters per second of the current preset in their respective boxes or the x and y velocities that will be given to the new preset.


Clear Button: Will clear all text boxes.


Save Preset: This button will remain disabled until all of the above text boxes are filled with appropriate information. This means that the name must be filled out and only numbers must be present for the other values.


Delete Preset: This button will delete the selected preset, if no preset is selected or exists it does nothing.


Generate All Orbits Button: This button will generate all orbits from all existing presets that are saved.


Year Trajectory slider: This slider changes the amount of time in years that the orbits are plotted, by default it is set to one.


Graph: The graph will display the orbits that have been selected. The graph can be traversed by clicking and dragging the mouse. Zooming in can be done with the scroll wheel.


Name Box: This box will display the name and color of the orbits that are currently on the graph.
