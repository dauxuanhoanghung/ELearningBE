package com.dxhh.elearning.pojos;

import com.dxhh.elearning.enums.LectureType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "lecture", catalog = "elearning", schema = "")
@Data
public class Lecture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Lob
    @Column(name = "content")
    private String content;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private LectureType type;
    @Basic(optional = false)
    @Column(name = "order_index")
    private int orderIndex;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lectureId")
    private Set<Video> videoSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lectureId")
    private Set<LectureComment> lectureCommentSet;
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Section sectionId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lectureId")
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
