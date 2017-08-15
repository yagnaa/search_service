package com.zenefits.api.autosuggest;

import java.util.ArrayList;
import java.util.List;

public class AutoSuggestResponse {
	
	List<Entity> entities;
//	List<String> matchedStrings;
//
//	public List<String> getMatchedStrings() {
//		return matchedStrings;
//	}
//
//	public void setMatchedStrings(List<String> matchedStrings) {
//		this.matchedStrings = matchedStrings;
//	}
	
	public static class Entity
	{
		public String name;
		public String url;
		public Entity(String name, String url) {
			super();
			this.name = name;
			this.url = url;
		}
		
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	
	

}
