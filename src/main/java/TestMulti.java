import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author zhouwei
 */
public class TestMulti {
    public static void main(String[] args) {
        // 创建客户端，根据 ip 和 port 连接服务器，redis-server需要已开启
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 清空当前数据库的所有键值对
        jedis.flushDB();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "world");
        jsonObject.put("name", "java");

        // 开启事务
        Transaction multi = jedis.multi();
        String result = jsonObject.toJSONString();
        try {
            // 向 redis 存入一条数据
            multi.set("json", result);
            // 向 redis 再存入一条数据
            multi.set("json2", result);
            // 这里发生异常
//            int i = 100/0;
            multi.exec();
        } catch (Exception e) {
            e.printStackTrace();
            // 如果发生异常，回滚
            multi.discard();
        } finally {
            System.out.println(jedis.get("json"));
            System.out.println(jedis.get("json2"));
            // 最终关闭客户端
            jedis.close();
        }
    }
}
