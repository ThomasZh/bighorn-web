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
public class LogoutActionController
{
	@RequestMapping(value = "/logoutAction", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session == null) {
			System.out.println("session is null");
		} else {
			session.setAttribute("user", null);
		}

		return new ModelAndView("index");
	}

}