package net.younguard.bighorn.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.younguard.bighorn.GlobalArgs;
import net.younguard.bighorn.broadcast.service.AccountService;
import net.younguard.bighorn.comm.util.EcryptUtil;
import net.younguard.bighorn.domain.AccountBaseInfo;
import net.younguard.bighorn.web.ApplicationContextProvider;

import org.springframework.context.ApplicationContext;
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

		String loginName = request.getParameter("inputLoginName");
		System.out.println("loginName:" + loginName);
		String password = request.getParameter("inputPassword");
		String md5Pwd = EcryptUtil.md5(password);
		// System.out.println(password);

		if (loginName != null && password != null) {
			ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
			AccountService accountService = (AccountService) ctx.getBean("broadcastAccountService");

			if (accountService.login(GlobalArgs.LOGIN_TYPE_LOGINNAME, loginName, md5Pwd)) {
				AccountBaseInfo account = accountService.queryAccount(GlobalArgs.LOGIN_TYPE_LOGINNAME, loginName);
				System.out.println("accountId: " + account.getAccountId());
				
				UserSession user = new UserSession();
				user.setId(account.getAccountId());
				user.setNickname(loginName);
				session.setAttribute("user", user);

				String lastPage = (String) session.getAttribute("lastPage");
				if (lastPage != null && lastPage.length() > 0) {
					return new ModelAndView(lastPage);
				} else {
					return new ModelAndView("redirect:/loginSuccess");
				}
			} else {
				return new ModelAndView("login", "message", "Wrong login name or password!");
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