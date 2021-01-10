package roles;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

public class Role {
    private int id;
    private String name;
    private LocalDateTime createdAt;

    public Role(String name){
        this.name      = name.toLowerCase();
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getId() == role.getId() && getName().equals(role.getName()) && getCreatedAt().equals(role.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCreatedAt());
    }
}
