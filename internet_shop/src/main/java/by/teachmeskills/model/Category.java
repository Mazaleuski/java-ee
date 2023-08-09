package by.teachmeskills.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
@Builder
public class Category {
    private String id;
    private String name;
    private String imageName;
    private List<Product> productList;
}
