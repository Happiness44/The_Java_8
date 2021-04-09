# THE JAVA 8 정리(04/10)
## Stream
### Stream API
- 콜렉션 or 연속된 데이터를 처리하는 오퍼레이션의 모음!
- 컬렉션이 데이터를 가지고 있고, 그 데이터를 소스로 처리를 하는 것 자체를 스트림이라 함!<br><br>
- sequence(연속) of elements supporting sequential and parallel aggregate operations
- 데이터를 담고 있는 저장소(컬렉션)이 아니다.
- 스트림이 처리하는 데이터를 변경하지 않는다.
- 스트림으로 처리하는 데이터는 오직 한번만 처리된다.(여러 개의 오퍼레이터를 한번만 지나감)
- 무제한일 수도 있다.(Short Circuit 메소드를 사용해서 제한 가능)
- 스트림의 중개/종료 오퍼레이션 중 중개 오퍼레이션은 근본적으로 lazy 하다.
- 손쉽게 병렬 처리를 할 수 있다.

### 스트림 파이프라인
- 0 또는 다수의 중개 오퍼레이션과 한 개의 종료 오퍼레이션(반드시 필요!)으로 구성된다.
- 스트림의 데이터 소스는 종료 오퍼레이션을 실행할 때에만 처리된다.

### 중개 오퍼레이션
- <b>Stream을 리턴</b>
- 종료 오퍼레이션이 오기 전까지 실행하지 X
- Stateless / Stateful 오퍼레이션으로 구분할 수도 있다.
    - 대부분은 Stateless 지만 distinct 나 sorted 처럼 이전 소스 데이터를 참조해야 하는 오퍼레이션은 Stateful 오퍼레이션
- filter, map, limit, skip, sorted ...

### 종료 오퍼레이션
- <b>Stream을 리턴하지 않고 다른 타입을 리턴</b>
- collect, allMatch, count, forEach, min, max ...

### Stream API 사용 예제
- 걸러내기
  - Filter(Predicate)
  - 예) 이름이 3글자 이상인 데이터만 새로운 스트림으로
  
- 변경하기
  - Map(Function) or FlatMap(Function)
  - 예) 각각의 Post 인스턴스에서 String title만 새로운 스트림으로
  - List<Stream<String>>을 String의 스트림으로
  
- 생성하기
  - generate(Supplier) 또는 Iterate(T seed, UnaryOperator)
  - 예) 10부터 1씩 증가하는 무제한 숫자 스트림
  - 예) 랜덤 int 무제한 스트림
  
- 제한하기
  - limit(long) or skip(long)
  - 예) 최대 5개의 요소가 담긴 스트림을 리턴
  - 예) 앞으로 3개를 뺀 나머지 스트림을 리턴
  
- 스트림에 있는 데이터가 특정 조건을 만족하는 지 확인
  - 예) k로 시작하는 문자열이 있는 지 확인(return true or false)
  - 예) 스트림에 있는 모든 값이 10보다 작은 지 확인
  
- 개수 세기
  - count()
  - 예) 10보다 큰 수의 개수를 센다.
  
- 스트림을 데이터 하나로 뭉치기
  - reduce(identity, BiFunction), collect(), sum(), max()
  - 
  
## Optional

- 자바 프로그래밍에서 NPE를 종종 보게 되는 이유
    - null을 리턴 && null 체크 X
- 메소드에서 작업 중 특별한 상황에서 값을 제대로 리턴할 수 없는 경우 선택할 수 있는 방법
    - 예외를 던진다.
    - null을 리턴한다.
    - Optional을 리턴한다.
- Optional
    - 오직 값 한 개가 들어있을 수도 없을 수도 있는 컨테이너
- 주의할 것
    - 리턴값으로만 쓰기를 권장(메소드 매개변수 타입, 맵의 키 타입, 인스턴스 필드 타입으로 쓰지 말자!)
    - Optional을 리턴하는 메소드에서 null을 리턴하지 말자
    - 프리티브 타입용 Optional 을 따로 있다.
    -