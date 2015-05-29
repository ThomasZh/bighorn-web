package net.younguard.bighorn.web.mvc.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.younguard.bighorn.broadcast.service.AccountService;
import net.younguard.bighorn.comm.util.DatetimeUtil;
import net.younguard.bighorn.web.ApplicationContextProvider;
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
public class ModifyProfileActionController
{
	private String lastPage = "index";

	@RequestMapping(value = "/modifyProfileAction", method = RequestMethod.GET)
	public ModelAndView modify(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session == null)
			System.out.println("session is null");
		UserSession user = (UserSession) session.getAttribute("user");

		String nickname = request.getParameter("inputNickname");
		logger.debug("nickname: " + nickname);
		String avatarUrl = request.getParameter("hidden_filename");
		if (avatarUrl == null || avatarUrl.length() == 0) {
			avatarUrl = user.getAvatarUrl();
		} else {
			avatarUrl = "http://bighorn.b0.upaiyun.com" + avatarUrl;
		}
		logger.debug("avatarUrl: " + avatarUrl);

		ServletContext sc = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		AccountService accountService = (AccountService) ctx.getBean("broadcastAccountService");

		int currentTimestamp = DatetimeUtil.currentTimestamp();
		accountService.modify(user.getId(), nickname, avatarUrl, currentTimestamp);

		user.setNickname(nickname);
		user.setAvatarUrl(avatarUrl);
		session.setAttribute("user", user);

		lastPage = (String) session.getAttribute("lastPage");
		return new ModelAndView("redirect:/modifyProfileSuccess");
	}

	@RequestMapping(value = "/modifyProfileSuccess", method = RequestMethod.GET)
	public String success()
	{
		if (lastPage != null && lastPage.length() > 0) {
			System.out.println("lastPage: " + lastPage);
			return lastPage;
		} else {
			return "index";
		}
	}

	private final static Logger logger = LoggerFactory.getLogger(ModifyProfileActionController.class);
}
