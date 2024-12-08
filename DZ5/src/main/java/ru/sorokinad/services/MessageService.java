package ru.sorokinad.services;

import ru.sorokinad.dao.MessageDao;
import ru.sorokinad.dao.MessageDaoImpl;
import ru.sorokinad.models.Message;

import java.time.LocalDateTime;
import java.util.List;

public class MessageService {
    private final MessageDao messageDao = new MessageDaoImpl();
    private final UserService userService = new UserService();

    public void saveMessage(String username, String content) {

        userService.createUserIfNotExists(username);
        Message message = new Message(username, content, LocalDateTime.now());
        messageDao.saveMessage(message);
    }

    public List<Message> getChatHistory() {
        return messageDao.getAllMessages();
    }
}