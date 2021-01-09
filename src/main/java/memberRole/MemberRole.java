package components.memberRole;

import java.time.LocalDateTime;
import java.util.Objects;

public class MemberRole {
    private int id;
    private int memberId;
    private int roleId;
    private LocalDateTime createdAt;

    public MemberRole(int memberId, int roleId){
        this.memberId  = memberId;
        this.roleId    = roleId;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
        if (!(o instanceof MemberRole)) return false;
        MemberRole that = (MemberRole) o;
        return getId() == that.getId() && getMemberId() == that.getMemberId() && getRoleId() == that.getRoleId() && getCreatedAt().equals(that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMemberId(), getRoleId(), getCreatedAt());
    }
}
