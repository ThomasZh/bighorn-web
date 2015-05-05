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
public class LoginActionController
{
	@RequestMapping(value = "/loginAction", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session == null)
			System.out.println("session is null");

		String loginame = request.getParameter("inputLoginame");
		System.out.println("loginame:" + loginame);
		String password = request.getParameter("inputPassword");
		// System.out.println(password);

		if (loginame != null && password != null) {
			if (loginame.equals("a") && password.equals("a")) {
				UserSession user = new UserSession();
				user.setId("id");
				user.setNickname("Tom");
				session.setAttribute("user", user);

				return new ModelAndView("redirect:/loginSuccess");
			} else {
				return new ModelAndView("login", "message", "Wrong loginame or password!");
			}
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
	public String success()
	{
		return "playing";
	}

	@RequestMapping(value = "/loginFailure", method = RequestMethod.GET)
	public String failure()
	{
		return "login";
	}

}