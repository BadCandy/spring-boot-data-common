package me.christ9979.springbootjpa.post;

import me.christ9979.springbootjpa.post.event.PostPublishedEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
    스프링 Entity에 도메인 이벤트를 발생시키고 싶으면
    AbstractAggregateRoot를 상속받아 사용하면 된다.
    원래는 @DomainEvent와 @AfterDomainEventPublication으로 구현해야한다.
    이미 도메인이 상속받은 클래스라면 위의 어노테이션을 사용하는것을 고려하자.

    현재는 save() 할 때만 발생한다.
 */
@Entity
public class Post extends AbstractAggregateRoot<Post> {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    /*
        본문이 255자가 넘을경우 @Lob 어노테이션을 사용한다.
     */
    @Lob
    private String content;

    /*
        TemporalType.TIME : Only 시간
        TemporalType.DATE : Only 날짜
        TemporalType.TIMESTAMP : 날짜 + 시간
    */
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    /*
        cascade :
            Cascade.PERSIST가 적용되어 있으므로 엔티티의 상태변화가 전파된다.
            즉 Post만 insert를 하더라도 comment도 자동으로 insert가 된다.
            기본값은 CascadeType이 정해져있지 않다.
            (정확히는 persistent context에서 상태변화)

        fetch :
            OneToMany는 기본적으로 FetchType.LAZY가 디폴트 값이다.
            왜냐하면, 많은 값을 사용하지도 않을건데, 미리 읽어오면 성능 효율이 떨어지기 때문이다.
     */
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment) {
        this.getComments().add(comment);
        comment.setPost(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    /*
        toString 사용시 lazy loading이 되는 값을 사용하면,
        lazy loading이 안될수도 있으니 유의해서 사용하자.
     */
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                '}';
    }

    /*
        이벤트를 등록한다.
        이 이벤트는 해당 리스너가 작성되어 있어야 처리가된다.
        직접 작성한 PostEventListener 클래스가 받아서 처리한다.
     */
    public Post registerPost() {
        this.registerEvent(new PostPublishedEvent(this));
        return this;
    }
}
