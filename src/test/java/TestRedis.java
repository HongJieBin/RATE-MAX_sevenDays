import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestRedis {

    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        Transaction multi = jedis.multi();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name", "nisdasd");
        try {
            multi.set("user1", jsonObject.toJSONString());
            multi.set("user2", jsonObject.toJSONString());
            multi.exec();
        }
        catch (Exception e) {
            multi.discard();
        }
        finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }

    }
}
