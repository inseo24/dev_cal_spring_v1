package com.example.ecommerce.persistence.board;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Board")
public class BoardJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    private String userId;
    private Long imageId;
    private String title;
    private String content;

    @Builder
    public BoardJpaEntity(String userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
