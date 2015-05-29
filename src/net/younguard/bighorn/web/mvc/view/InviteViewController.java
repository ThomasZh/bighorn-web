package net.younguard.bighorn.web.mvc.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.younguard.bighorn.GlobalArgs;
import net.younguard.bighorn.broadcast.domain.Page;
import net.younguard.bighorn.broadcast.service.AccountService;
import net.younguard.bighorn.broadcast.service.GameService;
import net.younguard.bighorn.domain.AccountBaseInfo;
import net.younguard.bighorn.domain.GameMasterInfo;
import net.younguard.bighorn.domain.GameMemberMasterInfo;
import net.younguard.bighorn.web.ApplicationContextProvider;
import net.younguard.bighorn.web.domain.GameInviteInfo;
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
public class InviteViewController
{
	@RequestMapping(value = "/invite", method = RequestMethod.GET)
	public ModelAndView getPages(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session == null)
			logger.warn("session is null");
		UserSession user = (UserSession) session.getAttribute("user");

		session.setAttribute("lastPage", "invite");
		ModelAndView model = new ModelAndView("invite");

		ServletContext sc = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		GameService gameService = (GameService) ctx.getBean("broadcastGameService");
		AccountService accountService = (AccountService) ctx.getBean("broadcastAccountService");

		String strPageNumber = request.getParameter("pageNumber");
		logger.debug("pageNumber:" + strPageNumber);

		short pageNum = 1;
		if (strPageNumber != null) {
			pageNum = Short.parseShort(strPageNumber);
			if (pageNum == 0)
				pageNum = 1;
			logger.debug("pageNum:" + pageNum);
		}
		short pageSize = 10;

		Page<GameMasterInfo> pages = gameService.queryInvitePagination(pageNum, pageSize);
		List<GameMasterInfo> games = pages.getPageItems();
		model.addObject("pageNumber", pages.getPageNumber());
		model.addObject("pagesAvailable", pages.getPagesAvailable());
		logger.debug("pageNumber: " + pages.getPageNumber());
		logger.debug("pagesAvailable: " + pages.getPagesAvailable());

		List<GameInviteInfo> invites = new ArrayList<GameInviteInfo>();

		for (GameMasterInfo game : games) {
			GameInviteInfo invite = new GameInviteInfo();
			invite.setId(game.getGameId());
			invite.setTimeRule(game.getTimeRule());
			logger.debug("gameId: " + invite.getId());
			logger.debug("timeRule: " + invite.getTimeRule());

			List<GameMemberMasterInfo> players = gameService.queryGameMembers(game.getGameId());
			if (players != null)
				for (GameMemberMasterInfo player : players) {
					invite.setPlayerColor(player.getColor());

					AccountBaseInfo account = accountService.query(player.getAccountId());
					invite.setPlayerName(account.getNickname());
					invite.setPlayerAvatarUrl(account.getAvatarUrl());

					logger.debug("playerColor: " + invite.getPlayerColor());
					logger.debug("playerName: " + invite.getPlayerName());
					logger.debug("playerAvatarUrl: " + invite.getPlayerAvatarUrl());

					if (user != null) {
						if (player.getAccountId().equals(user.getId())) {
							invite.setIsMe(GlobalArgs.TRUE);
							logger.debug("player is me: " + invite.getIsMe());
						}
					}
				}

			invites.add(invite);
		}

		model.addObject("invites", invites);

		return model;
	}
	
	private final static Logger logger = LoggerFactory.getLogger(InviteViewController.class);
}
