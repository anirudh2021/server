This is a repo for Maven-based Java application that recieves request with json object as message/request body and save the message to a new file. 
This application can accept and handle multiple request simultaneously

You require maven and java installed in your local machine to build and run this application

This application requires path of java properties file as input. Below are instructions to setup the file
1. Create a new file in your local machine
2. Open the file in a text editor
3. Add key-value pairs to it. Each key-value pair should be on a new line and separated by an equal sign (=). For example: property.name=property.value
4. Save the file
5. Note complete path to the file as this required for starting application

Below are the required properties for this application with example values, make sure to follow same format
Use "output.file.directory.path" to set output directory to save the messages
Use "output.file.name.extention" to set extentions for the newly created files
Use "output.file.delimiter" to set delimiter between key value pairs of json object
Use "server.port" to start server at specific port

Below is the example of properties file

#input application properties
output.file.directory.path=C:\\Users\\aniru\\serverOutputDir
output.file.name.extention=.properties
output.file.delimiter=\=
server.port=8091



To build and run this application follow the below steps
1. Pull this repository to your local machine
2. Open terminal/command prompt and change path to the recently downloaded repo folder
3. Run following command to build the application
	mvn package
4. Run following command to execute the application
	java -jar target\server-1.0-SNAPSHOT-jar-with-dependencies.jar path-to-application-properties-folder