Welcome to our CS215 Final Project! We were tasked with using the ideas of Brute Force, Transform and Conquer, and Greedy Technique to tackle data compression problems.

To do so, we created a JavaFX application with a basic UI to make it easy to use. Here is a screenshot of the UI:

![Alt text](screenshot.png?raw=true "Screenshot of User Interface")

 - The input box is where you input text to be operated on. The text inside will either be compressed or decompressed depending upon your selection.
 - The compress button runs the appropriate compression method on whatever is in the input box.
 - The dropdown menu allows you to choose the algorithm you wish to compress/decompress with. It defaults to Brute Force (Run-Length Encoding). The other options are Transform and Conquer (Burrows-Wheeler Transform) and Greedy Technique (Lipman-Ziv-Welch).
 - The decompress button runs the appropriate decompression method on whatever is in the input box.
 - The output box shows the output of whatever method and operation was chosen.
 - The text at the bottom dynamically updates with the amount of time the last operation took and the compression ratio, which works for both compression and decompression.

Please note that to test that the compressed text decompresses back to the original text, it must be copy-pasted from the output box to the input box. The UI is also scalable, so it can maximized if you want to see more of your text at one time.

GitHub Link: https://github.com/asdarms/CS215Project
