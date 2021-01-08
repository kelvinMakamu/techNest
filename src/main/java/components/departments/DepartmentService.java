package components.departments;

import components.members.Member;
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
        return null;
    }

    @Override
    public List<Member> getAllDepartmentMembers(int departmentId) {
        return null;
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

    }

    @Override
    public void deleteDepartmentById(int departmentId) {

    }
}
