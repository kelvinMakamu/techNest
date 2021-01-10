package roles;

import org.sql2o.Sql2o;

import java.util.List;

public class RoleService implements RoleDao{

    private final Sql2o sql2o;

    public RoleService(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Role> getAllRoles() {
        return null;
    }

    @Override
    public void addRole(Role role) {

    }

    @Override
    public Role getRoleById(int roleId) {
        return null;
    }

    @Override
    public void updateRole(int roleId, String name) {

    }

    @Override
    public void deleteRoleById(int roleId) {

    }

    @Override
    public void deleteRoles() {

    }
}
