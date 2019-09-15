package mvc.model;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

public class Notas {

	private Integer id;
	private String user;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Calendar date;
	private String priority;
	private String msg;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Calendar getDate() {
		return this.date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
