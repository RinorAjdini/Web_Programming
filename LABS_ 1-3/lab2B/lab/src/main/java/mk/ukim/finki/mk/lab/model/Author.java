package mk.ukim.finki.mk.lab.model;

import lombok.Data;

@Data
public class Author {
    public Long id;
    public String name;
    public String surname;
    public String biography;

    public Author(String name, String surname, String biography) {
        this.id=(long)(Math.random()*1000);
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }
    @Override
    public String toString(){
        return name+" "+surname;
    }
}
