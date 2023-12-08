# Prog-JavaFX
Group Term Project for 420-203-RE

Group Members:
1. Daniel Diliberto
2. Samuel Marziliano

#### Presentation: https://docs.google.com/presentation/d/1TSuybud0eG9NEw1A5J1b-Ou7nbwAyAeCA-jcfqRL7rg/edit

---

# User Manual:


### Overview

&nbsp;This project was made in order to plot the orbits of planets around the sun. The orbit of these bodies are calculated using mechanic's kinematic formulas in conjuction with Euler's method. Put simply, the orbits demonstrated through the program are not exact, but are good approximations.

## Table of Contents
1. [Utilization](#Utilization)
2. [Step by Step Guide](#Step-by-step-guide) <p>
  a) [The Menu Bar](#The-menu-bar) <p>
  b) [The left panel](#The-left-panel) <p> 
  c) [The right panel](#The-right-panel)
3. [Troubleshooting](#troubleshooting) <p>
  a) ["My Orbit looks weird"](#"My-Orbit-looks-weird") <p>
  b) [Checkbox Broken](#Checkbox-broken) <p>
  c) [Program laggy](#Program-laggy) 
4. [References](#references)

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

#### The Left Panel:

&nbsp;The left portion of the screen displays the potential data the user may input. In the following image, the red region is the "left panel":<p>
![image](https://github.com/ddiliberto123/Prog-JavaFX/assets/114122493/521cdc76-be72-4041-b786-e37c09078bef) <p>
Clicking on "Planet Presets" will engage a drop down menu allowing for the user to click on the planet names they'd like to generate into the chart. When a planet is drawn inside the chart, a checkmark will be visible and the planet's respective name and colour will be displayed below the chart. <p><p>
&nbsp;Clicking on the planet names within the "Planet Presets" drop down will additionally auto-fill the data fields with the data from the planet that was most recently selected. The user may modify the data within the textfields, while keeping the planet name the same in order to override the already existing information. Otherwise, if the user would like to create a new planet, they can put their own respective data and planet name. <p>

&nbsp; The additional 4 buttons allow the user to do the following:
1. The "Clear" button allows the user to clear the textfields of data. <p>
Note: this does not clear the data associated with the planet, in the instance where the data was auto-filled.
2. The "Save Preset" button allows the user to save a preset or override an already existing preset using the data contained in the textfields. <p>
Note: The data within the datafields must be valid for the button to be enabled.
3. The "Delete Preset" button deletes the preset currently named within the "Name" field.
4. The "Generate All Orbits" button shows the orbits of all planets within the chart.

#### The Right Panel:
&nbsp;The right portion of the screen is completely comprised of the chart where the planets are to be displayed. In the following image, the green region is the "right panel":<p>
![image](https://github.com/ddiliberto123/Prog-JavaFX/assets/114122493/521cdc76-be72-4041-b786-e37c09078bef) <p>
At the bottom of the chart, the current showing planets will be displayed with their respective colours to the left of their names. The sun will always be present and displayed at 0,0 within the chart. The user may also additionally pan within the chart to move arround. The user may also scroll either inside the chart, to keep a 1:1 ratio, or on the axises themselves if desired.

### Troubleshooting:

#### "My Orbit looks weird":
&nbsp; If the orbit looks weird there are two solutions to fix the problem.
1. Deselect and reselect the desired planet, allowing it to disappear from the chart and then re-appear.
2. Make sure the chart is 1:1, scrolling on the axis can decrease the potential stretching that may have occured.

#### Checkbox Broken:
&nbsp; If the checkbox doesn't display the correct value, i.e. the checkbox is unchecked even though the orbit is shown, deselect and reselect the associated planet.

#### Program laggy:
&nbsp; If the program is perceived as slow, give is some time to compute the values desired. Please note: changing the trajectory very quickly may be the cause of this interruption/reduction in speed.

### References:
&nbsp; The following sources where used as references in the completion of this program.
1. [OpenStax](https://openstax.org/books/university-physics-volume-1/pages/13-1-newtons-law-of-universal-gravitation) 
2. [Wikipedia](https://en.wikipedia.org/wiki/Euler_method#:~:text=In%20mathematics%20and%20computational%20science,with%20a%20given%20initial%20value)

