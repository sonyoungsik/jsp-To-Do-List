package controller;

import dto.Todo;
import dao.TodoRepository;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "TodoController", urlPatterns = {"/addTodo.do"})

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
		}
		req.getRequestDispatcher("todolist.jsp").forward(req, resp);
	}
}
