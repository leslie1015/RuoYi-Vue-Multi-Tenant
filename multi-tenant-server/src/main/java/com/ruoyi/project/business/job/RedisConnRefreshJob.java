package com.ruoyi.project.business.job;

import com.ruoyi.framework.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("RedisConnRefreshJob")
public class RedisConnRefreshJob {

    @Autowired
    private RedisCache redisCache;

    /**
     * 刷新redis连接
     *
     * @throws Exception
     */
    public void doExecute() throws Exception {
        redisCache.getCacheMap("1");
    }
    
}
