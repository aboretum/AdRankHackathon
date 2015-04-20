package com.hackathon.app;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hackathon.bean.User;
import com.hackathon.dao.UserDao;
import com.hackathon.util.MessageDigestService;

/**
 * Handles requests for the user login from sign-in page.
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	UserDao userDao = new UserDao();
	
	/**
	 * Processing the user input for credentials to authenticate the user.
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpServletRequest request) throws NoSuchAlgorithmException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String loginEmail = request.getParameter("email");
		String passWord = request.getParameter("password");
		User user = userDao.getUserByEmail(loginEmail);
		
		if(user.getPassWord().equals(MessageDigestService.getDigest(passWord))){
			userDao.updateUserFrequency(user);
			model.addAttribute("user", user);
			
			return "index";
		}
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
