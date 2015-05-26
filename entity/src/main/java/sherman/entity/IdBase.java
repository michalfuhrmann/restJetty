package sherman.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class IdBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    public Integer getId() {
        return id;
    }
}
