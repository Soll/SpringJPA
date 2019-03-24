package com.art.tacocloud.data;

import com.art.tacocloud.taco.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
