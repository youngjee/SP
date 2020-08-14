package file.divide;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Info {
	private LocalDateTime time;
	private String id;
	private String message;
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Info(String time, String id, String message) {

		this.time = translateTime(time);
		this.id = id;
		this.message = message;
	}
	
	public LocalDateTime translateTime(String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);
		
		return localDateTime;
	}
}
