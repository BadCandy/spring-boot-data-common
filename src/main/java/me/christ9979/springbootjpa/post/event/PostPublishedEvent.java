package me.christ9979.springbootjpa.post.event;

import me.christ9979.springbootjpa.post.Post;
import org.springframework.context.ApplicationEvent;

/*
    이벤트를 발행하기 위한 이벤트 클래스.
    ApplicationEvent를 상속해서 작성한다.
 */
public class PostPublishedEvent extends ApplicationEvent {

    private final Post post;

    public PostPublishedEvent(Object source) {
        super(source);
        this.post = (Post) source;
    }

    public Post getPost() {
        return post;
    }
}
