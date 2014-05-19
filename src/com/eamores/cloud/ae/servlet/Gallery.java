package com.eamores.cloud.ae.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eamores.cloud.ae.dao.Blob;
import com.eamores.cloud.ae.dao.Tags;
import com.eamores.cloud.ae.tables.Links;

public class Gallery extends HttpServlet {
	private static final long serialVersionUID = -65765132281305858L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
        
        
		String uid = req.getParameter("uid");
		ArrayList<Links> links = Blob.getLinks(uid);
		
		String html = "";
		for(int i = 0; i < links.size(); i++){
			 html += "<a href='/photo?blob_key=" + links.get(i).getBlob_key() + "'><img src='" + links.get(i).getGoogle_url() +  "=s200' alt='image' style='padding:5px'></a>";
		}
		
		req.setAttribute("images", html);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/gallery.jsp");
        dispatcher.forward(req,  res);
	}

}
