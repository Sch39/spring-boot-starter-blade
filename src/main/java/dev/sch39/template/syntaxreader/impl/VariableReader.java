package dev.sch39.template.syntaxreader.impl;

import dev.sch39.template.Token;
import dev.sch39.template.syntaxreader.TokenReader;

public class VariableReader implements TokenReader {
  private static final String OPEN = "{{";
  private static final String CLOSE = "}}";

  @Override
  public Token readToken(String input, int pos, int line, int column) {
    if (input.startsWith(OPEN, pos)) {
      int end = input.indexOf(CLOSE, pos);
      if (end == -1) {
        throw new IllegalArgumentException("Unclosed variable token '{{' at line " + line + ", column " + column);
      }
      String content = input.substring(pos + OPEN.length(), end).trim();
      return new Token(Token.Type.VARIABLE, content);
    }
    return null;
  }

  @Override
  public int getTokenLength(String input, int pos) {
    int end = input.indexOf(CLOSE, pos);
    return end + CLOSE.length() - pos;
  }

}
