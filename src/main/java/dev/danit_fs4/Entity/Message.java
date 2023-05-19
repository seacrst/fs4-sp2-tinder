package dev.danit_fs4.Entity;

import dev.danit_fs4.DAO.Identifiable;

public class Message implements Identifiable {
    private final Integer id;
    private final String createdAt;
    private final Integer idSender;
    private final Integer idReceiver;
    private final String body;
    private final String nameSender;
    private final String nameReceiver;

    public Message(Integer id, String createdAt, String body, Integer idSender, Integer idReceiver, String nameSender, String nameReceiver) {
        this.id = id;
        this.createdAt = createdAt;
        this.body = body;
        this.idSender = idSender;
        this.idReceiver = idReceiver;
        this.nameSender = nameSender;
        this.nameReceiver = nameReceiver;
    }

    public Integer getId() {
        return id;
    }

    public String getNameSender() {
        return nameSender;
    }

    public String getNameReceiver() {
        return nameReceiver;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Integer getIdSender() {
        return idSender;
    }

    public Integer getIdReceiver() {
        return idReceiver;
    }

    public String getBody() {
        return body;
    }

    @Override
    public Integer id() {
        return getId();
    }
}
