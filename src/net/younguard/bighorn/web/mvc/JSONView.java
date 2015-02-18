package net.younguard.bighorn.web.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

/**
 * this view writes the json response to the httpResponse
 * 
 * @author Thomas.H.Zhang
 */
public class JSONView
		extends AbstractView
{
	public JSONView()
	{
		super();
		// ������Ӧͷ,MIMEtype������������͵��ļ�����?
		setContentType("application/json");
	}

	protected void renderMergedOutputModel(Map model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		response.setContentType(getContentType());
		String rs = JSONSerializer.toJSON(model.get("JSON_OBJECT")).toString();
		////System.out.println(rs);
		// the JSONSerializer is part of - http://json-lib.sourceforge.net/, we
		// get the json array from the model hashmap, let the serializer turn it
		// into json text, then we send it down the wire
		//response.getWriter().flush();
		response.getWriter().write(rs);
		//response.getWriter().
		
		//tmp add code by andy
		//response.sendRedirect("view.html");
		//response.sendRedirect("catalog-query.go");
	}
}
