package components.departments;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        assertEquals("Business Engagement",department.getName());
    }

    public Department setupNewDepartment(){
        return new Department("Business Engagement");
    }
}