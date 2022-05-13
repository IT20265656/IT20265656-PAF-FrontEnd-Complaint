package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Complain;

//for map
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class ComplainAPI
 */
@WebServlet("/ComplainAPI")
public class ComplainAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Complain comObj = new Complain();
	
	// Convert request parameters to a Map
	private static Map getParsMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "URF-8");
			String queryString = scanner.hasNext() ?
								scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for (String param : params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}catch(Exception e)
		{
			
		}
		return map;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplainAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String output = comObj.insertComplain(
				request.getParameter("combox"),
				request.getParameter("comDate"),
				request.getParameter("Userid"));

		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
