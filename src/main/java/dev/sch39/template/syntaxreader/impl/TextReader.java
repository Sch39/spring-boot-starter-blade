package dev.sch39.template.syntaxreader.impl;

import dev.sch39.template.Token;
import dev.sch39.template.syntaxreader.TokenReader;

public class TextReader implements TokenReader {

  @Override
  public Token readToken(String input, int pos, int line, int column) {
    int nextVarPos = input.indexOf("{{", pos);
    int nextRawVarPos = input.indexOf("{!!", pos);

    int nextTokenPos = Math.min(
        (nextVarPos != -1 ? nextVarPos : input.length()),
        (nextRawVarPos != -1 ? nextRawVarPos : input.length()));

    if (nextTokenPos > pos) {
      String text = input.substring(pos, nextTokenPos);
      return new Token(Token.Type.TEXT, text);
    }
    return null;
  }

  @Override
  public int getTokenLength(String input, int pos) {
    int nextVarPos = input.indexOf("{{", pos);
    int nextRawVarPos = input.indexOf("{!!", pos);

    int nextTokenPos = Math.min(
        (nextVarPos != -1 ? nextVarPos : input.length()),
        (nextRawVarPos != -1 ? nextRawVarPos : input.length()));
    return nextTokenPos - pos;
  }

}
