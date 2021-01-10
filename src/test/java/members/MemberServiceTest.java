package members;

import departments.Department;
import departments.DepartmentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class MemberServiceTest {

    private MemberService memberService;
    private Connection connection;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/technest_test";

    @Before
    public void setUp() throws Exception {
        Sql2o sql2o       = new Sql2o(this.DB_URL,null,null);
        memberService     = new MemberService(sql2o);
        connection        = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        memberService.deleteMembers();
        connection.close();
    }

    @Test
    public void addMember_addingSetsId(){
        Member member = setupNewMember();
        int originalID = member.getId();
        memberService.addMember(member);
        assertNotEquals(originalID, member.getId());
    }

    @Test
    public void getAllMembers_ReturningForNoMembers(){
        assertEquals(0,memberService.getAllMembers().size());
    }

    @Test
    public void deleteMembers_deleteAvailableMembers(){
        Member member = setupNewMember();
        memberService.addMember(member);
        memberService.deleteMembers();
        assertEquals(0,memberService.getAllMembers().size());
    }

    public Member setupNewMember(){
        return new Member("Kelvin","Makamu",7);
    }
}