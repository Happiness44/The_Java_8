package me.whiteship.java8to11.optional;

import java.util.Optional;

public class OnlineClass {
    private Integer id;
    private String title;
    private boolean closed;
    public Progress progress;
    // 인스턴스의 필드 타입
    // public Optional<Progress> progress; -> 상위/하위 클래스로 쪼갬

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /*public Progress getProgress() {
        // 1) throw 에러 -> checked Exception을 thorw 할 경우 에러 처리 강제, 에러 발생의 정보를 담는(stacktrace) 리소스 사용
        if(this.progress == null){
            throw new IllegalStateException();
        }

        return progress;
    }*/

    // 비어 있을 수도 or 값 하나만을 담고 있는 수도 있는 컨테이너 인스턴스의 타입
    // 비어 있는 값이 전달될 수 있는 경우 Optional로 감싸서 사용
    public Optional<Progress> getProgress() {
        // 참고하는 타입이 null일 수 있음
        return Optional.ofNullable(progress);

        // 참고하는 타입이 null일 수 없음
        // return Optional.of(progress);

        // return null;
        // return Optional.empty();
    }

    /*public void setProgress(Progress progress) {
        this.progress = progress;
    }*/

    public void setProgress(Optional<Progress> progress) {
        /*if(progress != null){
            progress.ifPresent(p -> this.progress = p);
        }*/

        progress.ifPresent(p -> this.progress = p);
    }

    @Override
    public String toString() {
        return "OnlineClass{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", closed=" + closed +
                '}';
    }
}
