package members;

import departments.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void NewMember_InstantiatesCorrectly_true(){
        Member member = setupNewMember();
        assertEquals(true, member instanceof Member);
    }

    @Test
    public void getFirstName_InstantiatesWithName_Kelvin(){
        Member member = setupNewMember();
        assertEquals("Kelvin",member.getFirstName());
    }

    @Test
    public void getLastName_InstantiatesWithName_Makamu(){
        Member member = setupNewMember();
        assertEquals("Makamu",member.getLastName());
    }


    // HELPER METHOD
    public Member setupNewMember(){
        return new Member("Kelvin","Makamu",7);
    }
}