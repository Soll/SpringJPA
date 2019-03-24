package com.art.tacocloud.data;

import com.art.tacocloud.taco.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
