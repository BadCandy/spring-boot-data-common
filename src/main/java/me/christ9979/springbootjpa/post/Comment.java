package me.christ9979.springbootjpa.post;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    /*
        fetch :
            ManyToOne는 기본적으로 FetchType.EAGER가 디폴트 값이다.
            왜냐하면, 한개의 값은 미리 읽어와도 성능에 큰영향을 미치지 않는다.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    private Integer likeCount;

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }
}
