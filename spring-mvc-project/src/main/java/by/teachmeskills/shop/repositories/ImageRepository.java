package by.teachmeskills.shop.repositories;

import by.teachmeskills.shop.entities.Image;

import java.util.List;

public interface ImageRepository extends BaseRepository<Image> {
    Image findById(int id);

    Image findByCategoryId(int id);

    List<Image> findByProductId(int id);


}
