import redis.clients.jedis.Jedis;

import java.util.HashMap;

/**
 * @author zhouwei
 */
public class TestHash {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        jedis.hmset("hash", map);
        jedis.hset("hash", "key5", "value5");
        System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));

        System.out.println("散列hash的所有键为：" + jedis.hkeys("hash"));
        System.out.println("散列hash的所有值为：" + jedis.hvals("hash"));
        System.out.println("将key6保存的值加上一个整数，如果key6不存在则添加key6：" + jedis.hincrBy("hash", "key6", 6));
        System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));

        System.out.println("将key6保存的值加上一个整数，如果key6不存在则添加key6：" + jedis.hincrBy("hash", "key6", 3));
        System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));

        System.out.println("删除一个或多个键值对：" + jedis.hdel("hash", "key2"));
        System.out.println("散列hash的所有键值对为：" + jedis.hgetAll("hash"));

        System.out.println("散列hash中的键值对个数：" + jedis.hlen("hash"));
        System.out.println("判断hash中是否存在key2：" + jedis.hexists("hash", "key2"));
        System.out.println("判断hash中是否存在key3：" + jedis.hexists("hash", "key3"));

        System.out.println("获取hash中的一个值：" + jedis.hget("hash", "key3"));
        System.out.println("获取hash中的多个值：" + jedis.hmget("hash", "key3", "key4"));

        jedis.close();
    }
}
