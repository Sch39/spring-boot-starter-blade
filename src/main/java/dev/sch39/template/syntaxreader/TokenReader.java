package dev.sch39.template.syntaxreader;

import dev.sch39.template.Token;

public interface TokenReader {
  Token readToken(String input, int pos, int line, int column);

  int getTokenLength(String input, int pos);
}
