package net.younguard.bighorn.web.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.younguard.bighorn.web.mvc.UserSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class ChessBoardViewController
{
	@RequestMapping(value = "/chessBoard", method = RequestMethod.GET)
	public ModelAndView getPages(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if (session == null)
			System.out.println("session is null");

		ModelAndView model = new ModelAndView("chess-board");
		model.addObject("playing", "1");
		return model;
	}

	// @Autowired
	public UserSession user;

	private final static Logger logger = LoggerFactory.getLogger(ChessBoardViewController.class);
}
