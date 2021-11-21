package infrastructure;

import domain.Member;
import domain.MemberId;
import domain.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class InMemoryMemberRepository implements MemberRepository {

    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<MemberId, Member> data = new ConcurrentHashMap<>();

    @Override
    public void save(final Member member) {
        data.put(member.getMemberId(), member);
    }

    @Override
    public void removeById(final MemberId memberId) { data.remove(memberId); }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public Member byId(final MemberId memberId) {
        final Member member = data.get(memberId);
        if (member == null) {
            throw new RuntimeException("Cannot find member with id " + memberId.getValue());
        }
        return member;
    }

    @Override
    public MemberId nextIdentity() {
        return MemberId.of(counter.incrementAndGet());
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(data.values());
    }
}
