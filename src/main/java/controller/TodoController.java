package controller;

import dto.Todo;
import dao.TodoRepository;

import java.util.List;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "TodoController", urlPatterns = { "/addTodo.do", "/toggleTodo.do", "/removeTodo.do", "/clearTodo.do" })

public class TodoController extends HttpServlet {

	/**
	 * TodoController가 HttpServlet을 상속받기 때문에 직렬화(serialible:인스턴스가 파일에 저장되는 과정)로 버전
	 * 불일치 문제를 없애기 위해 UID를 지정해주면 오류가 표시되지 않는다.
	 */
	private static final long serialVersionUID = -5789298759459019056L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String command = req.getRequestURI().substring(req.getContextPath().length());

		TodoRepository repository = TodoRepository.getInstance();

		if (command.equals("/clearTodo.do")) {

			List<Todo> todos = repository.getTodos();

			todos.clear();
		}
		req.getRequestDispatcher("todolist.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String command = req.getRequestURI().substring(req.getContextPath().length());

		// addTodo.do, toggleTodo.do, removeTodo.do 싱글턴 패턴이 중복되므로 하나로 통일시킨다...
		TodoRepository repository = TodoRepository.getInstance();

		if (command.equals("/addTodo.do")) {

			String task = req.getParameter("task");

			Todo todo = new Todo(task);

			repository.addTodo(todo);

		} else if (command.equals("/toggleTodo.do")) {

			long id = Long.parseLong(req.getParameter("id"));

			repository.toggle(id);

		} else if (command.equals("/removeTodo.do")) {

			long id = Long.parseLong(req.getParameter("id"));

			repository.remove(id);
		}
		req.getRequestDispatcher("todolist.jsp").forward(req, resp);
	}
}