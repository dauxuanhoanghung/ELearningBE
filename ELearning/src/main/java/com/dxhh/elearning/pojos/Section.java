package com.dxhh.elearning.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "section", catalog = "elearning", schema = "")
@Data
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "order_index")
    private int orderIndex;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne(optional = false)
    private Course course;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "section")
    private Set<Lecture> lectures;

    public Section() {
    }

    public Section(Integer id) {
        this.id = id;
    }

    public Section(Integer id, String name, int orderIndex) {
        this.id = id;
        this.name = name;
        this.orderIndex = orderIndex;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Section)) {
            return false;
        }
        Section other = (Section) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dxhh.elearning.pojos.Section[ id=" + id + " ]";
    }
    
}
