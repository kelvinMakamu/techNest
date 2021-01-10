package roles;

import departments.Department;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class RoleService implements RoleDao{

    private final Sql2o sql2o;

    public RoleService(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Role> getAllRoles() {
        String query = "SELECT * FROM roles ORDER BY id DESC";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(query)
                    .executeAndFetch(Role.class);
        }
    }

    @Override
    public void addRole(Role role) {
        String query = "INSERT INTO roles(name) VALUES(:name)";
        try(Connection connection = sql2o.open()){
            int id = (int)connection.createQuery(query,true)
                    .bind(role)
                    .executeUpdate()
                    .getKey();
            role.setId(id);
        }catch (Sql2oException ex){
            System.out.println("Database connection failed "+ex.getLocalizedMessage());
        }
    }

    @Override
    public Role getRoleById(int roleId) {
        String query = "SELECT * FROM roles WHERE id=:id";
        try(Connection connection = sql2o.open()){
            return connection.createQuery(query)
                    .addParameter("id",roleId)
                    .executeAndFetchFirst(Role.class);
        }
    }

    @Override
    public void updateRole(int roleId, String name) {

    }

    @Override
    public void deleteRoleById(int roleId) {

    }

    @Override
    public void deleteRoles() {
        String query = "DELETE FROM roles";
        try(Connection connection = sql2o.open()){
            connection.createQuery(query)
                    .executeUpdate();
        }catch(Sql2oException ex){
            System.out.println("Database Error "+ex.getLocalizedMessage());
        }
    }
}
