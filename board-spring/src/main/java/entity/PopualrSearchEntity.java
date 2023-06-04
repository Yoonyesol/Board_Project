package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="PopualrSearch")
@Table(name="PopualrSearch")
public class PopualrSearchEntity {
    @Id
    private String popularTerm;
    private int popularSearchCount;
}