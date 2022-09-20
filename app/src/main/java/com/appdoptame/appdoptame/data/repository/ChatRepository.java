package com.appdoptame.appdoptame.data.repository;

import com.appdoptame.appdoptame.data.listener.ChatCreatorListener;
import com.appdoptame.appdoptame.data.listener.CompleteListener;
import com.appdoptame.appdoptame.model.Chat;
import com.appdoptame.appdoptame.model.Message;

public interface ChatRepository {
    void createChat(Chat chat, Message firstMessage, ChatCreatorListener listener);
    void sendMessage(Message message, CompleteListener listener);
}
