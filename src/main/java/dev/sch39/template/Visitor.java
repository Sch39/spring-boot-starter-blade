package dev.sch39.template;

import java.util.Map;

import org.springframework.web.util.HtmlUtils;

public class Visitor {
  private final Map<String, ?> model;

  public Visitor(Map<String, ?> model) {
    this.model = model;
  }

  public String visit(Token token) {
    switch (token.type) {
      case VARIABLE:
        return this.resolveVariable(token.content, true);
      case RAW_VARIABLE:
        return this.resolveVariable(token.content, false);
      case TEXT:
        return token.content;

      default:
        return token.content;
    }
  }

  private String resolveVariable(String key, boolean escapeHtml) {
    Object value = this.model != null ? this.model.get(key) : null;
    if (value == null)
      return "";
    String stringValue = value.toString();
    return escapeHtml ? HtmlUtils.htmlEscape(stringValue) : stringValue;
  }
}
