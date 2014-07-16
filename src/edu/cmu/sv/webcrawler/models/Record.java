package edu.cmu.sv.webcrawler.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import edu.cmu.sv.webcrawler.services.KeywordExtractor;
import edu.cmu.sv.webcrawler.services.KeywordsService;
import edu.cmu.sv.webcrawler.util.MongoHelper;

public class Record {

	String companyName;
	String year;
	String riskFactor;
	String symbol;
	Map<String, Integer> keywords;
	Map<String, Integer> categories;

	/**
	 * @return the keywords
	 */
	public Map<String, Integer> getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(Map<String, Integer> keywords) {
		this.keywords = keywords;
	}

	public Record(String riskFactor, String symbol, String year,
			Map<String, Integer> keywords) {
		this.year = year;
		this.riskFactor = riskFactor;
		this.symbol = symbol;
		this.keywords = keywords;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the riskFactor
	 */
	public String getRiskFactor() {
		return riskFactor;
	}

	/**
	 * @param riskFactor
	 *            the riskFactor to set
	 */
	public void setRiskFactor(String riskFactor) {
		this.riskFactor = riskFactor;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Record [year=" + year + ", riskFactor=" + riskFactor
				+ ", symbol=" + symbol + "]";
	}

	/**
	 * Save the record into MongoDB
	 * 
	 * @param companyName
	 * @param year
	 * @param symbol
	 * @return
	 */
	public boolean save() {
		DBCollection db = MongoHelper.getCollection();
		BasicDBObject doc = new BasicDBObject();
		KeywordsService ks = new KeywordExtractor(riskFactor);
		Map<String, Integer> map = ks.getKeywordsBySymbol();
		BasicDBList list = new BasicDBList();
		for (String s : map.keySet()) {
			DBObject tmp = new BasicDBObject();
			tmp.put(s, map.get(s));
			list.add(tmp);
			System.out.println(s + "=>" + map.get(s));
		}
		doc.put("symbol", symbol);
		doc.put("year", year);
		doc.put("riskFactor", riskFactor);
		doc.put("keywords", list);
		db.insert(doc);
		this.keywords = map;
		return true;
	}

	public static List<Record> search(String symbol) {
		List<Record> records = new ArrayList<Record>();
		DBCollection db = MongoHelper.getCollection();
		BasicDBObject doc = new BasicDBObject();
		doc.put("symbol", symbol);
		DBCursor cursor = db.find(doc);
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				String year = (String) obj.get("year");
				String riskFactor = (String) obj.get("riskFactor");
				BasicDBList keywords = (BasicDBList) obj.get("keywords");
				Map<String, Integer> map = Keywords.getMap(keywords);
				records.add(new Record(riskFactor, symbol, year, map));
			}
		} catch (Exception e) {
		}
		return records;
	}

	public static List<Record> search(String symbol, String year) {
		List<Record> records = new ArrayList<Record>();
		DBCollection db = MongoHelper.getCollection();
		BasicDBObject doc = new BasicDBObject();
		doc.put("symbol", symbol);
		doc.put("year", year);
		DBCursor cursor = db.find(doc);
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				String riskFactor = (String) obj.get("riskFactor");
				BasicDBList keywords = (BasicDBList) obj.get("keywords");
				Map<String, Integer> map = Keywords.getMap(keywords);
				records.add(new Record(riskFactor, symbol, year, map));
			}
		} catch (Exception e) {
		}
		return records;
	}

	public static void remove(String symbol, String year) {
		DBCollection db = MongoHelper.getCollection();
		BasicDBObject doc = new BasicDBObject();
		doc.put("symbol", symbol);
		if (year != null && !year.isEmpty()) {
			doc.put("year", year);
		}
		db.remove(doc);
	}

	public static void removeAll() {
		DBCollection db = MongoHelper.getCollection();
		BasicDBObject doc = new BasicDBObject();
		db.remove(doc);
	}
}
