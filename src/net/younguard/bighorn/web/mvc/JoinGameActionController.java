package net.younguard.bighorn.web.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.younguard.bighorn.GlobalArgs;
import net.younguard.bighorn.broadcast.service.GameService;
import net.younguard.bighorn.comm.util.DatetimeUtil;
import net.younguard.bighorn.domain.GameMemberMasterInfo;
import net.younguard.bighorn.web.ApplicationContextProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class JoinGameActionController
{
	@RequestMapping(value = "/joinGameAction", method = RequestMethod.GET)
	public ModelAndView join(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session == null) {
			System.out.println("session is null");
		} else {
			UserSession user = (UserSession) session.getAttribute("user");
			String accountId = user.getId();
			System.out.println("accountId: " + accountId);

			String id = request.getParameter("id");
			logger.debug("gameId:" + id);

			ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
			GameService gameService = (GameService) ctx.getBean("broadcastGameService");

			short color = GlobalArgs.PLAYER_COLOR_BLACK;
			List<GameMemberMasterInfo> members = gameService.queryGameMembers(id);
			for (GameMemberMasterInfo member : members) {
				short opponentColor = member.getColor();
				if (opponentColor == GlobalArgs.PLAYER_COLOR_BLACK)
					color = GlobalArgs.PLAYER_COLOR_RED;
				else
					color = GlobalArgs.PLAYER_COLOR_BLACK;
			}
			int currentTimestamp = DatetimeUtil.currentTimestamp();
			gameService.join(id, accountId, color, currentTimestamp);

			gameService.modifyGameState(id, GlobalArgs.GAME_STATE_PLAYING, currentTimestamp);

			return new ModelAndView("redirect:/joinGameSuccess");
		}

		return null;
	}

	@RequestMapping(value = "/joinGameSuccess", method = RequestMethod.GET)
	public String success()
	{
		return "playing";
	}

	private final static Logger logger = LoggerFactory.getLogger(JoinGameActionController.class);
}
