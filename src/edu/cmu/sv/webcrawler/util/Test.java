package edu.cmu.sv.webcrawler.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class Test {

	public void mongoConnection() {

	}

	public static void main(String args[]) {
		MongoHelper mongo=new MongoHelper();
		DB db=mongo.getDb();
		DBCollection coll=db.getCollection("keywords");
		try {
			BufferedReader br = new BufferedReader(new FileReader("./keywords.txt"));
			String line=br.readLine();
			while(line!=null){
				coll.insert(new BasicDBObject().append("value", line));
				line=br.readLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
