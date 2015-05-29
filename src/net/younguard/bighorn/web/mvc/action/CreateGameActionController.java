package net.younguard.bighorn.web.mvc.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.younguard.bighorn.GlobalArgs;
import net.younguard.bighorn.broadcast.service.AccountService;
import net.younguard.bighorn.broadcast.service.GameService;
import net.younguard.bighorn.comm.util.DatetimeUtil;
import net.younguard.bighorn.web.ApplicationContextProvider;
import net.younguard.bighorn.web.mvc.UserSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class CreateGameActionController
{
	@RequestMapping(value = "/createGameAction", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session == null) {
			System.out.println("session is null");
		} else {
			UserSession user = (UserSession) session.getAttribute("user");
			String accountId = user.getId();
			System.out.println("accountId: " + accountId);

			ServletContext sc = request.getSession().getServletContext();
			ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
			GameService gameService = (GameService) ctx.getBean("broadcastGameService");

			String radioButton = request.getParameter("radiobutton");
			System.out.println("radioButton:" + radioButton);
			if (radioButton != null && radioButton.length() > 0) {
				short color = GlobalArgs.PLAYER_COLOR_BLACK;
				short timeRule = GlobalArgs.GAME_TIME_RULE_1DAY_25STEPS;
				int currentTimestamp = DatetimeUtil.currentTimestamp();

				if (radioButton.equals("blue")) {
					color = GlobalArgs.PLAYER_COLOR_BLACK;
				} else {
					color = GlobalArgs.PLAYER_COLOR_RED;
				}
				gameService.create(accountId, color, timeRule, currentTimestamp);
				return new ModelAndView("invite", "message", "must be choose a color");
			} else {
				return new ModelAndView("redirect:/createGameSuccess");
			}
		}

		return null;
	}

	@RequestMapping(value = "/createGameSuccess", method = RequestMethod.GET)
	public String success()
	{
		return "invite";
	}

}
