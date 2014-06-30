package edu.cmu.sv.webcrawler.services;

import java.util.List;

public interface KeywordsService {

	/**
	 * @return the keywords of the risk factors 
	 */
	public List<String>getKeywords();
	
	
	/**
	 * @param symbol: the symbol of a company
	 * @return the list of the keywords of risk factors about a given company
	 */
	public List<String>getKeywordsBySymbol(String symbol);
	
	/**
	 * @param symbol: the symbol of a company
	 * @param keyword
	 * @return whether the risk factors of a given company contains the keyword
	 */
	public boolean containsKeyword(String symbol,String keyword);
}
