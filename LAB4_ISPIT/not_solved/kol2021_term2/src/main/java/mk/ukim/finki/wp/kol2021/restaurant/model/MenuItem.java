package mk.ukim.finki.wp.kol2021.restaurant.model;

public class MenuItem {

    private Long id;

    private String name;

    private String description;

    public MenuItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public MenuItem() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
