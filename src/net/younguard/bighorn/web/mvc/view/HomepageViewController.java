package net.younguard.bighorn.web.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.younguard.bighorn.web.mvc.UserSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class HomepageViewController
{
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView getPages(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session == null)
			System.out.println("session is null");
		session.setAttribute("lastPage", "index");

		user = (UserSession) session.getAttribute("user");
		if (user == null) {
			System.out.println("user is null");
			
//			user = new UserSession();
//			user.setId("id");
//			user.setNickname("Tom");
//			session.setAttribute("user", user);
		} else {
			System.out.println("userId: " + user.getId());
			System.out.println("nickname: " + user.getNickname());
		}

		ModelAndView model = new ModelAndView("index");
		return model;
	}

	// @Autowired
	public UserSession user;

}
