package dev.sch39.template;

import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Map.Entry;

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

      if (model != null && model.size() > 0) {
        content = this.replaceVariables(content, model);
      }

      response.getWriter().write(content);
    }
  }

  // public String renderTemplate(String templatePath, Map<String, Object> model)
  // throws IOException {
  // // Baca file template dari path
  // Path path = Paths.get(templatePath);
  // String templateContent = Files.readString(path);

  // // Gantikan variabel dengan nilai dari model
  // templateContent = replaceVariables(templateContent, model);

  // // Proses kontrol alur (if, foreach, dll)
  // templateContent = processControlFlow(templateContent, model);

  // return templateContent;
  // }

  private String replaceVariables(String templateContent, Map<String, ?> model) {
    for (Entry<String, ?> entry : model.entrySet()) {
      String placeholder = "\\{\\{\\s*" + entry.getKey() + "\\s*\\}\\}";
      templateContent = templateContent.replaceAll(placeholder.trim(),
          entry.getValue() != null ? entry.getValue().toString() : "");
    }
    return templateContent;
  }

  // private String processControlFlow(String templateContent, Map<String, Object>
  // model) {
  // templateContent = processIfStatements(templateContent, model);
  // templateContent = processForeachStatements(templateContent, model);
  // return templateContent;
  // }

  // private String processIfStatements(String templateContent, Map<String,
  // Object> model) {
  // int ifStart;
  // while ((ifStart = templateContent.indexOf("@if")) != -1) {
  // int ifEnd = templateContent.indexOf("@endif", ifStart);
  // String condition = templateContent.substring(ifStart + 3, ifEnd).trim();

  // boolean conditionMet = evaluateCondition(condition, model);
  // String block = templateContent.substring(ifStart, ifEnd + "@endif".length());
  // String replacement = conditionMet ? block.replace("@if " + condition,
  // "").replace("@endif", "") : "";

  // templateContent = templateContent.replace(block, replacement);
  // }
  // return templateContent;
  // }

  // private boolean evaluateCondition(String condition, Map<String, Object>
  // model) {
  // String[] parts = condition.split("==");
  // if (parts.length == 2) {
  // String varName = parts[0].trim();
  // String expectedValue = parts[1].trim().replaceAll("^['\"]|['\"]$", "");
  // Object varValue = model.get(varName);
  // return varValue != null && varValue.toString().equals(expectedValue);
  // }
  // return false;
  // }

  // private String processForeachStatements(String templateContent, Map<String,
  // Object> model) {
  // int foreachStart;
  // while ((foreachStart = templateContent.indexOf("@foreach")) != -1) {
  // int foreachEnd = templateContent.indexOf("@endforeach", foreachStart);
  // String loopData = templateContent.substring(foreachStart + 8,
  // foreachEnd).trim();
  // String block = templateContent.substring(foreachStart, foreachEnd +
  // "@endforeach".length());

  // Object loopItems = model.get(loopData);
  // StringBuilder replacement = new StringBuilder();

  // if (loopItems instanceof Iterable) {
  // for (Object item : (Iterable<?>) loopItems) {
  // String itemContent = block.replace("@foreach " + loopData,
  // "").replace("@endforeach", "")
  // .replace("{{ item }}", item.toString());
  // replacement.append(itemContent);
  // }
  // }

  // templateContent = templateContent.replace(block, replacement.toString());
  // }
  // return templateContent;
  // }

}
