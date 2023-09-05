package com.example.postpart2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 이걸 선언해야 자동으로 시간을 넣어주는 기능이 수행된다.
public abstract class Timestamped { // 추상 클래스가 아니여도 괜찮긴하나, 여기 클래스 자체를 Entity할 일이 없으므로 써도 된다.

    @CreatedDate // 최초생성 시간이 저장된다.
    @Column(updatable = false) // 업데이트를 막는다 시간 수정을 막는다.
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate // 변경된 시간으로 수정되어 저장된다.
    @Column
    @Temporal(TemporalType.TIMESTAMP) // 날짜나 시간데이터를 매핑 할 때 사용됨
    private LocalDateTime modifiedAt;
}