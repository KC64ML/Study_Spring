package study.datajpa.repository;

public interface NestedClosedProjections {

    String getUsername();  // 첫 번째만 최적화가 된다.
    TeamInfo getTeam(); // 두 번째부터는 최적화가 되지 않는다.

    interface TeamInfo {
        String getName();
    }

}
