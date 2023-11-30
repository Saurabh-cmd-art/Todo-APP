package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;
import dto.UserDto;
import service.UserService;

@WebServlet("/Addtask")
public class AddTask extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDto dto = (UserDto) req.getSession().getAttribute("user");
		if (dto == null) {
			resp.getWriter().print("<h1 align='center' style='color:red'>Session Expired , Login Again</h1>");
			req.getRequestDispatcher("todologin.html").include(req, resp);
		} else {
			String tname = req.getParameter("tname");
			String tdesc = req.getParameter("tdesc");

			Task task = new Task();
			
			task.setName(tname);
			task.setDescription(tdesc);
			task.setCreatedTime(LocalDateTime.now());
			task.setStatus(false);

			UserService service = new UserService();
			service.saveTask(task);

			List<Task> tasks = dto.getTasks();
			if (tasks == null)
				tasks = new ArrayList<Task>();
			tasks.add(task);
			dto.setTasks(tasks);

			service.updateUser(dto);

			UserDao dao = new UserDao();
			req.getSession().setAttribute("user", dao.findByEmail(dto.getEmail()));

			resp.getWriter().print("<h1 align='center' style='color:green'>Task Added Success</h1>");
			req.setAttribute("list", dto.getTasks());
			req.getRequestDispatcher("home.jsp").include(req, resp);

		}
	}
}
