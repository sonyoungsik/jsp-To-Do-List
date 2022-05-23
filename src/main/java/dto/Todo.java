package dto;

public class Todo {

	private long id; // 필드 초기값 : 0
	private String task; // 필드 초기값 : null
	private boolean isDone; // 필드 초기값 : false

	public Todo(String task) { // 오늘 할 일 내용을 입력하여 클래스 생성 시 생성자 호출
		this.task = task;
	}

	// 15 ~ 37 line : 변수 getter - setter 선언(캡슐화, 정보은닉을 위해 해당 방식으로 작성)
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	// 41 ~ 44 line : 저장된 변수가 리스트 컬렉션 등 표현될 때 잘 나오는지 확인하기 위해서 만듦
	
	@Override
	public String toString() {
		return "Todo [id=" + id + ", task=" + task + ", isDone=" + isDone + "]";
	}

}