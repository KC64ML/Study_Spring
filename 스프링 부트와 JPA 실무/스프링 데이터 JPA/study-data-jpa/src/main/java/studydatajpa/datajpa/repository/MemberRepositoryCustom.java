package studydatajpa.datajpa.repository;

import studydatajpa.datajpa.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
