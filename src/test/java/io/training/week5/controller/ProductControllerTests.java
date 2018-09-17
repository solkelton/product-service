package io.training.week5.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.training.week5.model.Product;
import io.training.week5.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(secure=false)
public class ProductControllerTests {

  @Autowired private MockMvc mockMvc;

  @MockBean private ProductService productService;

  private Product product;

  @Before
  public void setUpProduct() {
    product = new Product();
    product.setId(1);
    product.setDescription("This is a product");
    product.setImage("product/image");
    product.setName("widget");
    product.setPrice(20.0);
  }

  @Test
  public void testRetrieveProduct_ValidInput_ShouldReturnFoundEntry() throws Exception {
    when(productService.retrieveProduct(1)).thenReturn(java.util.Optional.ofNullable(product));

    mockMvc.perform(get("/products/{id}", 1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.name", Matchers.is(product.getName())))
        .andExpect(jsonPath("$.description", Matchers.is(product.getDescription())));

    verify(productService, times(1)).retrieveProduct(1);
    verifyNoMoreInteractions(productService);
  }

}
