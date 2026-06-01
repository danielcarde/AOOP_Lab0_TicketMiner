Lab0: TicketMiner Starter

A Java starter version of TicketMiner that reads event data and begins modeling ticketed sport events.

Highlights
Reads EventListPA0.csv.
Converts file data into Java objects.
Introduces a Sport event class.
Logs program activity in ActionLog.txt in the original version.

Overview
Lab0 is the first TicketMiner iteration. It focuses on reading structured event data, creating event objects, and printing or processing the data through a console program.
This lab establishes the file-processing and event-modeling foundation used in later TicketMiner versions.

Why This Project
This project introduces how a larger Java application can start from data parsing and a small set of domain classes. It is the foundation for the later object-oriented TicketMiner system.

Usage
Compile: Compile the Java files with javac *.java.
Run: Execute with java TicketMiner.
Keep EventListPA0.csv in the same folder.

Installation
Ensure you have the Java Development Kit (JDK) installed on your system.
Open a terminal in the lab folder before compiling so the program can find any required local text files.

Architecture
File Structure: The project contains TicketMiner.java, Sport.java, originalFileToArray.java, and EventListPA0.csv.
Components: TicketMiner.java is the main driver, Sport.java models sport events, and originalFileToArray.java handles file-to-array conversion.

Future Improvements
Generalize parsing for more event types.
Improve validation for CSV data.
Separate logging from the main program flow.
