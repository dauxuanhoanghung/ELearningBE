package com.dxhh.elearning.pojos;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "course", catalog = "elearning", schema = "")
@Data
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "background")
    private String background;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Basic(optional = false)
    @Column(name = "creator_id")
    private int creatorId;
    @Column(name = "publish_date")
    private LocalDateTime publishDate;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @OneToMany(mappedBy = "courseId")
    private Set<Voucher> voucherSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Set<Section> sectionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Set<CourseComment> courseCommentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Set<CourseCriteria> courseCriteriaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Set<FavoriteCourse> favoriteCourseSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Set<Transaction> transactionSet;

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(Integer id, String name, int creatorId) {
        this.id = id;
        this.name = name;
        this.creatorId = creatorId;
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
