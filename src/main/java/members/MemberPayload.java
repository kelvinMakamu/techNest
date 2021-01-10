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
    private List<Role> roleList;

    public MemberPayload(int memberId,String firstName,String lastName,int departmentId,String departmentName){
        this.memberId       = memberId;
        this.firstName      = firstName;
        this.lastName       = lastName;
        this.departmentId   = departmentId;
        this.departmentName = departmentName;
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

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberPayload)) return false;
        MemberPayload that = (MemberPayload) o;
        return getDepartmentId() == that.getDepartmentId() && getFirstName().equals(that.getFirstName()) && getLastName().equals(that.getLastName()) && getDepartmentName().equals(that.getDepartmentName()) && getRoleList().equals(that.getRoleList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getDepartmentId(), getDepartmentName(), getRoleList());
    }
}
