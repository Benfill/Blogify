package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "articles")
public class Article {

    @Id
    private Long id;
}
