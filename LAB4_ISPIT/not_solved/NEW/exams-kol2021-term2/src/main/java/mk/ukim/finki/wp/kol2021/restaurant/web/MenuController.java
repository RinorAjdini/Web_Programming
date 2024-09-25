package mk.ukim.finki.wp.kol2021.restaurant.web;

import mk.ukim.finki.wp.kol2021.restaurant.model.MenuType;
import mk.ukim.finki.wp.kol2021.restaurant.service.MenuService;

import java.util.List;

public class MenuController {

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }


    public String showMenus(String nameSearch,  MenuType menuType) {
        if (nameSearch == null && menuType == null) {
            service.listAll();
        } else {
            this.service.listMenuItemsByRestaurantNameAndMenuType(nameSearch,  menuType);
        }
        return "";
    }

    public String showAdd() {
        return "";
    }

    public String showEdit(Long id) {
        this.service.findById(id);
        return "";
    }


    public String create(String name, MenuType menuType, List<Long> menuItemIds) {
        this.service.create(name, menuType, menuItemIds);
        return "";
    }

    public String update(Long id, String name, String description, MenuType menuType, List<Long> menuItemIds) {
        this.service.update(id, name, description, menuType, menuItemIds);
        return "";
    }

    public String delete(Long id) {
        this.service.delete(id);
        return "";
    }
}
