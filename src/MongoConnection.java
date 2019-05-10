import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.util.UUID;

public class MongoConnection {

    public static void main( String args[] ) {

        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        DB db = mongoClient.getDB("test");
        DBCollection coll = db.getCollection("testUUID");
        BasicDBObject query = new BasicDBObject("uuid",
                UUID.fromString("5542bbe2-0111-243b-62a9-66c53641af8c"));
        DBObject doc = new BasicDBObject();
        DBCursor cur =coll.find(query);
        while(cur.hasNext())
        {
            System.out.println(cur.next());
        }
        cur.close();
    }
}