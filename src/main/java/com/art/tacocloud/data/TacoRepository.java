package com.art.tacocloud.data;

import com.art.tacocloud.taco.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
