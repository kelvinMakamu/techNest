package components.departments;

import components.members.Member;
import org.sql2o.Sql2o;

import java.util.List;

public class DepartmentService implements DepartmentDao{

    public DepartmentService(Sql2o sql2o) {
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

    }

    @Override
    public Department getDepartmentById(int departmentId) {
        return null;
    }

    @Override
    public void updateDepartment(int departmentId, String name) {

    }

    @Override
    public void deleteDepartmentById(int departmentId) {

    }
}
