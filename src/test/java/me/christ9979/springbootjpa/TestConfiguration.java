package me.christ9979.springbootjpa;

import me.christ9979.springbootjpa.post.event.PostEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    @DataJpaTest가 붙은 테스트는 Repository와 관련된
    Bean들을 등록하지 않으므로 테스트시 직접 등록해줘야한다.
 */
@Configuration
public class TestConfiguration {

    @Bean
    public PostEventListener postEventListener() {
        return new PostEventListener();
    }
}
