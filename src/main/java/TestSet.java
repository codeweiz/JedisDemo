import redis.clients.jedis.Jedis;

/**
 * @author zhouwei
 */
public class TestSet {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
        System.out.println("==========向集合中添加元素，不重复===========");

        System.out.println(jedis.sadd("eleSet", "e1", "e2", "e4", "e3", "e0", "e8", "e7", "e5"));
        System.out.println(jedis.sadd("eleSet", "e6"));
        System.out.println(jedis.sadd("eleSet", "e6"));
        System.out.println("eleSet的所有元素为：" + jedis.smembers("eleSet"));

        System.out.println("删除一个元素e0：" + jedis.srem("eleSet", "e0"));
        System.out.println("eleSet的所有元素为：" + jedis.smembers("eleSet"));

        System.out.println("删除一个元素e0：" + jedis.srem("eleSet", "e7", "e6"));
        System.out.println("eleSet的所有元素为：" + jedis.smembers("eleSet"));

        System.out.println("随机移除集合中的一个元素：" + jedis.spop("eleSet"));
        System.out.println("随机移除集合中的一个元素：" + jedis.spop("eleSet"));
        System.out.println("eleSet的所有元素为：" + jedis.smembers("eleSet"));

        System.out.println("eleSet中包含元素的个数：" + jedis.scard("eleSet"));

        System.out.println("e3是否在eleSet中：" + jedis.sismember("eleSet", "e3"));
        System.out.println("e1是否在eleSet中：" + jedis.sismember("eleSet", "e1"));
        System.out.println("e7是否在eleSet中：" + jedis.sismember("eleSet", "e7"));

        System.out.println("=====================");

        jedis.sadd("eleSet1", "e1", "e2", "e3", "e4", "e5", "e7", "e8", "e0");
        jedis.sadd("eleSet2", "e1", "e2", "e3", "e4", "e8", "e0");

        System.out.println("将eleSet1的e1移到eleSet3中：" + jedis.smove("eleSet1", "eleSet3", "e1"));
        System.out.println("eleSet1中的元素：" + jedis.smembers("eleSet1"));
        System.out.println("eleSet3中的元素：" + jedis.smembers("eleSet3"));

        System.out.println("===========集合运算============");
        System.out.println("eleSet1中的元素：" + jedis.smembers("eleSet1"));
        System.out.println("eleSet2中的元素：" + jedis.smembers("eleSet2"));
        System.out.println("eleSet1和eleSet2的交集：" + jedis.sinter("eleSet1", "eleSet2"));
        System.out.println("eleSet1和eleSet2的并集：" + jedis.sunion("eleSet1", "eleSet2"));
        System.out.println("eleSet1和eleSet2的差集，eleSet1中有，eleSet2中没有：" + jedis.sdiff("eleSet1", "eleSet2"));
        System.out.println("eleSet2和eleSet1的差集，eleSet2中有，eleSet1中没有：" + jedis.sdiff("eleSet2", "eleSet1"));

        System.out.println("求交集并将交集保存到eleSet4中：" + jedis.sinterstore("eleSet4", "eleSet1", "eleSet2"));
        System.out.println("eleSet4中的元素：" + jedis.smembers("eleSet4"));
        System.out.println("求并集并将并集保存到eleSet5中：" + jedis.sunionstore("eleSet5", "eleSet1", "eleSet2"));
        System.out.println("eleSet5中的元素：" + jedis.smembers("eleSet5"));
        System.out.println("求(eleSet1-eleSet2)差集并将差集保存到eleSet6中：" + jedis.sdiffstore("eleSet6", "eleSet1", "eleSet2"));
        System.out.println("eleSet6中的元素：" + jedis.smembers("eleSet6"));
        System.out.println("求(eleSet2-eleSet1)差集并将差集保存到eleSet7中：" + jedis.sdiffstore("eleSet7", "eleSet2", "eleSet1"));
        System.out.println("eleSet7中的元素：" + jedis.smembers("eleSet7"));

        jedis.close();
    }
}
