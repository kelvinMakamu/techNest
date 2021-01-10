package roles;

import departments.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;
import org.sql2o.Connection;

import static org.junit.Assert.*;

public class RoleServiceTest {

    private RoleService roleService;
    private Connection connection;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/technest_test";

    @Before
    public void setUp() throws Exception {
        Sql2o sql2o = new Sql2o(this.DB_URL,null,null);
        roleService = new RoleService(sql2o);
        connection  = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    @Test
    public void addRole_addingSetsId(){
        Role role      = setupNewRole();
        int originalID = role.getId();
        roleService.addRole(role);
        assertNotEquals(originalID, role.getId());
    }

    // HELPER METHODS
    public Role setupNewRole(){
        return new Role("Chairman");
    }

}