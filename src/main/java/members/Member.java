package members;

import departments.Department;
import departments.DepartmentService;
import org.sql2o.Sql2o;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

public class Member {
    private int id;
    private int departmentId;
    private int roleId;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;

    public Member(String firstName,String lastName,int departmentId,int roleId){
        this.firstName    = firstName.toLowerCase();
        this.lastName     = lastName.toLowerCase();
        this.departmentId = departmentId;
        this.roleId       = roleId;
        this.createdAt    = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return getId() == member.getId() && getDepartmentId() == member.getDepartmentId() && getRoleId() == member.getRoleId() && getFirstName().equals(member.getFirstName()) && getLastName().equals(member.getLastName()) && getCreatedAt().equals(member.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDepartmentId(), getRoleId(), getFirstName(), getLastName(), getCreatedAt());
    }
}