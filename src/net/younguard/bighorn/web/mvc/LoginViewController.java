package net.younguard.bighorn.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.younguard.bighorn.web.ApplicationContextProvider;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginViewController
{
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request)
	{
		String loginname = request.getParameter("inputEmail");
		System.out.println("loginname:" + loginname);
		String password = request.getParameter("inputPassword");
		// System.out.println(password);

		if (loginname != null && password != null) {
			ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
			GaAccountService accountService = (GaAccountService) ctx.getBean("gaAccountService");

			if (accountService.login(loginname, EcryptUtil.md5(password))) {
				HttpSession session = request.getSession();
				UserSession user = new UserSession();
				user.setId("id");
				user.setUsername(loginname);
				session.setAttribute("user", user);

				return new ModelAndView("redirect:/loginSuccess");
			} else {
				return new ModelAndView("login", "message", "Wrong username or pasword!");
			}
			// boolean rs = false;
			// if (loginname.equals("a@b.com") && password.equals("a")) {
			// HttpSession session = request.getSession();
			// UserSession user = new UserSession();
			// user.setId("id");
			// user.setUsername(loginname);
			// session.setAttribute("user", user);
			// rs = true;
			// }
			//
			// if (rs) {
			// return new ModelAndView("redirect:/loginSuccess");
			// } else {
			// return new ModelAndView("login", "message",
			// "Wrong username or pasword!");
			// }
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
	public String success()
	{
		return "admin-summary";
	}

	@RequestMapping(value = "/loginFailure", method = RequestMethod.GET)
	public String failure()
	{
		return "login";
	}

	ApplicationContext applicationContext = null;
}
