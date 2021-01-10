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
        roleService.deleteRoles();
        connection.close();
    }

    @Test
    public void addRole_addingSetsId(){
        Role role      = setupNewRole();
        int originalID = role.getId();
        roleService.addRole(role);
        assertNotEquals(originalID, role.getId());
    }

    @Test
    public void getAllRoles_ReturningForNoRoles(){
        assertEquals(0,roleService.getAllRoles().size());
    }

    @Test
    public void getAllRoles_CountAddedRoles(){
        Role role  = setupNewRole();
        roleService.addRole(role);
        assertEquals(1,roleService.getAllRoles().size());
    }

    @Test
    public void getRoleById_getSpecificRole(){
        Role role  = setupNewRole();
        roleService.addRole(role);
        Role foundRole = roleService.getRoleById(role.getId());
        assertEquals(role.getId(),foundRole.getId());
        assertEquals(role.getName(),foundRole.getName());
    }

    @Test
    public void updateRole_EditRoleName(){
        Role role          = setupNewRole();
        String initialName = role.getName();
        roleService.addRole(role);
        roleService.updateRole(role.getId(), "Secretary");
        Role foundRole     = roleService.getRoleById(role.getId());
        String updatedName = foundRole.getName();
        assertNotEquals(initialName,updatedName);
    }

    @Test
    public void deleteRoleById_deleteSpecificRole(){
        Role role = setupNewRole();
        roleService.addRole(role);
        roleService.deleteRoleById(role.getId());
        assertEquals(0,roleService.getAllRoles().size());
    }

    @Test
    public void deleteRoles_deleteAvailableRoles(){
        Role role  = setupNewRole();
        roleService.addRole(role);
        roleService.deleteRoles();
        assertEquals(0,roleService.getAllRoles().size());
    }

    // HELPER METHODS
    public Role setupNewRole(){
        return new Role("Chairman");
    }

}