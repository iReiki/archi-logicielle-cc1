package domain;

import java.util.List;

public interface MemberRepository {

    void save(Member member);

    void removeById(MemberId memberId);

    boolean isEmpty();

    Member byId(MemberId memberId);

    MemberId nextIdentity();

    List<Member> findAll();

    //List<Member> findBySubscription(String type):
}
