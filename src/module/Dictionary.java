package module;

import java.util.Map;

public class Dictionary {
	private Map<String, String> wordMap;
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, String> getWordMap() {
		return wordMap;
	}

	public void setWordMap(Map<String, String> wordMap) {
		this.wordMap = wordMap;
	}
	
}
