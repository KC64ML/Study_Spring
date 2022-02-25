package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)  // 이럴경우, jpa가 조회하는 경우에서는 성능이 최적화된다. (읽기 : readOnly = true, 쓰긴 경우 : x)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;  // 컴파일시에 생성자 체크할 수 있으므로 final을 추가해야 한다.

    // 회원 가입
    @Transactional  // readOnly = false가 우선권을 먹는다. (클래스 전체는 readOnly = true이지만 메소드 위에는 readOnly = false 이므로)
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("아마 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
