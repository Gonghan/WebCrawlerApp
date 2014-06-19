package edu.cmu.sv.webcrawler.util;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MongoHelper {
	private DBCollection collection;
	private DB db;
	private boolean auth;

	public MongoHelper() {
		this.connect();
		auth = false;

	}

	private void connect() {
		try {
			Mongo mongo = new Mongo(MongoConstants.HOST, MongoConstants.PORT);
			// Mongo mongo=new Mongo("localhost",27017);
			db = mongo.getDB(MongoConstants.DATABASE);
			auth = db.authenticate(MongoConstants.USERNAME,
					MongoConstants.PASSWORD.toCharArray());
			this.collection = db.getCollection(MongoConstants.COLLECTIONS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertCompanySymbol(String symbol) {
		DBCollection cs = db.getCollection("companysymbols");
		BasicDBObject doc = new BasicDBObject();
		doc.put("symbol", symbol);
		cs.insert(doc);
	}

	public ArrayList<String> getAllSymbols() {
		ArrayList<String> list = new ArrayList<String>();
		DBCollection cs = db.getCollection("companysymbols");
		DBCursor cursor = collection.find();
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				String tmp = (String) obj.get("symbol");
				list.add(tmp);
			}
		} catch (Exception e) {
			list.add("" + auth);
			// list.add(MongoConstants.URL);
			list.add(e.getMessage());
		}
		return list;
	}

	public DBCollection getCollection() {
		return collection;
	}

}
