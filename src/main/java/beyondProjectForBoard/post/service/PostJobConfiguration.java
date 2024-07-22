package beyondProjectForBoard.post.service;

import beyondProjectForBoard.post.domain.Post;
import beyondProjectForBoard.post.repository.PostRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

/*
* batchJob : 일련의 작업
* 배치 전용 테이블이 존재해야함.
* 경로 : ~\spring-batch-core\4.3.8\~\spring-batch-core-4.3.8.jar!\org\springframework\batch\core\schema-mysql.sql
* [schema-mysql.sql] 내부의 소스코드 복사해서 사용하면 batch 전용 테이블 생성 완료
* */

// batch 작업 목록 정의
@Configuration
public class PostJobConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private PostRepository postRepository;

    public Job excuteJob(){
        return jobBuilderFactory.get("excuteJob")
                .start(firstStep())
                .next(secondStep())
                .build();
    }

    @Bean
    public Step firstStep(){
        return stepBuilderFactory.get("firstStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("===예약글쓰기 batch task1 시작===");
                    Page<Post> posts = postRepository.findByAppointment(Pageable.unpaged(), "Y");
                    for(Post p : posts){
                        if(p.getAppointmentTime().isBefore(LocalDateTime.now())){
                            p.updateAppointment("N");
                        }
                    }
                    System.out.println("===예약글쓰기 task1 종료");
                    return  RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step secondStep(){
        return stepBuilderFactory.get("secondStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("===예약글쓰기 batch task2 시작===");
                    System.out.println("===예약글쓰기 task2 종료");
                    return  RepeatStatus.FINISHED;
                }).build();
    }

}
