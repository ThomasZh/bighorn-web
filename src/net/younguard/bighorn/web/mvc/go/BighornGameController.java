package net.younguard.bighorn.web.mvc.go;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.younguard.bighorn.broadcast.service.GameService;
import net.younguard.bighorn.comm.util.DatetimeUtil;
import net.younguard.bighorn.web.mvc.JSONView;
import net.younguard.bighorn.web.mvc.UserSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class BighornGameController
		extends AbstractController
{
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");

		// the spring mvc framework takes a hashmap as the model..... :| so in
		// order to get the json array to the View, we need to put it in a
		// HashMap
		Map model = new HashMap();

		HttpSession session = request.getSession();
		if (session == null) {
			System.out.println("session is null");
		}

		logger.debug("queryString: " + request.getQueryString());

		String strOper = request.getParameter("oper");
		if (strOper != null && strOper.length() > 0) {
			UserSession user = (UserSession) session.getAttribute("user");
			String accountId = user.getId();
			logger.debug("accountId: " + accountId);

			ServletContext sc = request.getSession().getServletContext();
			ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
			GameService gameService = (GameService) ctx.getBean("broadcastGameService");

			int currentTimestamp = DatetimeUtil.currentTimestamp();

			if (strOper.equals("playStep")) { // oper:playStep
				String gameId = request.getParameter("id");
				logger.debug("gameId: " + gameId);

				String stri = request.getParameter("i");
				short i = Short.parseShort(stri);
				logger.debug("i: " + i);

				String strj = request.getParameter("j");
				short j = Short.parseShort(strj);
				logger.debug("j: " + j);

				short lastStep = gameService.queryLastStep(gameId);
				short color = gameService.queryColor(gameId, accountId);
				gameService.addStep(gameId, accountId, ++lastStep, color, i, j, currentTimestamp);

				model.put("JSON_OBJECT", true);
			}
		}
		return new ModelAndView(new JSONView(), model);
	}

	private GameService gameService;

	public GameService getGameService()
	{
		return gameService;
	}

	public void setGameService(GameService gameService)
	{
		this.gameService = gameService;
	}

	private final static Logger logger = LoggerFactory.getLogger(BighornGameController.class);
}
