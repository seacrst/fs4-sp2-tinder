package dev.danit_fs4.entities;

import dev.danit_fs4.dao.Identifiable;


public record Account(Integer id, String email, String password, String uuid) implements Identifiable {
}
