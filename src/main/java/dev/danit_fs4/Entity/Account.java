package dev.danit_fs4.Entity;

import dev.danit_fs4.DAO.Identifiable;


public record Account(Integer id, String email, String password, String uuid) implements Identifiable {
}
