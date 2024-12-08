package ru.sorokinad.dao;

import ru.sorokinad.models.Message;

import java.util.List;

public interface MessageDao {
    void saveMessage(Message message);

    List<Message> getAllMessages();
}