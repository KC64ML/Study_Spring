package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 가입을 하고 회원을 찾으려면 앞서 만든 MemberRepository 인터페이스가 필요하다.
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // DIP를 위반하고 있다.
    // 추상화, 구현체 의존 (두 개 의존 좋지 못하다.)

    @Override
    public void join(Member member) {
        memberRepository.save(member);
        // MemoryMemberRepository 구조체 안에 save 메서드 호출하여 member 저장
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
