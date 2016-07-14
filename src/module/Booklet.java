package module;

import java.util.Map;

public class Booklet {
	
	private String user_id;
	private String type;
	private Map<String, String> wordMap;
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, String> getWordMap() {
		return wordMap;
	}
	public void setWordMap(Map<String, String> wordMap) {
		this.wordMap = wordMap;
	}
	
}
