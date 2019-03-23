package com.art.tacocloud.data;

import com.art.tacocloud.taco.Order;

public interface OrderRepository {
    Order save(Order order);
}
