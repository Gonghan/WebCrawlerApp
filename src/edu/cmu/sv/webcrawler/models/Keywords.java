package edu.cmu.sv.webcrawler.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import edu.cmu.sv.webcrawler.util.MongoHelper;

public class Keywords {

	private DBCollection collection;

	public Keywords() {
		MongoHelper helper = new MongoHelper();
		this.collection = helper.getDb().getCollection("keywords");
	}

	public void insert(String line) {
		BasicDBObject obj = new BasicDBObject();
		obj.put("value", line);
		collection.insert(obj);
	}

	public Set<String> getKeywords() {
		Set<String> set = new HashSet<String>();
		DBCursor cursor = collection.find();
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				String tmp = (String) obj.get("value");
				set.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}

	public void removeAll() {
		BasicDBObject doc = new BasicDBObject();
		collection.remove(doc);
	}

	public Map<String, Integer> getKeywords(String symbol,String year) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("symbol", symbol);
		doc.put("year", year);
		DBCursor cursor = MongoHelper.getCollection().find(doc);
		Map<String, Integer> map = null;
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			BasicDBList keywords = (BasicDBList) obj.get("keywords");
			map =getMap(keywords);
			break;
		}
		return map;
	}

	public static Map<String, Integer> getMap(BasicDBList keywords) {
		Map<String,Integer>map = new HashMap<String,Integer>();
		for (Iterator<Object> it = keywords.iterator(); it.hasNext();) {
			BasicDBObject dbo = (BasicDBObject) it.next();
			for (String s : dbo.keySet()) {
				map.put(s, dbo.getInt(s));
			}
		}
		return map;

	}
}
