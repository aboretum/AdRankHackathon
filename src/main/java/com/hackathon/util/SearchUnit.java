package com.hackathon.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTube.Search;
import com.google.api.services.youtube.model.Video;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.services.youtube.model.ThumbnailDetails;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.api.services.youtube.model.VideoSnippet;




public class SearchUnit {
	private static final String PROPERTIES_FILENAME = "youtube.properties";
	
	private static final long NUMBER_OF_VIDEOS_RETURNED = 5;
	
	private static YouTube youtube;
	
    public static List<Video> getVideos(String queryTerm) {
        // Read the developer key from the properties file.
        Properties properties = new Properties();
        try {
        	
        	
        	InputStream in = SearchUnit.class.getClassLoader().getResourceAsStream("/"+PROPERTIES_FILENAME); 
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }

        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("AdRankHackathon").build();

            // Prompt the user to enter a query term.
            //queryTerm = "Golf";

            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");

            // Set your developer key from the Google Developers Console for
            // non-authenticated requests. See:
            // https://console.developers.google.com/
            String apiKey = properties.getProperty("youtube.apikey");
            search.setKey(apiKey);
            search.setQ(queryTerm);

            // Restrict the search results to only include videos. See:
            // https://developers.google.com/youtube/v3/docs/search/list#type
            search.setType("video");

            // To increase efficiency, only retrieve the fields that the
            // application uses.
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {
                List<Video> videoList = prettyPrint(searchResultList.iterator(), queryTerm, apiKey);
                return videoList;
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        
        return null;
    }
    
    private static List<Video> prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query, String apiKey) {
    	List<Video> videoList = new ArrayList<Video>();
        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();
            try{
            	YouTube.Videos.List video = youtube.videos().list("id, snippet, contentDetails, statistics");
            	video.setKey(apiKey);
            	video.setId(singleVideo.getId().getVideoId());
            	video.setFields("items(snippet/channelTitle, snippet/description, snippet/publishedAt, snippet/categoryId, contentDetails/duration, statistics/viewCount)");
            	VideoListResponse videoResponse = video.execute();
            	List<Video> videoResultList = videoResponse.getItems();
            	if(videoResultList!=null){
            		Video v = videoResultList.get(0);
            		v.setId(rId.getVideoId());
            		
            		System.out.println(v.getContentDetails().getDuration());
            		System.out.println(v.getStatistics().getViewCount());
            		System.out.println(v.getSnippet().getChannelTitle());
            		System.out.println(v.getSnippet().getCategoryId());
            		System.out.println(v.getSnippet().getDescription());
            		System.out.println(v.getSnippet().getPublishedAt());
            		
            		ThumbnailDetails thumbnails = new ThumbnailDetails();
            		thumbnails.setDefault(singleVideo.getSnippet().getThumbnails().getDefault());
				
					v.getSnippet().setThumbnails(thumbnails);
					v.getSnippet().setTitle(singleVideo.getSnippet().getTitle());
					
            		
            		
            		videoList.add(videoResultList.get(0));
            		
            	}
            }catch (GoogleJsonResponseException e) {
                System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                        + e.getDetails().getMessage());
            } catch (IOException e) {
                System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
            } catch (Throwable t) {
                t.printStackTrace();
            }
            
            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
                
                System.out.println(" Video Id" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
        
        return videoList;
    }
   
}
