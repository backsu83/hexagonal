package com.ebaykorea.payback.scheduler;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
public class ExecutorTreadPoolTest {

  // 100ms 만큼 block되는 작업
  private Runnable getTask() {
    return () -> {
      try {
        TimeUnit.MILLISECONDS.sleep(100);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    };
  }

  @Test
  void testThreadPoolTasks() throws Exception {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
        10,
        10,
        10,
        TimeUnit.SECONDS,
        new LinkedBlockingDeque<>(100));

    IntStream.range(0,100).forEach(i -> threadPoolExecutor.execute(getTask()));

    int poolSize = threadPoolExecutor.getPoolSize();
    int queueSize = threadPoolExecutor.getQueue().size();

    assertThat(poolSize).isEqualTo(10);
    assertThat(queueSize).isEqualTo(90);

    try {
      threadPoolExecutor.shutdown();
      threadPoolExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void testCompletableFutureAllOf() {

    List<CompletableFuture> futures = Lists.newArrayList();

    IntStream.range(0,10).forEach(i -> {
      futures.add(CompletableFuture.supplyAsync(() -> {
        log.info("task-{}" , i);
        return "task-" + i;
      }));
    });

    final var join = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
        .thenApply(s -> {
          List<Object> result = futures.stream()
              .filter(f-> Objects.nonNull(f))
              .map(pageContentFuture -> pageContentFuture.join())
              .collect(Collectors.toList());
          return result;
        }).join();

    assertTrue(join.size() == 10);
    assertTrue(join.stream().anyMatch(s->s.equals("task-1")));
  }
}
