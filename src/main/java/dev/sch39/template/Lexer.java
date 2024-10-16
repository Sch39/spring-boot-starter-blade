package dev.sch39.template;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
  private final String input;
  private int pos = 0;

  public Lexer(String input) {
    this.input = input;
  }

  public List<Token> tokenize() {
    List<Token> tokens = new ArrayList<>();

    while (pos < input.length()) {
      if (input.startsWith("{{", pos)) {
        tokens.add(readVariable());
      } else if (input.startsWith("{!!", pos)) {
        tokens.add(readRawVariable());
      } else {
        int nextVarPos = input.indexOf("{{", pos);
        int nextRawVarPos = input.indexOf("{!!", pos);

        if (nextVarPos == -1 && nextRawVarPos == -1) {
          tokens.add(readText(input.substring(pos)));
          pos = input.length();
        } else {
          int nextTokenPos = Math.min(
              (nextVarPos != -1 ? nextVarPos : input.length()),
              (nextRawVarPos != -1 ? nextRawVarPos : input.length()));
          tokens.add(readText(input.substring(pos, nextTokenPos)));
          pos = nextTokenPos;
        }
      }
    }
    return tokens;
  }

  private Token readVariable() {
    String token = "}}";
    int start = this.pos;
    int end = this.input.indexOf(token, start);
    if (end == -1) {
      throw new IllegalArgumentException("Unclosed variable token '{{' at" + start);
    }
    this.pos = end + token.length();
    String content = this.input.substring(start + token.length(), end).trim();
    return new Token(Token.Type.VARIABLE, content);
  }

  private Token readRawVariable() {
    String token = "!!}";
    int start = this.pos;
    int end = this.input.indexOf(token, start);
    if (end == -1) {
      throw new IllegalArgumentException("Unclosed raw variable token '{!!' at" + start);
    }
    this.pos = end + token.length();
    String content = this.input.substring(start + token.length(), end).trim();
    return new Token(Token.Type.RAW_VARIABLE, content);
  }

  private Token readText(String text) {
    return new Token(Token.Type.TEXT, text);
  }
}
