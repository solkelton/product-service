package io.training.week5.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.training.week5.model.Product;
import io.training.week5.service.ProductService;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
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

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService;

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
    when(productService.retrieveProduct(1)).thenReturn(product);

    mockMvc.perform(get("/{id}", 1))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.name", Matchers.is(product.getName())))
        .andExpect(jsonPath("$.description", Matchers.is(product.getDescription())));

    verify(productService, times(1)).retrieveProduct(1);
    verifyNoMoreInteractions(productService);
  }

  @Test
  public void testAddProduct_ValidInput_ShouldCreateNewEntry() throws Exception {
    when(productService.addProduct(product)).thenReturn(product);

    mockMvc.perform(post("/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(product)))
        .andExpect(status().isOk());

    verifyNoMoreInteractions(productService);
  }

  @Test
  public void testUpdateProduct_ValidInput_ShouldUpdateEntry() throws Exception {
    Product newProduct = new Product();
    newProduct.setName("wumbo");

    mockMvc.perform(put("/{productId}", 1)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void testRemoveProduct_ValidInput_ShouldDeleteEntry() throws Exception {
    mockMvc.perform(delete("/{productId}", 1)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  private String asJsonString(Product product) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(product);
  }

}