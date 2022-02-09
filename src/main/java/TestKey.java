import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author zhouwei
 */
public class TestKey {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println("清空当前数据库数据：" + jedis.flushDB());
        System.out.println("判断某个键是否存在：" + jedis.exists("k1"));
        System.out.println("新增键k1值v1：" + jedis.set("k1", "v1"));
        System.out.println("新增键k2值v2：" + jedis.set("k2", "v2"));
        Set<String> keys = jedis.keys("*");
        System.out.println("当前数据库中所有的keys：" + keys);
        System.out.println("删除键k1：" + jedis.del("k1"));
        System.out.println("判断键k1是否存在：" + jedis.exists("k1"));
        System.out.println("查看键k2对应的值v2的数据类型：" + jedis.type("k2"));
        System.out.println("随机返回keys空间里的一个键：" + jedis.randomKey());
        System.out.println("重命名键k2为key2：" + jedis.rename("k2", "key2"));
        System.out.println("键改名后取对应的值：" + jedis.get("key2"));
        System.out.println("按索引查询：" + jedis.select(0));
        System.out.println("删除当前数据库的所有键值对：" + jedis.flushDB());
        System.out.println("查询当前数据库键的个数：" + jedis.dbSize());
        System.out.println("清空所有数据库的键值对：" + jedis.flushAll());

        jedis.close();
    }
}
