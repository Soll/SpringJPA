package com.art.tacocloud.temp;

import com.art.tacocloud.taco.Ingredient;
import com.art.tacocloud.taco.Taco;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

@Repository
public class JdbcTacoRepository {

    private JdbcTemplate jdbc;
    private ObjectMapper objectMapper;
    private SimpleJdbcInsert tacoInserter;

    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.tacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco").usingGeneratedKeyColumns("id");
        this.objectMapper = new ObjectMapper();
    }


    public Taco save(Taco taco) {

        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);

        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco) {

        taco.setCreatedAt(new Date());
        Map<String, Object> values = objectMapper.convertValue(taco, Map.class);
        values.put("createdAt", taco.getCreatedAt());
        long tacoId = tacoInserter.executeAndReturnKey(values).longValue();

        return tacoId;
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {

        jdbc.update("insert into Taco_Ingredients (taco, ingredient) values (?, ?)", tacoId, ingredient.getId());
    }

}
