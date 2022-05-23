<%-- <%@page import="dto.Todo"%>
<%@page import="dao.TodoRepository"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 

//request.setCharacterEncoding("UTF-8");

long id = Long.parseLong(request.getParameter("id"));

TodoRepository repository = TodoRepository.getInstance();

repository.toggle(id);

response.sendRedirect("todolist.jsp");

%> --%>