package me.whiteship.java8to11.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApp {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("keesun");
        names.add("whiteship");
        names.add("toby");
        names.add("foo");

        // 스트림이 처리하는 데이터를 변경하지 않는다.
        System.out.println("1.==================");
        Stream<String> stringStream = names.stream()
                .map(String::toUpperCase);
        names.forEach(System.out::println);

        // 스트림의 중개/종료 오퍼레이션 중 중개 오퍼레이션은 근본적으로 lazy 하다.
        // 정의만 했을 뿐!
        System.out.println("2.==================");
        names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        });

        // 리턴 타입 List -> 종료형 오퍼레이터
        System.out.println("3.==================");
        List<String> collect = names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);

        // 손쉽게 병렬 처리를 할 수 있다.
        System.out.println("4.==================");
        for(String name : names){
            if(name.startsWith("k")){
                System.out.println(name.toUpperCase());
            }
        }

        System.out.println("--------------------");

        names.stream()
                .filter(n -> n.startsWith("k"))
                .forEach(s -> System.out.println(s.toUpperCase()));

        System.out.println("5.==================");
        // parallelStream를 이용해 병렬적으로 실행 -> 무조건 빨라지는 것은 아님!
        // 쓰레드를 만들어서 처리하는 비용이 듦(쓰레드 생성, 병렬적으로 처리하고 수집, 쓰레드 스위칭 비용 ...)
        // 데이터가 방대하게 많은 경우 유용
        List<String> collectParallel = names.parallelStream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collectParallel.forEach(System.out::println);

        System.out.println("--------------------");

        // 같은 쓰레드에서 실행
        List<String> collectThread = names.stream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collectThread.forEach(System.out::println);
    }
}
