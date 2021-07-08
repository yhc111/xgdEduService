import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

/**
 * @Author: Shawn Yin
 * @Date: 2021/5/12 22:53
 */
public class AAA {

    @Test
    public void testConnection(){
        // 创建mongodb客户端
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        // 或者采用连接字符串
        // MongoClientURI connectionString = new MongoClientURI("mongodb://root:root@localhost:27017");
        // MongoClient mongoClient = new MongoClient(connectionString);
        // 连接数据库
        MongoDatabase database = mongoClient.getDatabase("test");
        // 连接collection
        MongoCollection<Document> collection = database.getCollection("student");
        // 查询第一个文档
        Document myDoc = collection.find().first();
        // 得到文件内容 json串
        String json = myDoc.toJson();
        System.out.println(json);
    }

}
