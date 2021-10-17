package com.dacky.service.dto;

public class NotificationDTO {

    private String notification;
    private String notificationImage;
    private String publicNotificationImage;
    private String publicNotification;

    public NotificationDTO() {}

    public NotificationDTO(String notification, String notificationImage, String publicNotificationImage, String publicNotification) {
        this.notification = notification;
        this.notificationImage = notificationImage;
        this.publicNotificationImage = publicNotificationImage;
        this.publicNotification = publicNotification;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getNotificationImage() {
        return notificationImage;
    }

    public void setNotificationImage(String notificationImage) {
        this.notificationImage = notificationImage;
    }

    public String getPublicNotificationImage() {
        return publicNotificationImage;
    }

    public void setPublicNotificationImage(String publicNotificationImage) {
        this.publicNotificationImage = publicNotificationImage;
    }

    public String getPublicNotification() {
        return publicNotification;
    }

    public void setPublicNotification(String publicNotification) {
        this.publicNotification = publicNotification;
    }
}
