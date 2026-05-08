package se.iuh.orderService.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class ResilienceConfig {

    @Value("${resilience.external-client.name:order-external-client}")
    private String clientName;

    @Value("${resilience.external-client.circuit-breaker.failure-rate-threshold:50}")
    private float cbFailureRateThreshold;

    @Value("${resilience.external-client.circuit-breaker.slow-call-rate-threshold:50}")
    private float cbSlowCallRateThreshold;

    @Value("${resilience.external-client.circuit-breaker.slow-call-duration-seconds:2}")
    private long cbSlowCallDurationSeconds;

    @Value("${resilience.external-client.circuit-breaker.sliding-window-size:10}")
    private int cbSlidingWindowSize;

    @Value("${resilience.external-client.circuit-breaker.minimum-number-of-calls:5}")
    private int cbMinimumNumberOfCalls;

    @Value("${resilience.external-client.circuit-breaker.wait-duration-open-seconds:10}")
    private long cbWaitDurationOpenSeconds;

    @Value("${resilience.external-client.circuit-breaker.permitted-half-open-calls:3}")
    private int cbPermittedHalfOpenCalls;

    @Value("${resilience.external-client.retry.max-attempts:3}")
    private int retryMaxAttempts;

    @Value("${resilience.external-client.retry.wait-duration-millis:300}")
    private long retryWaitDurationMillis;

    @Value("${resilience.external-client.retry.fail-after-max-attempts:true}")
    private boolean retryFailAfterMaxAttempts;

    @Value("${resilience.external-client.rate-limiter.limit-refresh-period-seconds:1}")
    private long rlLimitRefreshPeriodSeconds;

    @Value("${resilience.external-client.rate-limiter.limit-for-period:10}")
    private int rlLimitForPeriod;

    @Value("${resilience.external-client.rate-limiter.timeout-duration-millis:200}")
    private long rlTimeoutDurationMillis;

    @Value("${resilience.external-client.time-limiter.timeout-duration-seconds:2}")
    private long tlTimeoutDurationSeconds;

    @Value("${resilience.external-client.time-limiter.cancel-running-future:true}")
    private boolean tlCancelRunningFuture;

    @Value("${resilience.external-client.executor.pool-size:4}")
    private int executorPoolSize;

    @Bean
    public CircuitBreaker externalClientCircuitBreaker() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(cbFailureRateThreshold)
                .slowCallRateThreshold(cbSlowCallRateThreshold)
                .slowCallDurationThreshold(Duration.ofSeconds(cbSlowCallDurationSeconds))
                .slidingWindowSize(cbSlidingWindowSize)
                .minimumNumberOfCalls(cbMinimumNumberOfCalls)
                .waitDurationInOpenState(Duration.ofSeconds(cbWaitDurationOpenSeconds))
                .permittedNumberOfCallsInHalfOpenState(cbPermittedHalfOpenCalls)
                .build();
        return CircuitBreaker.of(clientName, config);
    }

    @Bean
    public Retry externalClientRetry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(retryMaxAttempts)
                .waitDuration(Duration.ofMillis(retryWaitDurationMillis))
                .failAfterMaxAttempts(retryFailAfterMaxAttempts)
                .build();
        return Retry.of(clientName, config);
    }

    @Bean
    public RateLimiter externalClientRateLimiter() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(rlLimitRefreshPeriodSeconds))
                .limitForPeriod(rlLimitForPeriod)
                .timeoutDuration(Duration.ofMillis(rlTimeoutDurationMillis))
                .build();
        return RateLimiter.of(clientName, config);
    }

    @Bean
    public TimeLimiter externalClientTimeLimiter() {
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(tlTimeoutDurationSeconds))
                .cancelRunningFuture(tlCancelRunningFuture)
                .build();
        return TimeLimiter.of(config);
    }

    @Bean(destroyMethod = "shutdown")
    public ScheduledExecutorService externalClientExecutor() {
        return Executors.newScheduledThreadPool(executorPoolSize);
    }
}
