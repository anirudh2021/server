This is a repo for a Maven-based Java application that receives requests with JSON object as a message/request body and saves the message to a new file. 
This application can accept and handle multiple requests simultaneously

You require Maven and java installed on your local machine to build and run this application

This application requires the path of the Java properties file as input. Below are instructions to set the file
1. Create a new file in your local machine
2. Open the file in a text editor
3. Add key-value pairs to it. Each key-value pair should be on a new line and separated by an equal sign (=). For example: property.name=property.value
4. Save the file
5. Note the complete path to the file as this is required for starting the application

Below are the required properties for this application with example values, make sure to follow same format
Use "output.file.directory.path" to set the output directory to save the messages
Use "output.file.name.extention" to set extensions for the newly created files
Use "output.file.delimiter" to set delimiter between key-value pairs of JSON object
Use "server.port" to start server at a specific port

Below is the example of the properties file

#input application properties
output.file.directory.path=C:\\Users\\aniru\\serverOutputDir
output.file.name.extention=.properties
output.file.delimiter=\=
server.port=8091



To build and run this application follow the below steps
1. Pull this repository to your local machine
2. Open the terminal/command prompt and change the path to the recently downloaded repo folder
3. Run the following command to build the application
	mvn package
4. Run the following command to execute the application
	java -jar target\server-1.0-SNAPSHOT-jar-with-dependencies.jar path-to-application-properties-folder
