import redis.clients.jedis.Jedis;

/**
 * @author zhouwei
 */
public class TestPassword {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
        jedis.connect();
        jedis.disconnect();
        jedis.flushAll();

        jedis.close();
    }
}
