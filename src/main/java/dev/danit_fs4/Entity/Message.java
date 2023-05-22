package dev.danit_fs4.Entity;

import dev.danit_fs4.DAO.Identifiable;

public class Message implements Identifiable {
    private final Integer id;
    private final String createdAt;
    private final String body;
    private final Integer idSender;
    private final Integer idReceiver;

//    private final String nameSender;
//    private final String nameReceiver;

    public Message(Integer id, String createdAt, String body, Integer idSender, Integer idReceiver) {
        this.id = id;
        this.createdAt = createdAt;
        this.body = body;
        this.idSender = idSender;
        this.idReceiver = idReceiver;
//        this.nameSender = nameSender;
//        this.nameReceiver = nameReceiver;
    }
    public Message( String body, Integer idSender, Integer idReceiver) {
        this.id = null;
        this.createdAt = null;
        this.body = body;
        this.idSender = idSender;
        this.idReceiver = idReceiver;
//        this.nameSender = null;
//        this.nameReceiver = null;
    }

    public Integer getId() {
        return id;
    }

//    public String getNameSender() {
//        return nameSender;
//    }

//    public String getNameReceiver() {
//        return nameReceiver;
//    }

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
