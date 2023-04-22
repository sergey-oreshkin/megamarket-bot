package group.megamarket.marketservice.mapper;

import group.megamarket.marketservice.dto.OrderRequest;
import group.megamarket.marketservice.dto.OrderResponse;
import group.megamarket.marketservice.entity.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final ModelMapper modelMapper;


    public OrderResponse toOrderResponse(Order order) {
        return Objects.isNull(order) ? null : modelMapper.map(order, OrderResponse.class);
    }


    public Order toOrder(OrderRequest orderRequest) {
        return Objects.isNull(orderRequest) ? null : modelMapper.map(orderRequest, Order.class);
    }

    /*TypeMap<OrderRequest, Order> orderTypeMap

/*private void init(){
    orderTypeMap = modelMapper.createTypeMap(OrderRequest.class, Order.class);
    orderTypeMap.addMapping(OrderRequest::getUserId, Order::setUserId);
    orderTypeMap.addMapping(OrderRequest::getProductId, (order, value) -> order.getOrderProducts().add(new OrderProduct(order, value, orderRequest.getQuantity())));

    TypeMap<Order, OrderResponse> orderResponseTypeMap = modelMapper.createTypeMap(Order.class, OrderResponse.class);
    orderResponseTypeMap.addMapping(Order::getId, OrderResponse::setId);
    orderResponseTypeMap.addMapping(Order::getUserId, OrderResponse::setUserId);
    orderResponseTypeMap.addMapping(Order::getDateCreated, (orderResponse, value) -> orderResponse.setOrderDate(value.toString()));
    orderResponseTypeMap.addMapping(Order::getStatus, OrderResponse::setOrderStatus);
    orderResponseTypeMap.addMapping(Order::getOrderProducts, (orderResponse, value) -> {
        List<ProductDetails> productDetailsList = new ArrayList<>();
        for (OrderProduct orderProduct : value) {
            productDetailsList.add(new ProductDetails(orderProduct.getProduct().getId(), orderProduct.getQuantity()));
        }
        orderResponse.setProductDetails(productDetailsList);
    });

    modelMapper.validate();
}*/

}
