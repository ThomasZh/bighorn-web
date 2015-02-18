package net.younguard.bighorn.web.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.younguard.bighorn.comm.util.DatetimeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


public class GaAccountController
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
		int timestamp = DatetimeUtil.currentTimestamp();

		String strOper = request.getParameter("oper");
		if (strOper != null && strOper.length() > 0) {
			logger.debug(strOper);

			if (strOper.equals("add")) { // oper:add
			} else if (strOper.equals("edit")) { // oper:edit
				String account_id = request.getParameter("id");
				logger.debug("account_id: " + account_id);

				String login_name = request.getParameter("login_name");
				logger.debug("login_name: " + login_name);

				String strState = request.getParameter("state");
				logger.debug("state: " + strState);
				if (strState != null) {
					short state = Short.parseShort(strState);
					accountDao.updateState(account_id, state, timestamp);
				}

				String account_name = request.getParameter("account_name");
				logger.debug("account_name: " + account_name);
				if (account_name != null) {
					accountDao.updateName(account_id, account_name, timestamp);
				}

				String account_desc = request.getParameter("account_desc");
				logger.debug("account_desc: " + account_desc);
				if (account_desc != null) {
					accountDao.updateDesc(account_id, account_desc, timestamp);
				}

				String avatar_url = request.getParameter("avatar_url");
				logger.debug("avatar_url: " + avatar_url);
				if (avatar_url != null) {
					accountDao.updateAvatarUrl(account_id, avatar_url, timestamp);
				}
			} else if (strOper.equals("del")) { // oper:del
				String account_id = request.getParameter("id");
				logger.debug("account_id: " + account_id);

				try {
					accountDao.remove(account_id);
					model.put("JSON_OBJECT", true);
				} catch (Exception e) {
					model.put("JSON_OBJECT", false);
				}
			}
		} else { // no oper
			List<Criterion> criteria = Collections.emptyList();
			criteria = new ArrayList<Criterion>();

			String strPage = request.getParameter("page");
			int page = Integer.parseInt(strPage);
			logger.debug("page: " + page);

			String strRows = request.getParameter("rows");
			int rows = Integer.parseInt(strRows);
			logger.debug("rows: " + rows);

			String sidx = request.getParameter("sidx"); // sidx:amount
			logger.debug("sidx: " + sidx);
			String sord = request.getParameter("sord"); // sord:asc,sord:desc
			logger.debug("sord: " + sord);

			String strSearch = request.getParameter("_search");
			boolean search = Boolean.parseBoolean(strSearch);
			logger.debug("search: " + search);
			if (search) {// _search:true
				// filters:{"groupOp":"AND","rules":[{"field":"id","op":"ne","data":"5"},{"field":"amount","op":"gt","data":"100"}]}
				String filters = request.getParameter("filters");
				if (filters != null && filters.length() > 0) {
					logger.debug("filters: " + filters);

					// 通过filters生成通用的查询条件
					CriterionUtil util = new CriterionUtil();
					criteria.addAll(util.generateSearchCriteriaFromFilters(filters));

					final JQGridPTO<JQAccount> pto = accountDao.queryPaginaction(page, rows, sidx, sord, criteria);

					model.put("JSON_OBJECT", pto);
				} else {
					String searchField = request.getParameter("searchField"); // searchField:id
					logger.info("searchField: " + searchField);
					String searchOper = request.getParameter("searchOper"); // searchOper:eg
					logger.info("searchOper: " + searchOper + "]");
					String searchString = request.getParameter("searchString"); // searchString:7
					logger.info("searchString: " + searchString);

					// 通过searchField、searchString、searchOper生成通用的查询条件
					CriterionUtil util = new CriterionUtil();
					Criterion criterion = util.generateSearchCriterion(searchField, searchString, searchOper);
					if (criterion != null) {
						criteria.add(criterion);
					}

					final JQGridPTO<JQAccount> pto = accountDao.queryPaginaction(page, rows, sidx, sord, criteria);

					model.put("JSON_OBJECT", pto);
				}
			} else {// _search:false
				final JQGridPTO<JQAccount> pto = accountDao.queryPaginaction(page, rows, sidx, sord, criteria);

				model.put("JSON_OBJECT", pto);
			}
		}

		return new ModelAndView(new JSONView(), model);
	}

	GaAccountDao accountDao;

	public GaAccountDao getAccountDao()
	{
		return accountDao;
	}

	public void setAccountDao(GaAccountDao accountDao)
	{
		this.accountDao = accountDao;
	}

	private final static Logger logger = LoggerFactory.getLogger(GaAccountController.class);

}
