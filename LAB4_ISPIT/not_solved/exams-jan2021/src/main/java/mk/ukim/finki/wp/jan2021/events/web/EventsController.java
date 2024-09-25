package mk.ukim.finki.wp.jan2021.events.web;

import mk.ukim.finki.wp.jan2021.events.model.EventType;
import mk.ukim.finki.wp.jan2021.events.service.EventService;

public class EventsController {

    private final EventService eventService;

    public EventsController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * This method should use the "list.html" template to display all events.
     * The method should be mapped on paths '/' and '/events'.
     * The arguments that this method takes are optional and can be 'null'.
     * In the case when the arguments are not passed (both are 'null') all events should be displayed.
     * If one, or both of the arguments are not 'null', the events that are the result of the call
     * to the method 'listEventsWithPriceLessThanAndType' from the EventService should be displayed.
     *
     * @param price
     * @param type
     * @return The view "list.html".
     */
    public String showEvents(Double price, EventType type) {
        if (price == null && type == null) {
            this.eventService.listAllEvents();
        } else {
            this.eventService.listEventsWithPriceLessThanAndType(price, type);
        }
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * The method should be mapped on path '/events/add'.
     *
     * @return The view "form.html".
     */
    public String showAdd() {
        return "";
    }

    /**
     * This method should display the "form.html" template.
     * However, in this case all 'input' elements should be filled with the appropriate value for the event that is updated.
     * The method should be mapped on path '/events/[id]/edit'.
     *
     * @return The view "form.html".
     */
    public String showEdit(Long id) {
        this.eventService.findById(id);
        return "";
    }

    /**
     * This method should create an event given the arguments it takes.
     * The method should be mapped on path '/events'.
     * After the event is created, all events should be displayed.
     *
     * @return The view "list.html".
     */
    public String create(String name, String description, Double price, EventType type, Long location) {
        this.eventService.create(name, description, price, type, location);
        return "";
    }

    /**
     * This method should update an event given the arguments it takes.
     * The method should be mapped on path '/events/[id]'.
     * After the event is updated, all events should be displayed.
     *
     * @return The view "list.html".
     */
    public String update(Long id, String name, String description, Double price, EventType type, Long location) {
        this.eventService.update(id, name, description, price, type, location);
        return "";
    }

    /**
     * This method should delete the event that has the appropriate identifier.
     * The method should be mapped on path '/events/[id]/delete'.
     * After the event is deleted, all events should be displayed.
     *
     * @return The view "list.html".
     */
    public String delete(Long id) {
        this.eventService.delete(id);
        return "";
    }

    /**
     * This method should increase the number of likes of the appropriate event by 1.
     * The method should be mapped on path '/events/[id]/like'.
     * After the operation, all events should be displayed.
     *
     * @return The view "list.html".
     */
    public String like(Long id) {
        this.eventService.like(id);
        return "";
    }
}
