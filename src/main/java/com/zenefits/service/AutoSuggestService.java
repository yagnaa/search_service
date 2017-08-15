package com.zenefits.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.zenefits.api.IndexRequest;
import com.zenefits.api.autosuggest.AutoSuggestResponse;
import com.zenefits.api.autosuggest.AutoSuggestResponse.Entity;
import com.zenefits.db.models.Trie;

public class AutoSuggestService {

	Trie trie = new Trie();

	public AutoSuggestService(){

	}

	public AutoSuggestResponse suggest(String prefix) throws Exception{
		AutoSuggestResponse resp = new AutoSuggestResponse();
		List<String> matchedStrings = trie.getString(prefix);
		List<Entity> entities = new ArrayList<>();
		if(matchedStrings!=null)
		{
			for(String matchedString: matchedStrings)
			{
				Entity e = new Entity(matchedString,"http://"+matchedString);
				entities.add(e);
			}
		}
		
		resp.setEntities(entities);
		return resp;
	}

	public String indexStrings(IndexRequest request) throws Exception{

		for(String stringToAdd: request.getStringsToAdd())
		{
			trie.addString(stringToAdd);
		}

		return "Success";
	}
	
	public String indexStringsFromFile(String fileName) throws Exception{

        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                String[] csvEntry = line.split(cvsSplitBy);
                trie.addString(csvEntry[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

		return "Success";
	}

}
