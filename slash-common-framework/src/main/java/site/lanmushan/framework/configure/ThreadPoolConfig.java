package site.lanmushan.framework.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Administrator
 */
@Component
public class ThreadPoolConfig {
    @Value("${thread.pool.core.pool.size:1}")
    private int threadPoolCorePoolSize;
    @Value("${thread.pool.max.pool.size:10}")
    private int threadPoolMaxPoolSize;
    @Value("${thread.pool.queue.capacity:10}")
    private int threadPoolQueueCapacity;
    @Value("${thread.pool.keep.alive.seconds:60}")
    private int threadPoolKeepAliveSeconds;

    @Primary
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        /** 核心线程数，默认为1 **/
        threadPoolTaskExecutor.setCorePoolSize(threadPoolCorePoolSize);
        /** 最大线程数，默认为Integer.MAX_VALUE **/
        threadPoolTaskExecutor.setMaxPoolSize(threadPoolMaxPoolSize);
        /** 队列最大长度，一般需要设置值: 大于等于notifyScheduledMainExecutor.maxNum；默认为Integer.MAX_VALUE **/
        threadPoolTaskExecutor.setQueueCapacity(threadPoolQueueCapacity);
        /** 线程池维护线程所允许的空闲时间，默认为60s **/
        threadPoolTaskExecutor.setKeepAliveSeconds(threadPoolKeepAliveSeconds);
        threadPoolTaskExecutor.setQueueCapacity(threadPoolQueueCapacity);
        threadPoolTaskExecutor.setThreadNamePrefix("任务处理线程");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return threadPoolTaskExecutor;

    }
}
