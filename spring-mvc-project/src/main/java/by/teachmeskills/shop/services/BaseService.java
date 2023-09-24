package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.BaseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    ModelAndView create(T entity);

    List<T> read();

    T update(T entity);

    void delete(int id);
}
