package dev.sch39.template;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.View;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BladeTemplateEngine implements View {
  private final String viewPath;

  public BladeTemplateEngine(String viewPath) {
    this.viewPath = viewPath;
  }

  @Override
  public String getContentType() {
    return "text/html";
  }

  @Override
  public void render(@Nullable Map<String, ?> model, @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response)
      throws Exception {
    File file = new File(viewPath);
    try (FileReader reader = new FileReader(file)) {
      char[] buffer = new char[(int) file.length()];
      reader.read(buffer);
      String content = new String(buffer);

      try {
        Lexer lexer = new Lexer(content);
        List<Token> tokens = lexer.tokenize();
        Visitor visitor = new Visitor(model);
        StringBuilder output = new StringBuilder();
        for (Token token : tokens) {
          output.append(visitor
              .visit(token));
        }
        response.getWriter().write(output.toString());

      } catch (IllegalArgumentException e) {
        response.getWriter().write("<h1>Error in template syntax: " + e.getMessage() + "</h1>");
      }
    }
  }

}
