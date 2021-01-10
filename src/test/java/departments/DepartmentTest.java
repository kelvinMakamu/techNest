package departments;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void NewDepartment_InstantiatesCorrectly_true(){
        Department department = setupNewDepartment();
        assertEquals(true, department instanceof Department);
    }

    @Test
    public void getName_InstantiatesWithName_Business_Engagement(){
        Department department = setupNewDepartment();
        assertEquals("business engagement",department.getName());
    }

    @Test
    public void getCreatedAt_InstantiatesWithCurrentTimeToday(){
        Department department = setupNewDepartment();
        assertEquals(LocalDateTime.now().getDayOfWeek(),department.getCreatedAt().getDayOfWeek());
    }

    public Department setupNewDepartment(){
        return new Department("business engagement");
    }
}