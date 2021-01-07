package components.departments;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public Department setupNewDepartment(){
        return new Department("Business Engagement");
    }
}