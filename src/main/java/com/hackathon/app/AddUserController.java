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

@Controller
public class AddUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private UserDao userDao = new UserDao();
	
	/**
	 * Processing the user input for credentials to authenticate the user.
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping(value = "/AddUser", method = RequestMethod.POST)
	public String addUser(Locale locale, Model model, HttpServletRequest request) throws NoSuchAlgorithmException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		String email = request.getParameter("email");
		User user = new User();
		User dbUser = null;
		
		dbUser = userDao.getUserByName(userName);
		
		if(dbUser==null){
			user.setUserName(userName);
			user.setPassWord(passWord);
			user.setUserEmail(email);
			userDao.insertNewUser(user);
		}
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
		return "home";
	}
	
}