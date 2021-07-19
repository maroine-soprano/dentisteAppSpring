package org.mk.dentisteapp.services;

import org.mk.dentisteapp.dao.NotificationRepository;
import org.mk.dentisteapp.dao.UserRepository;
import org.mk.dentisteapp.entities.Notification;
import org.mk.dentisteapp.filter.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification add(Notification notification) {
        notification.setUser(userRepository.findFirstByEmail(notification.getUser_id()));
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllByUser(Long userId) {
        return notificationRepository.findAllByUserIdOrderByIdDesc(userId);
    }
}
