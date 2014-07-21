package edu.cmu.sv.webcrawler.util;

import java.util.ArrayList;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MongoHelper {
	private static DBCollection collection;
	private static DB db;
	private static boolean auth;

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

	//Now, the mongodb service is deployed on mongolab now which is accessible from local machine
	//No need to maintain two mongo dbs.
	private static void localConnect() {
		connect();
//		try {
//			Mongo mongo = new Mongo("localhost", 27017);
//			db = mongo.getDB("db");
//			collection = db.getCollection(MongoConstants.COLLECTIONS);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
	}

	private static void connect() {
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

	public static DBCollection getCollection() {
		if (collection == null) {
			Map<String, String> env = System.getenv();
			if (env.containsKey("VCAP_SERVICES")) {
				connect();
			} else {
				localConnect();
			}
		}
		return collection;
	}

	public DB getDb(){
		return db;
	}
}
