package roles;

import departments.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

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

    @Test
    public void getName_InstantiatesWithName_Chairman(){
        Role role = setupNewRole();
        assertEquals("chairman",role.getName());
    }

    @Test
    public void getCreatedAt_InstantiatesWithCurrentTimeToday(){
        Role role = setupNewRole();
        assertEquals(LocalDateTime.now().getDayOfWeek(),role.getCreatedAt().getDayOfWeek());
    }

    // HELPER
    public Role setupNewRole(){
        return new Role("chairman");
    }

}