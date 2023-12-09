#Stick Man Hero Game Report File

# how to run the code

// We have even discussed how we used multithreading

This part details out how we used GAMEUI
The provided code represents a JavaFX-based game UI in the GameUI class, extending a MenuScreen. The class encapsulates the graphical elements and logic for a stickman-themed game. It utilizes various JavaFX classes to create a dynamic user interface, allowing players to control the stickman's movements and interact with the game environment.
The constructor initializes the scene and group for displaying graphical elements. It sets up essential rectangles, such as the stickman's body parts, obstacles, and a feed. Additionally, it configures labels, scores, and instructional text. The setRandoms() method is responsible for randomly placing elements like rectangles, feed, and obstacles at different positions, creating a dynamic and challenging gameplay environment.
Event handling is a crucial aspect of the game, and it involves both mouse and keyboard interactions. Multithreading is used to handle mouse events, allowing continuous adjustment of the stickman's position based on mouse movement. Threads are also employed to respond to keyboard events, such as the Enter key for rotation and the Spacebar for inverse movements.
The game's core logic is implemented in methods.These methods control the stickman's movements, update the game state, and handle collisions with obstacles and the feed. The game continuously checks conditions to determine if the game should end, triggering the gameOver() method.
Upon game over, the gameOver() method initiates an animation using PathTransition and FadeTransition for different stickman elements. Buttons for starting a new game, restarting, and exiting are displayed, each with its corresponding action. The Platform.runLater() method ensures UI updates occur on the JavaFX application thread, maintaining a responsive user interface.
Throughout the code, Thread.sleep() is used strategically to control the timing of movements and animations, preventing the UI from becoming unresponsive. The game's flow is orchestrated by a combination of user input, random element placement, and multithreading, resulting in an engaging and visually dynamic gaming experience.

How we used Multi-threading?

Multithreading is employed in the GameUI class to enhance the responsiveness of the stickman-themed game interface. In the context of mouse input handling, a new thread is created when the mouse is pressed. This thread continuously adjusts the position of the stickman's motion, represented by the line element, based on the vertical movement of the mouse cursor while the left mouse button is held down. The use of a separate thread ensures that the adjustment occurs smoothly and continuously, providing a dynamic interaction between the player's input and the stickman's motion.

The thread created for mouse input utilizes a while loop to monitor the state of the adjuster variable. As long as adjuster is false, the thread continues to modify the line by decreasing its end y-coordinate. This mechanism allows for a seamless adjustment of the stickman's position based on the user's mouse movements, creating a fluid and responsive gameplay experience.

Additionally, multithreading is employed to handle key input events, specifically the Enter key and the Spacebar. When a key is pressed, a new thread is spawned to respond to the key press events. For instance, when the Enter key is pressed, the thread adjusts the line, initiates the game (via the playGame() method), and executes other relevant tasks. Similarly, when the Spacebar is pressed, the thread initiates the inverse movement of the stickman, providing an interactive and varied gameplay experience.

The use of threads in both mouse and key input scenarios allows the game interface to handle these events concurrently with the main JavaFX application thread. This concurrency ensures that user interactions with the stickman's motion, whether through mouse or keyboard input, are processed independently and do not block the main thread. Consequently, the game maintains a smooth and dynamic responsiveness, enhancing the overall gameplay and user experience.
##contributors
kratik gupta 2022252
anikait agrawal 2022072
