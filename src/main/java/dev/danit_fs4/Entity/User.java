package dev.danit_fs4.Entity;

import dev.danit_fs4.DAO.Identifiable;

public class User implements Identifiable {
    private final Integer id;
    private final String name;

    private final String photo;

    public User(Integer id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public Integer id() {
        return id;
    }
}
