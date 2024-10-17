package dev.sch39.template;

import java.util.ArrayList;
import java.util.List;

import dev.sch39.template.syntaxreader.TokenReader;

import dev.sch39.template.syntaxreader.impl.RawVariableReader;
import dev.sch39.template.syntaxreader.impl.TextReader;
import dev.sch39.template.syntaxreader.impl.VariableReader;

public class Lexer {
  private final String input;
  private int pos = 0;
  private int line = 1;
  private int column = 1;
  private final List<TokenReader> tokenReaders = new ArrayList<>();

  public Lexer(String input) {
    this.input = input;
    tokenReaders.add(new VariableReader());
    tokenReaders.add(new RawVariableReader());
    tokenReaders.add(new TextReader());
  }

  public List<Token> tokenize() {
    List<Token> tokens = new ArrayList<>();
    while (pos < input.length()) {
      boolean matched = false;

      for (TokenReader reader : this.tokenReaders) {
        Token token = reader.readToken(this.input, this.pos, this.line, this.column);
        if (token != null) {
          tokens.add(token);
          // this.pos += reader.getTokenLength(this.input, this.pos);
          updatePosition(reader.getTokenLength(this.input, this.pos));
          matched = true;
          break;
        }
      }

      if (!matched) {
        throw new IllegalArgumentException("Unrecognized token at line " + line + ", column " + column);
      }
    }
    return tokens;
  }

  private void updatePosition(int length) {
    for (int i = 0; i < length; i++) {
      if (this.input.charAt(this.pos + i) == '\n') {
        this.line++;
        this.column = 1; // Reset kolom saat newline ditemukan
      } else {
        this.column++;
      }
    }
    this.pos += length;
  }
}