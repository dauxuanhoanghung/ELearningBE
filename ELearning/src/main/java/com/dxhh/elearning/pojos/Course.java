package com.dxhh.elearning.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "course", catalog = "elearning", schema = "")
@Data
public class Course implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    private String name;
    @Lob
    private String description;
    private String subtitle;
    private String background;
    private String slug;
    @Min(value=0)
    private Double price;
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User creator;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "publish_date")
    private LocalDateTime publishDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<Voucher> vouchers;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<Section> sections;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<CourseComment> courseComments;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<CourseCriteria> courseCriterias;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<FavoriteCourse> favoriteCourses;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<Transaction> transactions;

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dxhh.elearning.pojos.Course[ id=" + id + " ]";
    }

}
