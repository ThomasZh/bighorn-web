package net.younguard.bighorn.web.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class GaDeviceController
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

		String strOper = request.getParameter("oper");
		if (strOper != null && strOper.length() > 0) {
			if (strOper.equals("add")) { // oper:add
			} else if (strOper.equals("edit")) { // oper:edit
			} else if (strOper.equals("del")) { // oper:del
			} else if (strOper.equals("query")) { // oper:query
				String deviceId = request.getParameter("deviceId");
				if (deviceId != null && deviceId.length() > 0) {
					Device device = deviceDao.query(deviceId);

					JSONObject jsonObject = JSONObject.fromObject(device);
					String json = jsonObject.toString();
					logger.debug("json: " + json);
					model.put("JSON_OBJECT", json);
				}
			}
		} else { // no oper
		}

		return new ModelAndView(new JSONView(), model);
	}

	GaDeviceDao deviceDao;
	private final static Logger logger = LoggerFactory.getLogger(GaDeviceController.class);
}
