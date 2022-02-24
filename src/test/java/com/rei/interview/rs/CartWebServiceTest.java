package com.rei.interview.rs;

import java.math.BigDecimal;

import com.rei.interview.rs.cart.AddItem;
import com.rei.interview.rs.cart.CartDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartWebServiceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn200WhenSendingRequestCart() throws Exception {
        AddItem addItem = new AddItem();
        addItem.setProductId("123456");
        addItem.setQuantity(1);
        addItem.setLocation(Location.SEATTLE);

        ResponseEntity<CartDto> entity = this.testRestTemplate.postForEntity(
                "http://localhost:"+port+"/cart/123456", addItem, CartDto.class);

        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

}
