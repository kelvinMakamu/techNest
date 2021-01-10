package members;

import departments.Department;
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
    public void getAllMembers_CountAddedMembers(){
        Member member = setupNewMember();
        memberService.addMember(member);
        assertEquals(1,memberService.getAllMembers().size());
    }

    @Test
    public void getMemberById_getSpecificMember(){
        Member member = setupNewMember();
        memberService.addMember(member);
        Member foundMember = memberService.getMemberById(member.getId());
        assertEquals(member.getId(),foundMember.getId());
        assertEquals(member.getFirstName(),foundMember.getFirstName());
        assertEquals(member.getLastName(),foundMember.getLastName());
    }

    @Test
    public void updateMember_EditMemberDetails(){
        Member member            = setupNewMember();
        String originalFirstName = member.getFirstName();
        String originalLastName  = member.getLastName();
        memberService.addMember(member);
        memberService.updateMember(member.getId(), "Justin","Nyamohanga",7,1);
        Member foundMember      = memberService.getMemberById(member.getId());
        String updatedFirstName = foundMember.getFirstName();
        String updatedLastName = foundMember.getLastName();
        assertNotEquals(originalFirstName,updatedFirstName);
        assertNotEquals(originalLastName,updatedLastName);
    }

    @Test
    public void deleteMemberById_deleteSpecificMember(){
        Member member = setupNewMember();
        memberService.addMember(member);
        memberService.deleteMemberById(member.getId());
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
        return new Member("Kelvin","Makamu",7,1);
    }
}