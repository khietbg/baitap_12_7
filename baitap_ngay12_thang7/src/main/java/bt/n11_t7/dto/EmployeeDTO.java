package bt.n11_t7.dto;

import java.util.List;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String departmentName;
    private List<String> roles;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String name, String email, String departmentName, List<String> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentName = departmentName;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
