package edu.cmu.sv.webcrawler.util;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Map;

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
		// check whether this app is on BlueMix
		Map<String, String> env = System.getenv();
		if (env.containsKey("VCAP_SERVICES")) {
			connect();
		} else {
			localConnect();
		}
		auth = false;

	}

	private void localConnect() {
		try {
			Mongo mongo = new Mongo("localhost", 27017);
			db = mongo.getDB(MongoConstants.DATABASE);
			collection = db.getCollection(MongoConstants.COLLECTIONS);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private void connect() {
		try {
			Mongo mongo = new Mongo(MongoConstants.HOST, MongoConstants.PORT);
			db = mongo.getDB(MongoConstants.DATABASE);
			auth = db.authenticate(MongoConstants.USERNAME,
					MongoConstants.PASSWORD.toCharArray());
			collection = db.getCollection(MongoConstants.COLLECTIONS);
		} catch (Exception e) {
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
		DBCursor cursor = cs.find();
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
