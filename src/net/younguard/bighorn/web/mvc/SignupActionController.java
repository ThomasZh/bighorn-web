package net.younguard.bighorn.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.younguard.bighorn.GlobalArgs;
import net.younguard.bighorn.broadcast.service.AccountService;
import net.younguard.bighorn.comm.util.DatetimeUtil;
import net.younguard.bighorn.comm.util.EcryptUtil;
import net.younguard.bighorn.web.ApplicationContextProvider;

import org.springframework.context.ApplicationContext;
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

		String loginName = request.getParameter("inputLoginName");
		System.out.println("loginName: " + loginName);
		String password = request.getParameter("inputPassword");
		// System.out.println(password);

		if (loginName != null && password != null) {
			ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
			AccountService accountService = (AccountService) ctx.getBean("broadcastAccountService");

			if (accountService.isExistLogin(GlobalArgs.LOGIN_TYPE_LOGINNAME, loginName)) {
				return new ModelAndView("signup", "message", "This login name already exist!");
			} else {
				String md5Pwd = EcryptUtil.md5(password);
				int currentTimestamp = DatetimeUtil.currentTimestamp();

				String accountId = accountService.createLogin(GlobalArgs.LOGIN_TYPE_LOGINNAME, loginName, md5Pwd,
						currentTimestamp);
				UserSession user = new UserSession();
				user.setId(accountId);
				user.setNickname(loginName);
				session.setAttribute("user", user);

				String avatarUrl = null;
				accountService.create(accountId, loginName, avatarUrl, currentTimestamp);

				return new ModelAndView("redirect:/signupSuccess");
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
