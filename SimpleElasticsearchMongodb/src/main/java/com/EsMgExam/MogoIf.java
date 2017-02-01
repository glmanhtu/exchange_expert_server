package com.EsMgExam;
import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
public class MogoIf {
	public void addDocument(String inName, String inAddr)
	{
		try {
			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("quoctest");
			DBCollection table = db.getCollection("test2");
			BasicDBObject document = new BasicDBObject();
			document.put("name", inName);
			document.put("addr", inAddr);
			table.insert(document);

		}catch (UnknownHostException e) {
			e.printStackTrace();
	    } catch (MongoException e) {
	    	e.printStackTrace();
	    }
	}
}
