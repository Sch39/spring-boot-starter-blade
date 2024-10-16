package dev.sch39.template;

import java.io.File;
import java.util.Locale;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

@Component
public class BladeViewResolver implements ViewResolver {
  private final String templateDirectory = "src/main/resources/templates/";
  private final String fileExtension = ".blade.html";

  @Override
  public View resolveViewName(@NonNull String viewName, @NonNull Locale locale) throws Exception {
    String fullPath = templateDirectory + viewName + fileExtension;
    File file = new File(fullPath);
    if (!file.exists()) {
      throw new IllegalArgumentException("View file not found: " + fullPath);
    }
    return new BladeTemplateEngine(fullPath);
  }

}
