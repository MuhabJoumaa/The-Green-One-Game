# The Green One

A computer game project called **The Green One** featuring a social network with various features and its own website.

## Overview

This is a single-player online 2D-3D action-adventure game with extensive and diverse functionality, including camera and voice messaging capabilities.

## Technologies Used

The game is built using:
- ![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Java AWT](https://img.shields.io/badge/Java_AWT-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Java Swing](https://img.shields.io/badge/Java_Swing-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![JOGL](https://img.shields.io/badge/JOGL-%23FFFFFF.svg?style=for-the-badge&logo=opengl)
- ![C](https://img.shields.io/badge/C-%2300599C.svg?style=for-the-badge&logo=c&logoColor=white)
![C++](https://img.shields.io/badge/C++-%2300599C.svg?style=for-the-badge&logo=c%2B%2B&logoColor=white)
- ![MySQL](https://img.shields.io/badge/MySQL-%2300758F.svg?style=for-the-badge&logo=mysql&logoColor=white)
- ![Neo4J](https://img.shields.io/badge/Neo4j-008CC1?style=for-the-badge&logo=neo4j&logoColor=white)
- ![Pascal](https://img.shields.io/badge/Pascal-%23E3F171.svg?style=for-the-badge&logo=delphi&logoColor=black) (for the setup script)
- ![OpenGL](https://img.shields.io/badge/OpenGL-%23FFFFFF.svg?style=for-the-badge&logo=opengl)
- ![OpenCV](https://cdn.jsdelivr.net/gh/devicons/devicon/icons/opencv/opencv-original-wordmark.svg) (for web camera)
- ![HTML5](https://img.shields.io/badge/HTML5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![jQuery](https://img.shields.io/badge/jQuery-blue?logo=jquery&logoColor=white)
 (for the game's website)
- ![Blender](https://img.shields.io/badge/Blender-%23F5792A.svg?style=for-the-badge&logo=blender&logoColor=white) (for the 3D assets)
- ![MATLAB](https://cdn.jsdelivr.net/gh/devicons/devicon/icons/matlab/matlab-original.svg) (for the calculations before the programming phase)
- ![CorelDRAW](https://img.shields.io/badge/CorelDRAW-%2300A95C.svg?style=for-the-badge&logo=coreldraw&logoColor=white) (for the game's logo)

## Key Features

### Graphics & Game Engine
- Complex 3D character models partially created with Blender, then imported, processed, and controlled through Interactive Mesh
- Terrain in 3D stages generated using Perlin noise algorithm
- Built entirely from scratch with custom game engine including physics, projectile motion physics, geometry, linear algebra, shadows, collision detection, lighting, etc.
- Support for adjustable screen resolution, frame rate (FPS), and audio volume through game settings

### Database & Backend
- MySQL database and Backblaze server integration
- Neo4j AuraDB database dedicated to player messaging for easy storage and display of messages between players

### Social Network Features
- Player search and communication via text messages, voice messages, photos, and emojis
- Real-time online status indicators showing:
  - Whether a player is currently online
  - Last connection date and time if offline
  - "typing..." indicator when composing a text message
  - "recording..." indicator when recording a voice message
- Story feature allowing players to post images or GIFs to their profile (like Instagram and WhatsApp), visible to all players for 24 hours
- Ability to delete or modify your own stories
- Verified account system
- View recent scores/results of all other players
- Block/unblock specific players through settings
- Privacy controls to show or hide online status

### Customization & Media
- Custom font (non-default Windows font) modified to include emojis for messaging
- Profile photo upload and modification:
  - Direct camera capture (if available) with JPG save
  - Upload any image stored on the computer
- In-game screenshot capture via dedicated button

### Communication & Feedback
- Multiple ways to send complaints or feedback to the developer:
  - Send email to developer from within the game
  - Send SMS

### Security
- Two-stage password encryption during account registration:
  1. MD5 hashing
  2. Additional encryption algorithm
- Email validation for new account registration

### Accessibility
- Computer speech system (optional through settings) that reads in-game texts in English with human voice
- Support for gamepad in addition to keyboard and mouse

### Additional Features
- News section about the game
- "Forgot password" section
- Tutorial section explaining how to play
- Simple in-game store for free character add-ons/extensions
- Account data management in settings

### Auto-Update System
The game includes a separate program that:
- Detects updates or new game versions
- Notifies players when a new version is available
- Automatically downloads and replaces the old version (no external website or program needed)
- Displays new version number, file size in megabytes, and precise download time remaining
- Functions similar to a download manager but specifically for the game
- Built using Java NET API library

## Platform & Architecture

- Currently available for ![Windows](https://cdn.jsdelivr.net/gh/devicons/devicon/icons/windows8/windows8-original.svg) operating system
- Easy portability to other operating systems since the game's core is built in Java (a multiplatform language)
- Connection between Java and C++ via JNI (Java Native Interface)
- Utilizes many external technologies, libraries, and frameworks

## Distribution

The game is published on:
- **itch.io**
- **VK Play**

---

*A comprehensive action-adventure game with integrated social networking features, built from the ground up with custom engine technology.*
