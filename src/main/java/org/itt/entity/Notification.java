package org.itt.entity;

import java.sql.Timestamp;

public class Notification {
    private int notificationId;
    private int userId;
    private String message;
    private boolean isRead;
    private Timestamp createdDate;

    public Notification() {}

    public Notification(int notificationId, int userId, String message, boolean isRead, Timestamp createdDate) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.isRead = isRead;
        this.createdDate = createdDate;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationId=" + notificationId +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                ", isRead=" + isRead +
                ", createdDate=" + createdDate +
                '}';
    }
}
