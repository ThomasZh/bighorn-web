package net.younguard.bighorn.web.mvc.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.younguard.bighorn.GlobalArgs;
import net.younguard.bighorn.broadcast.service.AccountService;
import net.younguard.bighorn.comm.util.EcryptUtil;
import net.younguard.bighorn.domain.AccountBaseInfo;
import net.younguard.bighorn.web.mvc.UserSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class LoginActionController
{
	private String lastPage = "index";

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
			//ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
			ServletContext sc = request.getSession().getServletContext();
			ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
			AccountService accountService = (AccountService) ctx.getBean("broadcastAccountService");

			if (accountService.login(GlobalArgs.LOGIN_TYPE_LOGINNAME, loginName, md5Pwd)) {
				AccountBaseInfo account = accountService.queryAccount(GlobalArgs.LOGIN_TYPE_LOGINNAME, loginName);
				logger.debug("accountId: " + account.getAccountId());
				logger.debug("nickname: " + account.getNickname());
				logger.debug("avatarUrl: " + account.getAvatarUrl());

				UserSession user = new UserSession();
				user.setId(account.getAccountId());
				String nickname = account.getNickname();
				if (nickname != null && nickname.length() > 0) {
					user.setNickname(nickname);
				} else {
					user.setNickname(loginName);
				}
				user.setAvatarUrl(account.getAvatarUrl());
				session.setAttribute("user", user);

				lastPage = (String) session.getAttribute("lastPage");
				return new ModelAndView("redirect:/loginSuccess");
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
		if (lastPage != null && lastPage.length() > 0) {
			System.out.println("lastPage: " + lastPage);
			return lastPage;
		} else {
			return "index";
		}
	}

	@RequestMapping(value = "/loginFailure", method = RequestMethod.GET)
	public String failure()
	{
		return "login";
	}

	// //////////////////////////////////////////////////////

	// @Autowired
	// private AccountService accountService;

	public String getLastPage()
	{
		return lastPage;
	}

	public void setLastPage(String lastPage)
	{
		this.lastPage = lastPage;
	}

	private final static Logger logger = LoggerFactory.getLogger(LoginActionController.class);
}