package me.whiteship.java8to11.optional;

import java.time.Duration;
import java.util.Optional;
import java.util.OptionalInt;

public class OnlineMain {
    public static void main(String[] args) {
        // null을 참조
        OnlineClass springBoot = new OnlineClass(1, "spring boot", true);
        /*Duration studyDuration = springBoot.getProgress().getStudyDuration();
        System.out.println(studyDuration);*/

        /*Progress progress = springBoot.getProgress();
        // 2) null 리턴 -> 체크
        if(progress != null){
            System.out.println(progress.getStudyDuration());
        }*/

        // 주의 1) 파라미터로 사용 : null을 호출, 문법적 오류가 아님
        springBoot.setProgress(null);

        // 주의 2) Map의 Key 타입 사용 : Map 인터페이스의 특징을 깨버림(key는 null일 수 없음)
        
        
        // 원시 타입용 Optional
        OptionalInt.of(10);

        // Optional을 리턴하는 메소드에서 null을 리턴하지 말자
        OnlineClass onlineClass = new OnlineClass(1, "spring boot", true);
        Optional<Progress> progress = onlineClass.getProgress();
    }
}
