package dev.sch39.template;

import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.View;
import org.springframework.web.util.HtmlUtils;

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

      if (model != null && model.size() > 0) {
        content = this.doubleEncodeVariable(content, model);
      }

      response.getWriter().write(content);
    }
  }

  private String doubleEncodeVariable(String templateContent, Map<String, ?> model) {
    for (Entry<String, ?> entry : model.entrySet()) {
      String placeholder = "\\{\\{\\s*" + entry.getKey() + "\\s*\\}\\}";
      templateContent = templateContent.replaceAll(placeholder.trim(),
          entry.getValue() != null ? HtmlUtils.htmlEscape(entry.getValue().toString()) : "");
    }

    return templateContent;
  }

}
