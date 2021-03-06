package ru.geekbrains.spring.market.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.spring.market.services.ProductService;
import ru.geekbrains.spring.market.soap.products.GetAllProductsResponse;
import ru.geekbrains.spring.market.soap.products.GetProductByIdRequest;
import ru.geekbrains.spring.market.soap.products.GetProductByIdResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.market.ru/spring/ws/products";
    private final ProductService productService;

    /*
        Пример запроса: POST http://localhost:8189/market/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ps="http://www.market.ru/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <ps:getProductByIdRequest>
                    <ps:id>17</ps:id>
                </ps:getProductByIdRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProduct(productService.getById(request.getId()));
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8189/market/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ps="http://www.market.ru/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <ps:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts() {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }

}
