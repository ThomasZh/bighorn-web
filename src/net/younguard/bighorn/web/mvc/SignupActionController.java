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
public class SignupActionController
{
	@RequestMapping(value = "/signupAction", method = RequestMethod.GET)
	public ModelAndView signup(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session == null)
			System.out.println("session is null");

		String loginame = request.getParameter("inputLoginame");
		System.out.println("loginame: " + loginame);
		String password = request.getParameter("inputPassword");
		// System.out.println(password);

		if (loginame != null && password != null) {
			if (! loginame.equals("a")) {
				UserSession user = new UserSession();
				user.setId("id");
				user.setNickname("Jerry");
				session.setAttribute("user", user);

				return new ModelAndView("redirect:/signupSuccess");
			} else {
				return new ModelAndView("signup", "message", "This login name already exist!");
			}
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/signupSuccess", method = RequestMethod.GET)
	public String success()
	{
		return "playing";
	}

	@RequestMapping(value = "/signupFailure", method = RequestMethod.GET)
	public String failure()
	{
		return "signup";
	}
}
