# Melody Matcher

Melody Matcher is a web game that tests your ability to playback randomly generated melodies using a virtual keyboard.
Correctly replicating melodies will earn you points; the harder the melody, the more points you get!
However, you only have a certain amount of time to complete each melody, or else the game is over.
The timer is reset after every completed melody.
Melody Matcher can help players better distinguish between different tones and sounds, it doesn't matter if you're a casual music listener or an experienced musician!

![demo](/src/images/demo.png)

[Play the game now!](http://miaoxiao.github.io/Melody-Matcher/)

## Choosing a Difficulty
In the 'Settings' tab, the player can change their scale and key, as well as their difficulty.
These settings will take effect upon the next 'Restart'.
The chosen difficulty affects how much time you get per melody, the number of notes in your first melody, and your score multiplier.
Note that on Easy difficulty, the first note of every melody will be highlighted in blue for you.

![settings](/src/images/settings.png)

* Easy - 60 seconds - 2 notes -1.0 multiplier
* Medium - 40 seconds - 3 notes - 1.5 multiplier
* Hard - 20 seconds - 4 notes - 2.0 multiplier

##Points
Points are awarded by adding up a base score, any bonus scores, and then scaling it with a multiplier. The base score is calculated as follows:

<i>Base Score = (numNotes * 25) + (range * 10)<i>

Bonus points are awarded by satisfying certain requirements.
The calculated score for each bonus is also given.
On Easy difficulty, the player cannot obtain the streak bonus.

* Clicking 'Play Melody' once or less<br><i>(numNotes * 10)<i>
* Completing the melody without any errors<br><i>(numNotes * 15 + range * 15)<i>
* Completing the melody in less than 10 seconds <br><i>(numNotes * 10)<i>
* Completing a melody with at least one flat <br><i>(numFlats * 15)<i>
* Getting the No Error bonus consecutively. The higher the streak, the more points you get for each consecutive melody, up to 500 bonus points.<br><i>(streakCounter * 100)<i>

##Levels
Every time the player succesfully completes a melody, the player moves on to the next level.
The game is endless, so there is no 'end level'!
Instead, the game gets more and more difficult as the player progresses.
At specific level intervals, the difficulty of a randomly generated melody will increase.

* Every 5 levels, the range of possible notes increase, up to the range of the entire keyboard.
* Every 10 levels, the number of notes in the melody increases by one.
* Every 10 levels, the speed of the melody when playing back becomes slightly faster, up to a cap.

## Libraries and Other Info
Melody Matcher uses [SoundJS](http://www.createjs.com/SoundJS) for managing and playing back sounds.
Melodies are generated by filling an array with random note IDs from a given scale array, and then referring to those IDs when playing the corresponding note.
The notes are then played in sequence by traversing the array.
The algorithm also takes into account the number of notes in the melody, the possible range of the melody, and the speed of the melody.
Everything is written in Javascript, HTML, and CSS.