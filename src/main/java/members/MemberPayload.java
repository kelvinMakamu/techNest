package members;

import roles.Role;

import java.util.List;
import java.util.Objects;

public class MemberPayload {

    private int memberId;
    private String firstName;
    private String lastName;
    private int departmentId;
    private String departmentName;
    private String roleName;

    public MemberPayload(int memberId,String firstName,String lastName,int departmentId,
                         String departmentName,String roleName){
        this.memberId       = memberId;
        this.firstName      = firstName;
        this.lastName       = lastName;
        this.departmentId   = departmentId;
        this.departmentName = departmentName;
        this.roleName       = roleName;
    }

    public int getMemberId(){
        return memberId;
    }

    public void setMemberId(int memberId){
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberPayload)) return false;
        MemberPayload that = (MemberPayload) o;
        return getMemberId() == that.getMemberId() && getDepartmentId() == that.getDepartmentId() && getFirstName().equals(that.getFirstName()) && getLastName().equals(that.getLastName()) && getDepartmentName().equals(that.getDepartmentName()) && getRoleName().equals(that.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemberId(), getFirstName(), getLastName(), getDepartmentId(), getDepartmentName(), getRoleName());
    }
}
