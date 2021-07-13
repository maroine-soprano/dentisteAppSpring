package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
