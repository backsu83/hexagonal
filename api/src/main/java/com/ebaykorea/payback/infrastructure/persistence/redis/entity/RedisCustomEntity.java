package com.ebaykorea.payback.infrastructure.persistence.redis.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@RedisHash(value = "id")
public class RedisCustomEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private Long amount;
    private LocalDateTime refreshTime;

    @Builder
    public RedisCustomEntity(String id, Long amount, LocalDateTime refreshTime) {
        this.id = id;
        this.amount = amount;
        this.refreshTime = refreshTime;
    }
}
