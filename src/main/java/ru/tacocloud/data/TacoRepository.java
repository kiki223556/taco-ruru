package ru.tacocloud.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.tacocloud.model.taco.Taco;

public interface TacoRepository
        extends PagingAndSortingRepository<Taco, Long> {
}
