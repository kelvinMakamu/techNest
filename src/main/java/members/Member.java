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
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;

    String DB_URL = "jdbc:postgresql://localhost:5432/technest";
    Sql2o sql2o   = new Sql2o(DB_URL, null, null);
    DepartmentService departmentService = new DepartmentService(sql2o);

    public Member(String firstName, String lastName,int departmentId){
        this.firstName    = firstName.toLowerCase();
        this.lastName     = lastName.toLowerCase();
        this.departmentId = departmentId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return getId() == member.getId() && getDepartmentId() == member.getDepartmentId() && getFirstName().equals(member.getFirstName()) && getLastName().equals(member.getLastName()) && getCreatedAt().equals(member.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDepartmentId(), getFirstName(), getLastName(), getCreatedAt());
    }
}