package hello.core.order;

public interface OrderService {

    // 최종 주문 결과를 반환하는 기능
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
