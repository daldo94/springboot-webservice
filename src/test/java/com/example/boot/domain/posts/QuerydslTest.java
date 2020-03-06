package com.example.boot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QuerydslTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAllInBatch();
    }

    @Test
    public void querydsl_select_test(){
        //given
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> result = postsRepository.findAllDesc();

        //then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getContent(),is("content"));
    }

}
