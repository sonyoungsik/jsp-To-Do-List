package controller;

import dto.Todo;
import dao.TodoRepository;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "TodoController", urlPatterns = { "/addTodo.do", "/toggleTodo.do", "/removeTodo.do" })

public class TodoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String command = req.getRequestURI().substring(req.getContextPath().length());

		if (command.equals("/addTodo.do")) {

			String task = req.getParameter("task");

			Todo todo = new Todo(task);

			TodoRepository repository = TodoRepository.getInstance();

			repository.addTodo(todo);
			
		} else if (command.equals("/toggleTodo.do")) {
			
			long id = Long.parseLong(req.getParameter("id"));

			TodoRepository repository = TodoRepository.getInstance();

			repository.toggle(id);
		
		} else if (command.equals("/removeTodo.do")) {
			
			long id = Long.parseLong(req.getParameter("id"));

			TodoRepository repository = TodoRepository.getInstance();

			repository.remove(id);
		}
		req.getRequestDispatcher("todolist.jsp").forward(req, resp);
	}
}
