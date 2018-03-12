package nl.fontys.kwetter.model.post;

import nl.fontys.kwetter.model.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Tag implements Model {
    @Id
    @GeneratedValue
    private Long Id;
    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
