package com.eamores.cloud.ae.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eamores.cloud.ae.dao.Tags;
import com.eamores.cloud.ae.tables.TotalTags;


public class Search extends HttpServlet{

	private static final long serialVersionUID = 5832560395976420055L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String uid = req.getParameter("uid");
		ArrayList<TotalTags> tags = Tags.getTags(uid);
		
		String html = "";
		for(int i = 0; i < tags.size(); i++){
			html += "<span class='label style='padding:5px' label-default'><a href='taggallery?uid=" + uid + "&tag=" + tags.get(i).getTag()  + "'> <font size='4'>" + tags.get(i).getTag() + "(" + tags.get(i).getTotal()+ ")" + "</font></a></span>";
		}
		
		req.setAttribute("tags", html);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/tags.jsp");
        dispatcher.forward(req,  res);
	}
}
