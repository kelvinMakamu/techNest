package members;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class MemberTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }



    // HELPER METHOD
    public Member setupNewMember(){
        return new Member("Kelvin","Makamu",7);
    }
}