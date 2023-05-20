package dev.danit_fs4.Entity;

import java.util.UUID;

public record Account(Integer id, Integer userId, String email, String password, UUID uid) {
}
