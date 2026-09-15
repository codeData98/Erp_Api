package com.erp.signoff.controller;

import com.erp.signoff.common.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * RedisTemplate 连通性测试接口（仅用于验证 Redis 集成，验证通过后可删除）。
 */
@RestController
@RequestMapping("/api/redis-test")
public class RedisTestController {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisTestController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /** 写入字符串：POST /api/redis-test/set?key=test&value=hello */
    @PostMapping("/set")
    public Result<String> set(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return Result.success("写入成功: " + key);
    }

    /** 写入并设置过期时间（秒）：POST /api/redis-test/set-expire?key=test&value=hello&seconds=60 */
    @PostMapping("/set-expire")
    public Result<String> setWithExpire(@RequestParam String key, @RequestParam String value,
                                        @RequestParam(defaultValue = "60") long seconds) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(seconds));
        return Result.success("写入成功: " + key + "，" + seconds + " 秒后过期");
    }

    /** 读取：GET /api/redis-test/get?key=test */
    @GetMapping("/get")
    public Result<Object> get(@RequestParam String key) {
        return Result.success(redisTemplate.opsForValue().get(key));
    }

    /** 自增计数：GET /api/redis-test/increment?key=counter */
    @GetMapping("/increment")
    public Result<Long> increment(@RequestParam String key) {
        return Result.success(redisTemplate.opsForValue().increment(key));
    }

    /** 查询剩余过期时间（秒）：GET /api/redis-test/ttl?key=test */
    @GetMapping("/ttl")
    public Result<Long> ttl(@RequestParam String key) {
        return Result.success(redisTemplate.getExpire(key, TimeUnit.SECONDS));
    }

    /** 写入 JSON 对象（验证 JSON 序列化）：POST /api/redis-test/set-object?key=user，body 传 JSON */
    @PostMapping("/set-object")
    public Result<String> setObject(@RequestParam String key, @RequestBody Map<String, Object> obj) {
        redisTemplate.opsForValue().set(key, obj);
        return Result.success("对象写入成功: " + key);
    }

    /** 删除：DELETE /api/redis-test/delete?key=test */
    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestParam String key) {
        return Result.success(Boolean.TRUE.equals(redisTemplate.delete(key)));
    }
}
