package csjpabook.csjpashop.domain.item;

import csjpabook.csjpashop.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@DiscriminatorValue("A")
@Getter
@Entity
public class Album extends Item {
    private String artist;
    private String etc;
}
