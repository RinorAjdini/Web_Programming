package mk.ukim.finki.wp.jan2021.events.model;

public class EventLocation {

    public EventLocation() {
    }

    public EventLocation(String name) {
        this.name = name;
    }

    private Long id;

    private String name;

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
}
