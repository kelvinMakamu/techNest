package members;

import departments.Department;
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
    public List<MemberPayload> getAllMembers() {
        String query = "SELECT members.id AS memberId, members.firstName AS firstName, members.lastName As lastName," +
                "departments.id AS departmentId, departments.name AS departmentName" +
                " FROM members,departments WHERE members.departmentId=departments.id" +
                " ORDER BY members.id DESC";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(query)
                    .executeAndFetch(MemberPayload.class);
        }
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
        String query = "SELECT * FROM members WHERE id=:id";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(query)
                    .addParameter("id",memberId)
                    .executeAndFetchFirst(Member.class);
        }
    }

    @Override
    public void updateMember(int memberId, String firstName, String lastName, int departmentId) {
        String query = "UPDATE members SET firstName=:firstName,lastName=:lastName,departmentId=:departmentId" +
                " WHERE id=:id";
        try(Connection connection = sql2o.open()){
            connection.createQuery(query)
                    .addParameter("id",memberId)
                    .addParameter("firstName",firstName)
                    .addParameter("lastName",lastName)
                    .addParameter("departmentId",departmentId)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println("Database Error "+ex.getLocalizedMessage());
        }
    }

    @Override
    public void deleteMemberById(int memberId) {
        String query = "DELETE FROM members WHERE id=:id";
        try(Connection connection = sql2o.open()){
            connection.createQuery(query)
                    .addParameter("id",memberId)
                    .executeUpdate();
        }catch(Sql2oException ex){
            System.out.println("Database Error "+ex.getLocalizedMessage());
        }
    }

    @Override
    public void deleteMembers() {
        String query = "DELETE FROM members";
        try(Connection connection = sql2o.open()){
            connection.createQuery(query)
                    .executeUpdate();
        }catch(Sql2oException ex){
            System.out.println("Database Error "+ex.getLocalizedMessage());
        }
    }
}
