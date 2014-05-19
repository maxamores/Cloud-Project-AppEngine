package com.eamores.cloud.ae.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eamores.cloud.ae.dao.Blob;
import com.eamores.cloud.ae.tables.PicSummary;

public class Picture extends HttpServlet {

	private static final long serialVersionUID = -8606179735615762686L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String blob_key = req.getParameter("blob_key");
		PicSummary summary = Blob.getPics(blob_key) ;
		
		String html = "";
		for(int i = 0; i < summary.getTags().size(); i++){
			html += summary.getTags().get(i) + " ";
			
			if(i + 1 < summary.getTags().size()){
				html += ", ";
			}
		}
		
		req.setAttribute("tags", html);
		req.setAttribute("google", summary.getGoogle_url());
		req.setAttribute("amazon", summary.getAmazon_url());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/photo.jsp");
        dispatcher.forward(req,  res);
	}
	
}
