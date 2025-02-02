package com.board.springbootboard.domain.posts;


import com.board.springbootboard.domain.BaseTimeEntity;
import com.board.springbootboard.domain.user.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
@DynamicUpdate // 변경 필드만 대응
public class PostsEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 500,nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String author;

    // 생성자에 빌더 사용시, 생성자에 포함된 빌드만 빌더에 포함
    @Builder
    public PostsEntity(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // 수정
    public void update(String title,String content) {
        this.title=title;
        this.content=content;
    }


    // User와 매핑 (양방햐 매핑)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private UserEntity user;

    public void setUser(UserEntity user) {
        if(Objects.nonNull(this.user)) {
            this.user.getUsers().remove(this);
        }

        this.user = user;
        user.getUsers().add(this);
    }







}
