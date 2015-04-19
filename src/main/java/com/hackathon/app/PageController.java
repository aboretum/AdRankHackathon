package com.hackathon.app;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.hackathon.bean.Video;
import com.hackathon.util.SearchUnit;

/**
 * An mash-up of controller methods that handle requests for different
 * user pages. e.g. user Signup, user profile page, the search results. etc.
 */
@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	/**
	 * Processing the user input for credentials to authenticate the user.
	 */
	@RequestMapping(value = "/Signup", method = RequestMethod.GET)
	public String Signup(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	@RequestMapping(value = "/SearchKeyWord", method = RequestMethod.POST)
	public String searchKeyword(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		String keyWord = request.getParameter("keyword");
		
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		List<SearchResult> searchResultList = SearchUnit.getVideos(keyWord);
		
		List<Video> videoList =new ArrayList<Video>();
		for(SearchResult sr: searchResultList){
			String video_title = sr.getSnippet().getTitle();
			String video_etag = sr.getId().getVideoId();
			System.out.println(video_etag);
			Thumbnail thumbnail = sr.getSnippet().getThumbnails().getDefault();
			Video video = new Video();
			video.setTitle(video_title);
			video.setUrl(video_etag);
			videoList.add(video);
		}
		
		model.addAttribute("videoList", videoList);
		
		return "index";
	}
	
	
}
