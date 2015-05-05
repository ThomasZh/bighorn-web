package net.younguard.bighorn.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class PlayingViewController
{
	@RequestMapping(value = "/playing", method = RequestMethod.GET)
	public ModelAndView getPages(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session == null)
			System.out.println("session is null");

		user = (UserSession) session.getAttribute("user");
		if (user == null) {
			System.out.println("user is null");
			
			ModelAndView model = new ModelAndView("login");
			return model;
		} else {
			System.out.println("userId: " + user.getId());
			System.out.println("nickname: " + user.getNickname());
		}

		ModelAndView model = new ModelAndView("playing");
		return model;
	}

	// @Autowired
	public UserSession user;
}
