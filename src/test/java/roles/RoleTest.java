package roles;

import departments.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void NewRole_InstantiatesCorrectly_true(){
        Role role = setupNewRole();
        assertEquals(true, role instanceof Role);
    }

    // HELPER
    public Role setupNewRole(){
        return new Role("Chairman");
    }

}