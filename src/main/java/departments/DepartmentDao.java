package departments;

import members.Member;
import members.MemberPayload;

import java.util.List;

public interface DepartmentDao {
    // LIST ALL AVAILABLE DEPARTMENTS
    List<Department> getAllDepartments();
    // RESPECTIVE DEPARTMENT STAFF MEMBERS
    List<MemberPayload> getAllDepartmentMembers(int departmentId);
    // ADD NEW DEPARTMENT
    void addDepartment(Department department);
    // GET RESPECTIVE DEPARTMENT BY ID
    Department getDepartmentById(int departmentId);
    // UPDATE RESPECTIVE DEPARTMENT
    void updateDepartment(int departmentId, String name);
    // DELETE RESPECTIVE DEPARTMENT
    void deleteDepartmentById(int departmentId);
    // DELETE ALL DEPARTMENTS
    void deleteDepartments();
}
