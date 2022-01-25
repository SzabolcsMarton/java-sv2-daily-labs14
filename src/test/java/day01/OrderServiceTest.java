package day01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    OrderService orderService;

    @BeforeEach
    void init() {
        orderService = new OrderService();

        Product p1 = new Product("Tv", "IT", 2000);
        Product p2 = new Product("Laptop", "IT", 2400);
        Product p3 = new Product("Phone", "IT", 400);
        Product p4 = new Product("Lord of The Rings", "Book", 20);
        Product p5 = new Product("Harry Potter Collection", "Book", 120);

        Order o1 = new Order("pending", LocalDate.of(2021, 06, 07));
        o1.addProduct(p1);
        o1.addProduct(p2);
        o1.addProduct(p5);

        Order o2 = new Order("on delivery", LocalDate.of(2021, 06, 01));
        o2.addProduct(p3);
        o2.addProduct(p1);
        o2.addProduct(p2);

        Order o3 = new Order("pending", LocalDate.of(2021, 06, 07));
        o3.addProduct(p1);
        o3.addProduct(p2);
        o3.addProduct(p5);

        Order o4 = new Order("on delivery", LocalDate.of(2021, 06, 01));
        o4.addProduct(p3);
        o4.addProduct(p1);
        o4.addProduct(p2);

        Order o5 = new Order("pending", LocalDate.of(2021, 06, 07));
        o5.addProduct(p1);
        o5.addProduct(p2);
        o5.addProduct(p5);
        //add one more
        o5.addProduct(p4);

        orderService.saveOrder(o1);
        orderService.saveOrder(o2);
        orderService.saveOrder(o3);
        orderService.saveOrder(o4);
        orderService.saveOrder(o5);


    }

    @Test
    void getORdersByStatusTest(){
        List<Order> ordersByStatus = orderService.getOrdersByStatus("pending");

        assertEquals(3, ordersByStatus.size());
        assertEquals(3, ordersByStatus.get(0).getProducts().size());
    }

    @Test
    void getNumberOfOrdesrByStatusTest(){
        long result = orderService.getNumberOfOrdesrByStatus("pending");
        long result2 = orderService.getNumberOfOrdesrByStatus("on delivery");

        assertEquals(3, result);
        assertEquals(2,result2);
    }

    @Test
    void getOrdersBetweenTwoDatesTest() {
        List<Order> result = orderService.getOrdersBetweenTwoDates(LocalDate.of(2021,6,5),
                LocalDate.of(2021,6,9));

        assertEquals(3,result.size());
        assertEquals(3, result.get(0).getProducts().size());
    }

    @Test
    void isAnyOrderWithProductsLessThanParamTest(){
        boolean result = orderService.isAnyOrderWithProductsLessThanParam(3);

        assertFalse(result);
    }

    @Test
    void getOrderWithMostProductsTest(){
        Order result = orderService.getOrderWithMostProducts();

        assertEquals(4, result.getProducts().size());
    }

    @Test
    void getOrdersWithGivenCategoryTest(){
        List<Order> result = orderService.getOrdersWithGivenCategory("Book");

        assertEquals(3,result.size());
    }

}