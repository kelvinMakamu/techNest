package roles;

import departments.Department;
import members.Member;

import java.util.List;

public interface RoleDao {
    // LIST ALL AVAILABLE ROLES
    List<Role> getAllRoles();
    // ADD NEW ROLE
    void addRole(Role role);
    // GET RESPECTIVE ROLE BY ID
    Role getRoleById(int roleId);
    // UPDATE RESPECTIVE ROLE
    void updateRole(int roleId, String name);
    // DELETE RESPECTIVE ROLE
    void deleteRoleById(int roleId);
    // DELETE ALL ROLES
    void deleteRoles();
}
