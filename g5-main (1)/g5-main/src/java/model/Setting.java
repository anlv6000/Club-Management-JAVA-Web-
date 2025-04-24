package Model;

public class Setting {
    private int id;
    private String name;
    private String type;
    private String value;
    private int priority;
    private String status;
    private int roleId; // Changed from 'userType' to 'roleId'
    private String description;

    // Constructors
    public Setting() { }

    public Setting(int id, String name, String type, String value, int priority, String status, int roleId, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.priority = priority;
        this.status = status;
        this.roleId = roleId;
        this.description = description;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}