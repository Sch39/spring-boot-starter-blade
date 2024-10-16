package dev.sch39.template;

public class Token {
  public enum Type {
    TEXT, VARIABLE, RAW_VARIABLE
  }

  public final Type type;
  public final String content;

  public Token(Type type, String content) {
    this.type = type;
    this.content = content;
  }
}
