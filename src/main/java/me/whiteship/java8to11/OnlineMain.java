package me.whiteship.java8to11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OnlineMain {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring으로 시작하는 수업");
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.println(oc.getId()));
        System.out.println("====================");

        System.out.println("close 되지 않은 수업");
        springClasses.stream()
                .filter(oc -> !oc.isClosed())
                .forEach(oc -> System.out.println(oc.getId()));
        System.out.println("--------------------");
        // 임의의 객체에 인스턴스 메소드 참조(Predicate, java 11)
        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed))
                .forEach(oc -> System.out.println(oc.getId()));
        System.out.println("====================");

        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream().map(OnlineClass::getTitle)
                .forEach(System.out::println);
        System.out.println("====================");

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        // flatMap -> 내부의 데이터를 꺼낸다.
        System.out.println("두 수업 목록에 들어 있는 모든 수업 아이디 출력");
        keesunEvents.stream()
                .flatMap(List::stream)
                .forEach(oc -> System.out.println(oc.getId()));
        System.out.println("====================");

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);
        System.out.println("====================");

        System.out.println("자바 수업 중에 Test가 들어 있는 수업이 있는 지 확인");
        boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);
        System.out.println("====================");

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 것만 모아서 List로 만들기");
        // 순서에 따라 지나가는 타입이 다를 수 있다.
        List<String> filterList = springClasses.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)         // 메소드 레퍼런스
                .collect(Collectors.toList());
        filterList.stream().forEach(System.out::println);
        System.out.println("--------------------");
        List<String> mapList = springClasses.stream()
                .map(oc -> oc.getTitle())
                .filter(t -> t.contains("spring"))
                .collect(Collectors.toList());
        mapList.stream().forEach(System.out::println);
        System.out.println("====================");
    }
}
