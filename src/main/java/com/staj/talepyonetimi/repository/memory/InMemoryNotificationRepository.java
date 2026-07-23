package com.staj.talepyonetimi.repository.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.staj.talepyonetimi.model.Notification;
import com.staj.talepyonetimi.repository.NotificationRepository;
import com.staj.talepyonetimi.util.IdGenerator;

public class InMemoryNotificationRepository implements NotificationRepository {
    private final Map<String, Notification> storage = new HashMap<>();
    private final IdGenerator idGenerator = new IdGenerator("N");

    @Override
    public Notification save(Notification notification) {
        if (notification == null) {
            return null;
        }
        if (notification.getId() == null) {
            notification.setId(idGenerator.nextId());
        }

        this.storage.put(notification.getId(), notification);
        return notification;
    }
    
    @Override
    public Optional<Notification> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }
    
    @Override
    public List<Notification> findByReceiverId(String receiverId) {
        List<Notification> result = new ArrayList<>();
        for (Notification values : storage.values()) {
            if (values != null && values.belongsTo(receiverId)) {
                result.add(values);
            }
        }
        return result;
    }
    
    @Override
    public List<Notification> findUnreadByReceiverId(String receiverId) {
        List<Notification> result = new ArrayList<>();
        for (Notification values : storage.values()) {
            if (values != null && values.belongsTo(receiverId) && !values.isRead()) {
                result.add(values);
            }
        }
        return result;
    }
}
