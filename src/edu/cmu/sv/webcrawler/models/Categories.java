package edu.cmu.sv.webcrawler.models;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import edu.cmu.sv.webcrawler.util.MongoHelper;

public class Categories {
	Map<String, List<String>> categories;
	Map<String, Integer> map;

	/**
	 * @return the initMap
	 */
	public Map<String, List<String>> getInitMap() {
		return categories;
	}

	DBCollection cate;

	public Categories() {
		categories = new HashMap<String, List<String>>();
		MongoHelper helper = new MongoHelper();
		this.cate = helper.getDb().getCollection("categories");
		readFromDb();
	}

	// generate categories map
	/*
	 * { finacial: 5 law: 7 }
	 */
	public Categories(Map<String, Integer> keywordsMap) {
		categories = new HashMap<String, List<String>>();
		MongoHelper helper = new MongoHelper();
		this.cate = helper.getDb().getCollection("categories");
		readFromDb();
		this.map = new HashMap<String, Integer>();
		for (String key : categories.keySet()) {
			map.put(key, 0);
		}
		for (String key : keywordsMap.keySet()) {
			String cateKey = getCateKey(key);
			System.out.println(cateKey);
			if(cateKey!=null){
				map.put(cateKey, map.get(cateKey)+keywordsMap.get(key));
			}
		}
	}

	private String getCateKey(String key) {
		for (String cateKey : this.categories.keySet()) {
			List<String> list = categories.get(cateKey);
			if (list.contains(key)) {
				System.out.println(cateKey);
				return cateKey;
			}
		}
		return null;
	}

	private void readFromDb() {
		BasicDBObject doc = new BasicDBObject();
		DBCursor cursor = this.cate.find(doc);
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				for (String key : obj.keySet()) {
					if (key.equals("_id")) {
						continue;
					}
					BasicDBList category = (BasicDBList) obj.get(key);
					List<String> list = new ArrayList<String>();
					for (Object s : category) {
						list.add((String) s);
					}
					categories.put(key, list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void load(InputStream is) {

		try {
			if (is != null) {
				InputStreamReader isr = new InputStreamReader(is);
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(isr);
				Iterator<?> iter = json.keySet().iterator();
				while (iter.hasNext()) {
					String key = (String) iter.next();
					JSONArray msg = (JSONArray) json.get(key);
					List<String> list = new ArrayList<String>();
					for (Object value : msg.toArray()) {
						list.add((String) value);
					}
					categories.put(key, list);
				}
				System.out.println(categories);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		removeAll();
		save();
	}

	private void save() {
		BasicDBObject doc = new BasicDBObject();
		for (String key : this.categories.keySet()) {
			BasicDBList list = new BasicDBList();
			for (String value : this.categories.get(key)) {
				list.add(value);
			}
			doc.put(key, list);
		}
		this.cate.insert(doc);
	}

	private void removeAll() {
		BasicDBObject doc = new BasicDBObject();
		cate.remove(doc);
	}

	public Map<String, Integer> getMap() {
		return map;
	}
}
