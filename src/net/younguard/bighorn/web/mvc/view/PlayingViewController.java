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
import net.younguard.bighorn.web.domain.GamePlayingInfo;
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
public class PlayingViewController
{
	@RequestMapping(value = "/playing", method = RequestMethod.GET)
	public ModelAndView getPages(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session == null)
			System.out.println("session is null");

		ModelAndView model = new ModelAndView("playing");

		user = (UserSession) session.getAttribute("user");
		if (user == null) {
			System.out.println("user is null");

			model = new ModelAndView("login");
			return model;
		} else {
			String myAccountId = user.getId();
			System.out.println("userId: " + user.getId());
			System.out.println("nickname: " + user.getNickname());

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

			Page<GameMasterInfo> pages = gameService.queryPlayingPagination(pageNum, pageSize);
			List<GameMasterInfo> games = pages.getPageItems();
			model.addObject("pageNumber", pages.getPageNumber());
			model.addObject("pagesAvailable", pages.getPagesAvailable());
			logger.debug("pageNumber: " + pages.getPageNumber());
			logger.debug("pagesAvailable: " + pages.getPagesAvailable());

			List<GamePlayingInfo> playings = new ArrayList<GamePlayingInfo>();

			for (GameMasterInfo game : games) {
				GamePlayingInfo playing = new GamePlayingInfo();
				playing.setId(game.getGameId());
				playing.setTimeRule(game.getTimeRule());
				logger.debug("timeRule: " + playing.getTimeRule());
				playing.setStep(game.getLastStep());

				List<GameMemberMasterInfo> players = gameService.queryGameMembers(game.getGameId());
				if (players != null)
					for (GameMemberMasterInfo player : players) {
						AccountBaseInfo account = accountService.query(player.getAccountId());

						if (player.getColor() == GlobalArgs.PLAYER_COLOR_BLACK) {
							playing.setBlackPlayerName(account.getNickname());
							playing.setBlackPlayerAvatarUrl(account.getAvatarUrl());

							if (game.getLastStep() % 2 == 0) {
								playing.setIsBlackThinking(GlobalArgs.TRUE);
							}
							if (myAccountId.equals(player.getAccountId())) {

							}
						} else {
							playing.setWhitePlayerName(account.getNickname());
							playing.setWhitePlayerAvatarUrl(account.getAvatarUrl());
							
							if (game.getLastStep() % 2 != 0) {
								playing.setIsWhiteThinking(GlobalArgs.TRUE);
							}
							if (myAccountId.equals(player.getAccountId())) {

							}
						}
					}

				playings.add(playing);
			}

			model.addObject("playings", playings);
		}

		return model;
	}

	// @Autowired
	public UserSession user;

	private final static Logger logger = LoggerFactory.getLogger(PlayingViewController.class);
}
