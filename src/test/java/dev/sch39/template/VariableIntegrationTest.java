package dev.sch39.template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
public class VariableIntegrationTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testEscapeHtml() throws Exception {
    mockMvc.perform(get("/variable/escape"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Hello: &lt;br&gt;world!")))
        .andExpect(content().string(containsString("Age: &lt;h1&gt;29&lt;/h1&gt;")))
        .andExpect(content().string(containsString("Gender: male")))
        .andExpect(content().string(containsString("&lt;script&gt;alert(&#39;XSS&#39;);&lt;/script&gt;")));
  }

  @Test
  public void testNonEscapeHtml() throws Exception {
    mockMvc.perform(get("/variable/non-escape"))
        .andExpect(content().string(containsString("Hello: <br>world!")))
        .andExpect(content().string(containsString("Age: <h1>29</h1>")))
        .andExpect(content().string(containsString("Gender: male")))
        .andExpect(content().string(containsString("<script>alert('XSS');</script>")));
  }

  @Test
  public void testUnclosedEscapeHtml() throws Exception {
    mockMvc.perform(get("/variable/unclosed-escape"))
        .andExpect(content()
            .string(containsString("Error in template syntax: Unclosed variable token '{{' at line 2, column 1")));
  }
}
