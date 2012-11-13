package pl.kwi.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import pl.kwi.entities.UserEntity;
import pl.kwi.services.UserService;

@Component("TableServlet")
public class TableServlet implements HttpRequestHandler{
	
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(TableServlet.class);
	
	@Autowired
	private UserService userService;
	

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String submit = request.getParameter("submit");

		if("Display".equals(submit)){
			displayPage(request, response);
			return;
		}else if("Create".equals(submit)){
			handleCreateButton(request, response);
			return;
		}else if("View".equals(submit)){
			handleViewButton(request, response);
			return;
		}else if("Edit".equals(submit)){
			handleEditButton(request, response);
			return;
		}else if("Delete".equals(submit)){
			handleDeleteButton(request, response);
			return;
		}		
		
	}
	
	private void displayPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		List<UserEntity> users = userService.getUserList();		
		request.setAttribute("users", users);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/TableJSP.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	private void handleCreateButton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/create.do?submit=Display");
		requestDispatcher.forward(request, response);
		
	}
	
	private void handleViewButton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("id");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view.do?submit=Display&id=" + id);
		requestDispatcher.forward(request, response);
		
	}
	
	private void handleEditButton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("id");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.do?submit=Display&id=" + id);
		requestDispatcher.forward(request, response);
		
	}	
	
	private void handleDeleteButton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("id");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/delete.do?submit=Display&id=" + id);
		requestDispatcher.forward(request, response);
		
	}
		
}
