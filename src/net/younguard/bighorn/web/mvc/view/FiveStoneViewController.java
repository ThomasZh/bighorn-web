package net.younguard.bighorn.web.mvc.view;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.younguard.bighorn.broadcast.service.GameService;
import net.younguard.bighorn.domain.GameStep;
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

import com.google.gson.Gson;

@Controller
@Scope("session")
public class FiveStoneViewController
{
	@RequestMapping(value = "/5stoneBoard", method = RequestMethod.GET)
	public ModelAndView getPages(HttpServletRequest request)
	{
		String gameId = request.getParameter("id");

		HttpSession session = request.getSession();
		if (session == null)
			System.out.println("session is null");

		ServletContext sc = request.getSession().getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		GameService gameService = (GameService) ctx.getBean("broadcastGameService");

		short lastStep = 0;
		List<GameStep> steps = gameService.queryGameManual(gameId, lastStep);
		Gson gson = new Gson();
		String json = gson.toJson(steps);
		logger.debug("json: " + json);

		String rs = "";
		for (GameStep step : steps) {
			rs += step.getX() + "," + step.getY() + ",";
		}
		logger.debug("array: " + rs);

		ModelAndView model = new ModelAndView("5stone-board");
		model.addObject("gameId", gameId);
		model.addObject("gameManual", rs);

		return model;
	}

	// @Autowired
	public UserSession user;

	private final static Logger logger = LoggerFactory.getLogger(FiveStoneViewController.class);
}
