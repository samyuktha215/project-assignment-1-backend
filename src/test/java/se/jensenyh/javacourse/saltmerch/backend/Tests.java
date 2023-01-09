package se.jensenyh.javacourse.saltmerch.backend;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import se.jensenyh.javacourse.saltmerch.backend.model.Product;
import se.jensenyh.javacourse.saltmerch.backend.repository.ProductRepository;
import se.jensenyh.javacourse.saltmerch.backend.service.ProductService;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Tests {
    @MockBean
    ProductRepository mockRepo;
    @Autowired
    ProductService service;
    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    int port;
    @Test
    public void httpGetProductsReturnsProduct() {
        Mockito.when(mockRepo.getEntireProduct(2)).thenReturn(new Product(2,"hats","Some Hat 2","This is some hat 2","previewImage"));
        Product product=testRestTemplate.getForObject("http://localhost:"+ port +"api/v1/products/1",Product.class,"");
        System.out.println("product.id="+product==null?null:product.getId());
        assertThat(product.getCategory(),equals());
    }
    @Test
    public void givenRepoGetEntireProductIsMocked_whenServiceGetProduct_thenProductIsReturned(){
        Mockito.when(mockRepo.getEntireProduct(2)).thenReturn(new Product(2,"hats","Some Hat 2","This is some hat 2","preview Image"));
        Product product=service.getEntireProduct(2);
        assertThat(product.getCategory(),equals());
    }
}
