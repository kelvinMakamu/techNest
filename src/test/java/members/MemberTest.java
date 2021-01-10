package members;

import departments.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

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
        assertEquals("kelvin",member.getFirstName());
    }

    @Test
    public void getLastName_InstantiatesWithName_Makamu(){
        Member member = setupNewMember();
        assertEquals("makamu",member.getLastName());
    }

    @Test
    public void getCreatedAt_InstantiatesWithCurrentTimeToday(){
        Member member = setupNewMember();
        assertEquals(LocalDateTime.now().getDayOfWeek(),member.getCreatedAt().getDayOfWeek());
    }
    // HELPER METHOD
    public Member setupNewMember(){
        return new Member("kelvin","makamu",7,1);
    }
}