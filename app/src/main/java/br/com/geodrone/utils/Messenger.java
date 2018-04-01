package br.com.geodrone.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernandes on 01/04/2018.
 */

public class Messenger {

    private boolean isError = false;
    List<Message> messages = null;

    public void addError(String message){
        if (messages == null){
            messages = new ArrayList<>();
        }
        messages.add(Message.error(message));
        isError = true;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public boolean isError(){
        return isError;
    }
}
