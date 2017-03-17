# Very important: the lines containing commands must start with
# a tab and not spaces!
# Also note that the pound symbol indicates the start of a comment
# which is usually helpful text used to annotate the makefile code
# This command will compile the source code when you run
# the make compile command at the command line prompt

compile:
	javac CircleButton.java Main.java Notebook.java NumberFactory.java Scientist.java Stopwatch.java Trial.java

# This command will run the Hello World Program
run:
	java Main
#This command will “clean” out the directory of any output files
clean:
	rm *.class
