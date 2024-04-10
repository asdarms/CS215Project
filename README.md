Welcome to our CS215 Final Project! We were tasked with using the ideas of Brute Force, Transform and Conquer, and Greedy Technique to tackle data compression problems.

To do so, we created a JavaFX application with a basic UI to make it easy to use. Here is a screenshot of the UI:

![Alt text](screenshot.png?raw=true "Screenshot of User Interface")

1. The input box is where you input text to be operated on. The text inside will either be compressed or decompressed depending upon your selection.
2. The compress button runs the appropriate compression method on whatever is in the input box.
3. The dropdown menu allows you to choose the algorithm you wish to compress/decompress with. It defaults to Brute Force (Run-Length Encoding). The other options are Transform and Conquer (Burrows-Wheeler Transform) and Greedy Technique (Lipman-Ziv-Welch).
4. The decompress button runs the appropriate decompression method on whatever is in the input box.
5. The output box shows the output of whatever method and operation was chosen.
6. The text at the bottom dynamically updates with the amount of time the last operation took and the compression ratio, which works for both compression and decompression.

Some additional usage notes:
- Please note that to test that the compressed text decompresses back to the original text, it must be copy-pasted from the output box to the input box. 
- Also, you cannot mix compression methods! Both compression and decompression must be done using the same method to get correct results.
- The UI is also scalable, so it can be maximized if you want to see more of your text at one time.
- Decompressing using Transform and Conquer, or the Burrows-Wheeler Transform, may take long amounts of time with large numbers of characters (thousands). With inputs greater than 10,000 characters, the program may run out of memory and crash.
- Brute Force may not yield good results with small inputs as repeated characters are necessary for it to work. The Transform and Conquer helps with this, but is still not always smaller. The Greedy Technique will always yield a smaller output.

GitHub Link: https://github.com/asdarms/CS215Project
