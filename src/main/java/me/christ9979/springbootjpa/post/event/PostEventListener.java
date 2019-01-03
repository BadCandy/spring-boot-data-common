package me.christ9979.springbootjpa.post.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/*
    Post Entity에서 발생한 이벤트를 처리하는 리스너 클래스
 */
@Component
public class PostEventListener {

    @EventListener
    public void printOriginalObject(PostPublishedEvent event) {
        System.out.println("===================");
        System.out.println(event.getPost());
        System.out.println("===================");
    }
}
