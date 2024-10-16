package dev.sch39.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TemplateController {
  @GetMapping("/")
  public ModelAndView home() {
    ModelAndView view = new ModelAndView("variable");
    view.addObject("name", "World");
    view.addObject("age", 25);
    return view;
  }
}
