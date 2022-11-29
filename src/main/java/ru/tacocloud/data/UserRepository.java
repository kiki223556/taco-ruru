package ru.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import ru.tacocloud.model.auth.User;

// 透過使用者名稱查找資料
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
