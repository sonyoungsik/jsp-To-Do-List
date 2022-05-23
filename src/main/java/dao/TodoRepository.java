package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Todo;

public class TodoRepository {
	
	private static long currentId = 0;
	
	// 컬렉션 ArrayList로 dto 패키지 변수들을 저장받도록 하고 객체는 todos로 명명한다.
	private List<Todo> todos = new ArrayList<>();
	
	//[14~19 line] 디자인 패턴 중 생성구조 - 싱글턴 패턴을 이용하여 instance 한번 정의하고 여러번 재사용이 가능하다..
	private static TodoRepository instance = new TodoRepository();
	
	public static TodoRepository getInstance() {
		return instance;
	}
	
	// dto 변수들을 컬렉션 ArrayList 저장된 값들을 불러올 때 사용한다..
	public List<Todo> getTodos(){
		return todos;
	}
	
	public void addTodo(Todo todo) {
		todo.setId(currentId);
		currentId++;
		
		todos.add(todo);
	}
}