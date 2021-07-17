package org.mk.dentisteapp.dao;

import org.mk.dentisteapp.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
    Message findFirstById(Long id);
}
