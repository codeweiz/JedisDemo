import redis.clients.jedis.Jedis;

/**
 * @author zhouwei
 */
public class TestList {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
        System.out.println("========添加一个List=======");
        jedis.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap", "LinkedHashMap");
        jedis.lpush("collections", "HashSet");
        jedis.lpush("collections", "TreeSet");
        jedis.lpush("collections", "TreeMap");
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));
        System.out.println("collections区间[0,3]的元素：" + jedis.lrange("collections", 0, 3));

        System.out.println("==========================");

        System.out.println("删除指定的元素'HashMap'2次：" + jedis.lrem("collections", 2, "HashMap"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));

        System.out.println("删除下标在0-3之外的元素：" + jedis.ltrim("collections", 0, 3));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));

        System.out.println("左出栈一个元素：" + jedis.lpop("collections"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));

        System.out.println("从右边向列表添加元素：" + jedis.rpush("collections", "EnumMap"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));

        System.out.println("右出栈一个元素：" + jedis.rpop("collections"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));

        System.out.println("修改collections指定下标1的内容：" + jedis.lset("collections", 1, "LinkedArrayList"));
        System.out.println("collections的内容：" + jedis.lrange("collections", 0, -1));

        System.out.println("=======================");

        System.out.println("collections的长度：" + jedis.llen("collections"));
        System.out.println("collections下标为2的元素：" + jedis.lindex("collections", 2));

        System.out.println("=======================");
        System.out.println(jedis.lpush("sortedList", "3", "6", "2", "0", "7", "4"));
        System.out.println("sortedList排序前：" + jedis.lrange("sortedList", 0, -1));

        System.out.println(jedis.sort("sortedList"));
        System.out.println("sortedList排序后：" + jedis.lrange("sortedList", 0, -1));
        // 排序并不改变元素原来的顺序

        jedis.close();
    }
}
