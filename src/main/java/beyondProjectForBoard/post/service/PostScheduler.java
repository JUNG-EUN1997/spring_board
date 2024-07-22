package beyondProjectForBoard.post.service;

import beyondProjectForBoard.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostScheduler {
    private final PostRepository postRepository;

    @Autowired
    public PostScheduler(PostRepository postRepository){
        this.postRepository = postRepository;
    }

//    @Transactional
//    @Scheduled(cron = "0 0/1 * * * *")
////    cron의 각 자리 의미 [초 분 시간 일 월 요일]
////    예시) * * * * * * : 매초 매분 매시간 매일 매월 매요일 마다 반복
////    예시) 0 0 * * * * : 매일 0분 0초에 > 1시간에 1번
////    예시) 0 0 11 * * * : 매일 11시 0분 0초에 > 매일 11시에
////    예시) 0 0/1 * * * * : 매일 매분 마다 >
////    예시) 0 1 * * * * : 매일 매시 1분에 >
//    public void postSchedule(){
//        System.out.println("======예약글 쓰기 스케쥴러 시작======");
//        Page<Post> posts = postRepository.findAllByAppointment(Pageable.unpaged(),"Y");
//        for(Post p : posts){
//            if(p.getAppointmentTime().isBefore(LocalDateTime.now())){ //현재 시간보다 지남
//                p.updateAppointment("Y");
//            }
//        }
//    }

}
