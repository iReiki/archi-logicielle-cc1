package domain;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MembershipService {

    private MemberRepository memberRepository;

    public MembershipService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createMember(Member member) {
        this.memberRepository.save(member);
    }

    public void changeMemberPassword(MemberId memberId, String newPassword) {
        var member = this.memberRepository.byId(memberId);
        member.changePassword(newPassword);
        this.memberRepository.save(member);
    }

    public void removeMemberById(MemberId memberId) { this.memberRepository.removeById(memberId); }

    public boolean isRepositoryEmpty() {
        return this.memberRepository.isEmpty();
    }

    public Member getMemberById(MemberId memberId) { return this.memberRepository.byId(memberId); }

    public List<Member> allMembers() {
        return this.memberRepository.findAll();
    }

    public boolean verifyMember(String lastname, String firstname, String email, String password) {
        return !lastname.isEmpty() && !firstname.isEmpty() && checkEmail(email) && !password.isEmpty();
    }

    private boolean checkEmail(String email) {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(email);
        return matcher.find();
    }


}
