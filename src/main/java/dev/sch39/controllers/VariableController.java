package dev.sch39.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/variable")
public class VariableController {
  @GetMapping("/escape")
  public ModelAndView variable() {
    ModelAndView view = new ModelAndView("variable");
    view.addObject("name", "<br>world");
    view.addObject("age", "<h1>29</h1>");
    view.addObject("gender", "male");
    view.addObject("unsafeVariable", "<script>alert('XSS');</script>");
    return view;
  }
}
