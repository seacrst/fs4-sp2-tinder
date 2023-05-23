package dev.danit_fs4.Entity;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    final private List<Message> history;
    final private int hostId;
    final private int guestId;
    final private String hostName;
    final private String guestName;

    public Chat(List<Message> history, int hostId, int guestId, String hostName, String guestName) {
        this.history = history;
        this.hostId = hostId;
        this.guestId = guestId;
        this.hostName = hostName;
        this.guestName = guestName;
    }

    public Chat(int hostId, int guestId, String hostName, String guestName) {
        history = new ArrayList<>();
        this.hostId = hostId;
        this.guestId = guestId;
        this.hostName = hostName;
        this.guestName = guestName;
    }

    public List<Message> getHistory() {
        return history;
    }

    public int getHostId() {
        return hostId;
    }

    public int getGuestId() {
        return guestId;
    }

    public String getHostName() {
        return hostName;
    }

    public String getGuestName() {
        return guestName;
    }
}
