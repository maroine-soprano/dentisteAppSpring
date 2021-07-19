package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification>findAllByUserIdOrderByIdDesc(Long id);
}
