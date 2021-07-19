package org.mk.dentisteapp.filter;

import org.mk.dentisteapp.entities.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification>getAll();
    Notification add(Notification notification);
    List<Notification> getAllByUser(Long userId);
}
