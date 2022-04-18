package jpabook.jpashop.domain.Item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@DiscriminatorValue("M")
@Getter
@Entity
public class Movie extends Item {
    private String director;
    private String actor;
}
