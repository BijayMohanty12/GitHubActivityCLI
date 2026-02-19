GitHub Activity CLI

A simple Command Line Interface (CLI) application built with Java 21 that fetches and displays a GitHub user's recent public activity using the GitHub REST API.

*************************************************
      ***Fetches recent public events of any GitHub user**

-Displays human-readable activity output

-Handles different event types:

-Push events

-Issue creation

-Repository stars

-Built using Java 21 HttpClient

-Parses JSON using Jackson

-Accepts username as CLI argument

      *****Tech Stack****

-Java 21

-Maven

-Java HttpClient (java.net.http)

-Jackson Databind (for JSON parsing)

      ***How It Works***

-Takes GitHub username as a command-line argument.

-Builds the GitHub API URL:

-https://api.github.com/users/{username}/events

-Sends HTTP GET request using Java HttpClient.

-Parses JSON response using Jackson.

-Extracts event type and repository details.

-Prints formatted output to the terminal.

      ***Error Handling

-Handles invalid username (404 response)

-Prevents crash when no CLI argument is provided

-Safely checks JSON fields before accessing them

***Project Structure

src/
main/
      java/
            org/example/
                App.java
pom.xml

      *****What I Learned***

-How HTTP requests work in Java

-How to consume REST APIs

-JSON parsing and tree navigation

-CLI argument handling

-Maven project structure

-Debugging build and runtime issues
