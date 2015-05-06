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
public class CreateGameViewController
{
	@RequestMapping(value = "/create-game", method = RequestMethod.GET)
	public ModelAndView getPages(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session == null)
			System.out.println("session is null");

		session.setAttribute("lastPage", "create-game");

		UserSession user = (UserSession) session.getAttribute("user");
		if (user == null) {
			ModelAndView model = new ModelAndView("login");
			return model;
		} else {
			ModelAndView model = new ModelAndView("create-game");
			return model;
		}
	}
}
