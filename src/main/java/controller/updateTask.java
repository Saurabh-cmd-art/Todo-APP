package controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;
import dto.UserDto;
import service.UserService;

@WebServlet("update-task")
public class updateTask extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDto dto = (UserDto) req.getSession().getAttribute("user");
		if (dto == null) {
			resp.getWriter().print("<h1 align='center' style='color:red'>Session Expired, Login Again</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {
			String tName = req.getParameter("tname");
			String tDescription = req.getParameter("tdescription");
			int id = Integer.parseInt(req.getParameter("id"));

			// Loading values inside objects to save using hibernate
			Task task = new Task();
			task.setId(id);
			task.setName(tName);
			task.setDescription(tDescription);
			task.setCreatedTime(LocalDateTime.now());
			task.setStatus(false);

			// Saving Task
			UserService service = new UserService();
			service.updateUser(dto);

			UserDao dao = new UserDao();
			// Updating session
			UserDto dto2 = dao.findByEmail(dto.getEmail());
			req.getSession().setAttribute("user", dto2);

			resp.getWriter().print("<h1 align='center' style='color:green'>Task Updated Success</h1>");
			// Sending data to Home.jsp
			req.setAttribute("list", dto2.getTasks());
			req.getRequestDispatcher("Home.jsp").include(req, resp);
		}
	
		}
	}

