package components.departments;

import components.members.Member;

import java.util.List;

public interface DepartmentDao {
    // LIST ALL AVAILABLE DEPARTMENTS
    List<Department> getAllDepartments();
    // RESPECTIVE DEPARTMENT STAFF MEMBERS
    List<Member> getAllDepartmentMembers(int departmentId);
    // ADD NEW DEPARTMENT
    void addDepartment(Department department);
    // GET RESPECTIVE DEPARTMENT BY ID
    Department getDepartmentById(int departmentId);
    // UPDATE RESPECTIVE DEPARTMENT
    void updateDepartment(int departmentId, String name);
    // DELETE RESPECTIVE DEPARTMENT
    void deleteDepartmentById(int departmentId);
}
