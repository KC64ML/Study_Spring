import javax.persistence.*;

@Entity

public class Member {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;


//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        if(this.team != null) {	// this.team이 null이 아니면 이 member객체는 team이 있음을 의미
            this.team.getMembers().remove(this);		// 해당 팀의 멤버에서 삭제
        }
        this.team = team;
        team.getMembers().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
