package com.staj.talepyonetimi.repository;

import java.util.List;
import java.util.Optional;

import com.staj.talepyonetimi.model.Notification;

public interface NotificationRepository {
    Notification save(Notification notification);
    Optional<Notification> findById(Long id);
    List<Notification> findByReceiverId(Long receiverId);
    List<Notification> findUnreadByReceiverId(Long receiverId);
}
