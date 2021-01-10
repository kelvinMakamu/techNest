package departments;

import members.Member;
import members.MemberPayload;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class DepartmentService implements DepartmentDao{

    private final Sql2o sql2o;

    public DepartmentService(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Department> getAllDepartments() {
        String query = "SELECT * FROM departments ORDER BY id DESC";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(query)
                    .executeAndFetch(Department.class);
        }
    }

    @Override
    public List<MemberPayload> getAllDepartmentMembers(int departmentId) {
        String query = "SELECT members.id AS memberId, members.firstName AS firstName, members.lastName As lastName," +
                "departments.id AS departmentId, departments.name AS departmentName, roles.name AS roleName " +
                " FROM members,departments,roles WHERE members.roleId=roles.id AND members.departmentId=departments.id " +
                " AND members.departmentId=:id ORDER BY members.id DESC";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(query)
                    .addParameter("id",departmentId)
                    .executeAndFetch(MemberPayload.class);
        }
    }

    @Override
    public void addDepartment(Department department) {
        String query = "INSERT INTO departments(name) VALUES(:name)";
        try(Connection connection = sql2o.open()){
            int id = (int)connection.createQuery(query,true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        }catch (Sql2oException ex){
            System.out.println("Database connection failed "+ex.getLocalizedMessage());
        }
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        String query = "SELECT * FROM departments WHERE id=:id";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(query)
                    .addParameter("id",departmentId)
                    .executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public void updateDepartment(int departmentId, String name) {
        String query = "UPDATE departments SET name=:name WHERE id=:id";
        try(Connection connection = sql2o.open()){
            connection.createQuery(query)
                    .addParameter("id",departmentId)
                    .addParameter("name",name)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println("Database Error "+ex.getLocalizedMessage());
        }
    }

    @Override
    public void deleteDepartmentById(int departmentId) {
        String query = "DELETE FROM departments WHERE id=:id";
        try(Connection connection = sql2o.open()){
            connection.createQuery(query)
                    .addParameter("id",departmentId)
                    .executeUpdate();
        }catch(Sql2oException ex){
            System.out.println("Database Error "+ex.getLocalizedMessage());
        }
    }

    @Override
    public void deleteDepartments() {
        String query = "DELETE FROM departments";
        try(Connection connection = sql2o.open()){
            connection.createQuery(query)
                    .executeUpdate();
        }catch(Sql2oException ex){
            System.out.println("Database Error "+ex.getLocalizedMessage());
        }
    }
}
