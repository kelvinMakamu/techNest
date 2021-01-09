package departments;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
        departmentService.deleteDepartments();
        connection.close();
    }

    @Test
    public void addDepartment_addingSetsId(){
        Department department = setupNewDepartment();
        int originalID        = department.getId();
        departmentService.addDepartment(department);
        assertNotEquals(originalID, department.getId());
    }

    @Test
    public void getAllDepartments_ReturningForNoDepartments(){
        assertEquals(0,departmentService.getAllDepartments().size());
    }

    @Test
    public void getAllDepartments_CountAddedDepartments(){
        Department department = setupNewDepartment();
        departmentService.addDepartment(department);
        assertEquals(1,departmentService.getAllDepartments().size());
    }

    @Test
    public void getAllDepartmentMembers_ReturningForNoMembers(){
        Department department = setupNewDepartment();
        departmentService.addDepartment(department);
        assertEquals(0,departmentService.getAllDepartmentMembers(department.getId()).size());
    }

    @Test
    public void getDepartmentById_getSpecificDepartment(){
        Department department      = setupNewDepartment();
        departmentService.addDepartment(department);
        Department foundDepartment = departmentService.getDepartmentById(department.getId());
        assertEquals(department.getId(),foundDepartment.getId());
        assertEquals(department.getName(),foundDepartment.getName());
    }

    @Test
    public void updateDepartment_EditDepartmentName(){
        Department department      = setupNewDepartment();
        String initialName         = department.getName();
        departmentService.addDepartment(department);
        departmentService.updateDepartment(department.getId(), "Cyber Security");
        Department foundDepartment = departmentService.getDepartmentById(department.getId());
        String updatedName         = foundDepartment.getName();
        assertNotEquals(initialName,updatedName);
    }

    @Test
    public void deleteDepartmentById_deleteSpecificDepartment(){
        Department department      = setupNewDepartment();
        departmentService.addDepartment(department);
        departmentService.deleteDepartmentById(department.getId());
        assertEquals(0,departmentService.getAllDepartments().size());
    }

    @Test
    public void deleteDepartments_deleteAvailableDepartments(){
        Department department      = setupNewDepartment();
        departmentService.addDepartment(department);
        departmentService.deleteDepartments();
        assertEquals(0,departmentService.getAllDepartments().size());
    }

    // HELPER METHODS
    public Department setupNewDepartment(){
        return new Department("Business Engagement");
    }
}