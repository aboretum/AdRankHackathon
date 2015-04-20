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

import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.hackathon.bean.AdVideo;
import com.hackathon.bean.Search;
import com.hackathon.bean.User;
import com.hackathon.dao.SearchDao;
import com.hackathon.dao.UserDao;
import com.hackathon.dao.VideoDao;
import com.hackathon.util.SearchUnit;

/**
 * An mash-up of controller methods that handle requests for different
 * user pages. e.g. user Signup, user profile page, the search results. etc.
 */
@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	private UserDao userDao = new UserDao();
	private VideoDao videoDao = new VideoDao();
	private SearchDao searchDao = new SearchDao();
	/**
	 * Processing the user input for credentials to authenticate the user.
	 */
	@RequestMapping(value = "/Signup", method = RequestMethod.GET)
	public String Signup(Locale locale, Model model) {
		
		
		return "sign-up";
	}
	
	@RequestMapping(value = "/Main", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {
		
		
		return "index";
	}
	
	
	
	@RequestMapping(value = "/SearchKeyWord", method = RequestMethod.POST)
	public String searchKeyword(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		String keyWord = request.getParameter("keyword");
		
		User user = new User();
		user.setUserName("Nacho");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		List<Video> searchVideoList = SearchUnit.getVideos(keyWord);
		
		List<AdVideo> videoList =new ArrayList<AdVideo>();
		if(searchVideoList!=null){
			
		
		for(Video vdo: searchVideoList){
			
			String video_title = vdo.getSnippet().getTitle();
			String video_id = vdo.getId();
			
			Thumbnail thumbnail = vdo.getSnippet().getThumbnails().getDefault();
			AdVideo video = new AdVideo();
			video.setTitle(video_title);
			video.setUrl(video_id);
			video.setAuthor(vdo.getSnippet().getChannelTitle());
			video.setTotal_views(vdo.getStatistics().getViewCount().toString());
			Date vdate = new Date(vdo.getSnippet().getPublishedAt().getValue());
		
			video.setPublishDate(vdate);
			video.setThumb_url(thumbnail.getUrl());
			video.setCategory(vdo.getSnippet().getCategoryId());
			video.setDescription(vdo.getSnippet().getDescription());
			videoList.add(video);
			
			//System.out.println(vdate);
			//System.out.println(video.getThumb_url());
		}
		
		}
		
		searchDao.insertRecentSearch(user, videoList);
		searchDao.insertRecentKeyWord(keyWord);
		
		
		model.addAttribute("videoList", videoList);
		
		return "index";
	}
	
	@RequestMapping(value = "/SortBySpecifics", method = RequestMethod.GET)
	public String sortVideoList(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		String orderSpecifics = request.getParameter("orderSpecifics");
		
		User user = new User();
		user.setUserName("Nacho");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
		List<AdVideo> videoList = searchDao.getVideoListBySpecifics(user, orderSpecifics);
		if(videoList!=null){
			
			model.addAttribute("videoList", videoList);

		}
		
		
		return "index";
	}
	
	@RequestMapping(value = "/RecentSearch", method = RequestMethod.GET)
	public String showRecentSearch(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		User user = new User();
		user.setUserName("Nacho");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		List<Search> searchList = searchDao.getSearchListWithFrequency();
		
		
		if(searchList!=null){
			for(Search srch:searchList){
				System.out.println(srch.getSearchFrequency());
			}
			model.addAttribute("searchList", searchList);

		}
		
		return "recent-search";
	}
	
	@RequestMapping(value = "/FrequentUsers", method = RequestMethod.GET)
	public String showFrequentUsers(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		List<User> userList = userDao.getFrequentUsers();
		
		if(userList!=null){
			model.addAttribute("userList", userList);
		}
		return "frequent-users";
	}
	
	
	
	
}
