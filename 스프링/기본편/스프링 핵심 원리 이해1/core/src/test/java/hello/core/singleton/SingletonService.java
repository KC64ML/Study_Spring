package hello.core.singleton;

public class SingletonService {

    // 1. static 영역에 객체를 딱 1개만 생성해둔다.
    // 자기 자신을 내부에 private로 가지는데, static으로 가짐
    private static final SingletonService instance = new SingletonService();

    // public으로 열어서 객체 인스턴스가 필요하면 이 static 메소드를 통해 조회하도록 허용
    public static SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private으로 선언해 외부에서 new 키워드를 사용한 객체 생성을 못하도록 막음
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}