package com.erp.signoff;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RedisTemplate 集成测试。
 * <p>注意：本测试连接 application.yml 中配置的真实 Redis（120.79.247.255:6379），
 * 需保证网络可达后才能通过。</p>
 */
@SpringBootTest(classes = SignoffApplication.class)
class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    @DisplayName("① 字符串写入与读取")
    void testSetAndGet() {
        String key = "test:string";
        redisTemplate.opsForValue().set(key, "hello-redis");

        Object value = redisTemplate.opsForValue().get(key);
        assertNotNull(value);
        assertEquals("hello-redis", value);

        redisTemplate.delete(key);
    }

    @Test
    @DisplayName("② 写入带过期时间并校验 TTL")
    void testSetWithExpire() {
        String key = "test:expire";
        redisTemplate.opsForValue().set(key, "temp-value", Duration.ofSeconds(5));

        Long ttl = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        assertNotNull(ttl);
        assertTrue(ttl > 0 && ttl <= 5, "TTL 应在 1~5 秒之间，实际: " + ttl);

        redisTemplate.delete(key);
    }

    @Test
    @DisplayName("③ 对象 JSON 序列化写入与读取")
    void testSetObject() {
        String key = "test:object";
        Map<String, Object> user = new HashMap<>();
        user.put("name", "张三");
        user.put("orgId", 1001L);

        redisTemplate.opsForValue().set(key, user);

        Object value = redisTemplate.opsForValue().get(key);
        assertNotNull(value);
        assertTrue(value instanceof Map);
        Map<?, ?> cached = (Map<?, ?>) value;
        assertEquals("张三", cached.get("name"));
        assertEquals(1001, ((Number) cached.get("orgId")).intValue());

        redisTemplate.delete(key);
    }

    @Test
    @DisplayName("④ 自增计数器（INCR）")
    void testIncrement() {
        String key = "test:counter";
        redisTemplate.delete(key);

        Long v1 = redisTemplate.opsForValue().increment(key);
        Long v2 = redisTemplate.opsForValue().increment(key);

        assertEquals(1L, v1);
        assertEquals(2L, v2);

        redisTemplate.delete(key);
    }

    @Test
    @DisplayName("⑤ 删除后读取应返回 null")
    void testDelete() {
        String key = "test:delete";
        redisTemplate.opsForValue().set(key, "to-be-deleted");

        Boolean deleted = redisTemplate.delete(key);
        assertTrue(deleted);
        assertNull(redisTemplate.opsForValue().get(key));
    }
}
