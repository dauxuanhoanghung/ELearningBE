package com.dxhh.elearning.pojos;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_note", catalog = "elearning", schema = "")
@Data
public class UserNote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "note_text")
    private String text;
    @Column(name = "note_time")
    private Integer noteTime;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @JoinColumn(name = "lecture_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lecture lecture;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    {
        createdDate = LocalDateTime.now();
    }

    public UserNote() {
    }

    public UserNote(Integer id) {
        this.id = id;
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
        if (!(object instanceof UserNote)) {
            return false;
        }
        UserNote other = (UserNote) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dxhh.elearning.pojos.UserNote[ id=" + id + " ]";
    }
    
}
