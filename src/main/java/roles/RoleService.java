package roles;

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
        String query = "UPDATE roles SET name=:name WHERE id=:id";
        try(Connection connection = sql2o.open()){
            connection.createQuery(query)
                    .addParameter("id",roleId)
                    .addParameter("name",name)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println("Database Error "+ex.getLocalizedMessage());
        }
    }

    @Override
    public void deleteRoleById(int roleId) {
        String query = "DELETE FROM roles WHERE id=:id";
        try(Connection connection = sql2o.open()){
            connection.createQuery(query)
                    .addParameter("id",roleId)
                    .executeUpdate();
        }catch(Sql2oException ex){
            System.out.println("Database Error "+ex.getLocalizedMessage());
        }
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
