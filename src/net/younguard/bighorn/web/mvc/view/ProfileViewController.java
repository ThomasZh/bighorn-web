package net.younguard.bighorn.web.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.younguard.bighorn.web.mvc.UserSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class ProfileViewController
{
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView getPages(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session == null)
			logger.warn("session is null");
		UserSession user = (UserSession) session.getAttribute("user");

		ModelAndView model = new ModelAndView("profile");

		model.addObject("nickname", user.getNickname());
		model.addObject("avatarUrl", user.getAvatarUrl());

		return model;
	}

	private final static Logger logger = LoggerFactory.getLogger(ProfileViewController.class);
}
