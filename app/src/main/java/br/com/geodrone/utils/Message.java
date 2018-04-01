package br.com.geodrone.utils;


public class Message {
    private final Type type;
    private final String content;

    public static Message info(String content){
        return new Message(Type.INFO, content);
    }

    public static Message warning(String content){
        return new Message(Type.WARNING, content);
    }

    public static Message error(String content){
        return new Message(Type.ERROR, content);
    }

    public Message(Type type, String content) {
        if(type == null){
            throw new IllegalArgumentException("Tipo da mensagem não pode ser nulo.");
        }

        if(content == null){
            throw new IllegalArgumentException("Conteúdo da mensagem não pode ser vazio.");
        }

        this.type = type;
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public enum Type{
        INFO,
        WARNING,
        ERROR
    }


}
