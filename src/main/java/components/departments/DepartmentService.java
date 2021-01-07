package components.departments;

import components.members.Member;

import java.util.List;

public class DepartmentService implements DepartmentDao{

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
