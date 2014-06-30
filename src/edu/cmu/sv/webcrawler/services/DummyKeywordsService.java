package edu.cmu.sv.webcrawler.services;

import java.util.ArrayList;
import java.util.List;

public class DummyKeywordsService implements KeywordsService{

	public List<String> getKeywords() {
		List<String> kws=new ArrayList<String>();
		kws.add("A");
		kws.add("B");
		kws.add("C");
		return kws;
	}

	public List<String> getKeywordsBySymbol(String symbol) {
		List<String> kws=new ArrayList<String>();
		kws.add("A");
		return kws;
	}

	public boolean containsKeyword(String symbol, String keyword) {
		// TODO Auto-generated method stub
		return false;
	}

}
