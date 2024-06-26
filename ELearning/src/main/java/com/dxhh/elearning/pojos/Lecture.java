package com.dxhh.elearning.pojos;

import com.dxhh.elearning.enums.LectureType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "lecture", catalog = "elearning", schema = "")
@Data
public class Lecture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "description", length = -1)
    private String description;
    @Column(name = "duration", nullable = false)
    private Integer duration;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private LectureType type;
    @Basic(optional = false)
    @Column(name = "order_index")
    private int orderIndex;
    @Basic(optional = false)
    @Column(name = "video_url")
    private String videoUrl;
    @JsonIgnore
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Section section;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecture")
    private Set<LectureComment> lectureCommentSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecture")
    private Set<UserNote> userNoteSet;

    public Lecture() {
    }

    public Lecture(Integer id) {
        this.id = id;
    }

    public Lecture(Integer id, String title, String content, LectureType type, int orderIndex) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lecture)) {
            return false;
        }
        Lecture other = (Lecture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dxhh.elearning.pojos.Lecture[ id=" + id + " ]";
    }
    
}
