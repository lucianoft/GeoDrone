package br.com.geodrone.utils;


public class Message {
    private final Type type;
    private final String content;
    private final String key;

    public static Message info(String key, String content){
        return new Message(Type.INFO, key, content);
    }

    public static Message warning(String key, String content){
        return new Message(Type.WARNING, key, content);
    }

    public static Message error(String key, String content){
        return new Message(Type.ERROR, key, content);
    }

    public Message(Type type, String key, String content) {
        if(type == null){
            throw new IllegalArgumentException("Tipo da mensagem não pode ser nulo.");
        }

        if(content == null){
            throw new IllegalArgumentException("Conteúdo da mensagem não pode ser vazio.");
        }

        this.key = key;
        this.type = type;
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getKey() {
        return key;
    }

    public enum Type{
        INFO,
        WARNING,
        ERROR
    }


}
