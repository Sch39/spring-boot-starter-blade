package dev.sch39.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TemplateController {
  @GetMapping("/")
  public ModelAndView home() {
    ModelAndView view = new ModelAndView("test1");
    view.addObject("name", "World");
    return view;
  }

}
