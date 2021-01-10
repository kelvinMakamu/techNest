package members;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class MemberService implements MemberDao{

    private final Sql2o sql2o;

    public MemberService(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Member> getAllMembers() {
        return null;
    }

    @Override
    public void addMember(Member member) {
        String query = "INSERT INTO members(firstName,lastName,departmentId) VALUES(:firstName,:lastName,:departmentId)";
        try(Connection connection = sql2o.open()){
            int id = (int)connection.createQuery(query,true)
                    .bind(member)
                    .executeUpdate()
                    .getKey();
            member.setId(id);
        }catch (Sql2oException ex){
            System.out.println("Database connection failed "+ex.getLocalizedMessage());
        }
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
