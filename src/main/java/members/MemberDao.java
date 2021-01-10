package members;

import java.util.List;

public interface MemberDao {
    // LIST ALL AVAILABLE MEMBERS
    List<Member> getAllMembers();
    // ADD NEW MEMBER
    void addMember(Member member);
    // GET RESPECTIVE MEMBER BY ID
    Member getMemberById(int memberId);
    // UPDATE RESPECTIVE MEMBER
    void updateMember(int memberId, String firstName, String lastName, int departmentId);
    // DELETE RESPECTIVE MEMBER
    void deleteMemberById(int memberId);
    // DELETE ALL MEMBERS
    void deleteMembers();
}
