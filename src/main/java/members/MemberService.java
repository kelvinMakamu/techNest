package members;

import java.util.List;

public class MemberService implements MemberDao{
    @Override
    public List<Member> getAllMembers() {
        return null;
    }

    @Override
    public void addMember(Member member) {

    }

    @Override
    public Member getMemberById(int memberId) {
        return null;
    }

    @Override
    public void updateMember(int memberId, String firstName, String lastName, int departmentId) {

    }

    @Override
    public void deleteMemberById(int memberId) {

    }

    @Override
    public void deleteMembers() {

    }
}
