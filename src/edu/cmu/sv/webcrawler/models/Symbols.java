package edu.cmu.sv.webcrawler.models;

import java.util.List;

import edu.cmu.sv.webcrawler.util.MongoHelper;

public class Symbols {

	List<String> symbols;
	public Symbols(){
		MongoHelper helper=new MongoHelper();
		this.symbols=helper.getAllSymbols();
	}
	/**
	 * @return the symbols
	 */
	public List<String> getSymbols() {
		return symbols;
	}
	/**
	 * @param symbols the symbols to set
	 */
	public void setSymbols(List<String> symbols) {
		this.symbols = symbols;
	}
	
	
}
