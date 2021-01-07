package components.departments;

import org.junit.After;
import org.junit.Before;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class DepartmentServiceTest {

    private DepartmentService departmentService;
    private Connection connection;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/technest_test";

    @Before
    public void setUp() throws Exception {
        Sql2o sql2o       = new Sql2o(this.DB_URL,null,null);
        departmentService = new DepartmentService(sql2o);
        connection        = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
    }

    // HELPER METHODS
    public Department setupNewDepartment(){
        return new Department("Business Engagement");
    }
}