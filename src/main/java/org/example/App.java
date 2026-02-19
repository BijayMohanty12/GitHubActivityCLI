package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if(args.length==0)
        {

            System.out.println("Usage: github-activity <username>");
            System.out.println("-------------------");
            System.out.println("Example: github-activity kamran");
            return;
        }
        String userName=args[0];
        String baseURL="https://api.github.com/users/";
        String suffix="/events";
        String url =baseURL+userName+suffix;
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest= HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> httpResponse= httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper= new ObjectMapper();
           JsonNode root= objectMapper.readTree(httpResponse.body());
           for(JsonNode event:root) {
               String eventType = event.get("type").asText();

               String  repoName= event.get("repo").get("name").asText();
               switch (eventType)
               {
                   case "IssueCommentEvent":
                       String action1=event.get("payload").get("action").asText();
                       if(action1.equals("created"))
                       {
                           System.out.println("- Created  a new issue in " + repoName);

                       }
                       break;
                   case "PushEvent":

                       JsonNode payload = event.get("payload");
                       JsonNode commits = payload.get("commits");

                       if (commits != null) {
                           int commitCount = commits.size();
                           System.out.println("- Pushed " + commitCount +
                                   " commits to " + repoName);

                       } else {
                           System.out.println("- Pushed commits to " + repoName);

                       }
                       break;

                   case "DeleteEvent":
                       String ref= event.get("payload").get("ref").asText();
                       System.out.println("-Deleted  "+ref);

                       break;
                   case "PullRequestEvent":
                      String action2= event.get("payload").get("action").asText();
                      if(action2.equals("merged"))
                      {
                          System.out.println("-Merged request in "+repoName);

                      }
                      break;
                   case "CreateEvent":
                       System.out.println("-New event is created in "+repoName);

                       break;
                   case "IssuesEvent":
                       System.out.println("-new Issues has been created  in"+repoName);

                       break;
                   case "WatchEvent":
                       System.out.println("-WatchEvent has been created in"+repoName);
                   default:
                       break;
               }
            }



            httpClient.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
