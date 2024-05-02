//package server.sopt.carrot.config;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//import server.sopt.carrot.constant.CellingStatus;
//import server.sopt.carrot.constant.Place;
//import server.sopt.carrot.dto.customer.CustomerCreate;
//import server.sopt.carrot.entity.Customer;
//import server.sopt.carrot.entity.Product;
//import server.sopt.carrot.repo.CustomerRepository;
//import server.sopt.carrot.repo.OrderRepository;
//import server.sopt.carrot.repo.ProductRepository;
//
//
///**
// * 초기 상태 등록 Config
// */
//@Component
//@RequiredArgsConstructor
////@Profile(value = "!test") // test 에서는 제외
//public class InitializeDefaultConfig implements ApplicationRunner {
//
//    private final CustomerRepository customerRepository;
//    private final OrderRepository orderRepository;
//    private final ProductRepository productRepository;
//
//    /**
//     * 유저등록, note 4개 등록
//     */
////    @Bean
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        Customer customer = Customer.builder()
//                .age(27)
//                .name("member1")
//                .build();
//        Customer customer1 = Customer.builder()
//                .age(27)
//                .name("member1")
//                .build();
//        Customer customer2 = Customer.builder()
//                .age(27)
//                .name("member1")
//                .build();
//        Customer customer3 = Customer.builder()
//                .age(27)
//                .name("member1")
//                .build();
//        Customer customer4 = Customer.builder()
//                .age(27)
//                .name("member1")
//                .build();
//        Customer customer5 = Customer.builder()
//                .age(27)
//                .name("member1")
//                .build();
//        customerRepository.save(customer);
//        customerRepository.save(customer1);
//        customerRepository.save(customer2);
//        customerRepository.save(customer3);
//        customerRepository.save(customer4);
//        customerRepository.save(customer5);
//
//
//        Product product = Product.builder()
//                .customer(customer1)
//                .price(127000)
////                .cellingStatus(CellingStatus.NOT_SOLD)
//                .place(Place.BELLSTREET)
//                .itemName("bike")
//                .itemDescription("이것은 제가 어릴적 할머니께 받은 미국식 바이크입니다. 어릴적 할머니가 미국에서 바이크를 열심히 타셔서 제가 물려받았습니다.")
//                .build();
//        Product product2 = Product.builder()
//                .customer(customer2)
//                .price(127000)
////                .cellingStatus(CellingStatus.NOT_SOLD)
//                .place(Place.CASTLENORTH)
//                .itemName("iphonPro13")
//                .itemDescription("\"새 상품입니다!.")
//                .build();
//        Product product3 = Product.builder()
//                .customer(customer4)
//                .price(123455)
////                .cellingStatus(CellingStatus.NOT_SOLD)
//                .place(Place.BELLSTREET)
//                .itemName("new things!")
//                .itemDescription("아무것도아닙니다..껄껄.")
//                .build();
//        productRepository.save(product);
//        productRepository.save(product2);
//        productRepository.save(product3);
//    }
//}
