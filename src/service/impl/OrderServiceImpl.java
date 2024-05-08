package service.impl;

import exception.EcommerceException;
import model.ErrorCode;
import model.Order;
import repository.OrderRepository;
import service.BuyerService;
import service.OrderService;
import service.PincodeServiceabilityService;
import service.ProductService;
import utils.ErrorCodeMap;

public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    ProductService productService;
    PincodeServiceabilityService pincodeServiceabilityService;
    BuyerService buyerService;

    public OrderServiceImpl(
            OrderRepository orderRepository, ProductService productService,
            PincodeServiceabilityService pincodeServiceabilityService,
            BuyerService buyerService){
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.pincodeServiceabilityService = pincodeServiceabilityService;
        this.buyerService = buyerService;
    }
    @Override
    public String addOrder(Order order) throws EcommerceException {
        final String sourcePinCode=productService.getProduct(order.getProductId()).getAddress().getPinCode();
        final String destPinCode=buyerService.getBuyer(order.getBuyerId()).getAddress().getPinCode();

        if(!pincodeServiceabilityService.checkIsSourceAndDestPinCodeMatchesForPaymentType(sourcePinCode,destPinCode,
                order.getPaymentMode())){
            throw new EcommerceException(
                    ErrorCode.PIN_CODE_UNSERVICEABLE, ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.PIN_CODE_UNSERVICEABLE)
            );    }

        if(productService.checkInventory(order.getQuantity(),order.getProductId())){
            Order createdOrder =  orderRepository.createOrder(order);
            return createdOrder.getOrderId();
        }
        throw new EcommerceException(
                ErrorCode.ORDER_CREATION_FAILED, ErrorCodeMap.errorCodeStringHashMap.get(ErrorCode.ORDER_CREATION_FAILED)
        );
    }

    @Override
    public Order getOrder(String orderId) throws EcommerceException {
       return orderRepository.getOrder(orderId);
    }
}
