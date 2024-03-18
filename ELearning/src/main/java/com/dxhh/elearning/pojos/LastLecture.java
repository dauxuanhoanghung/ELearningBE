package com.dxhh.elearning.pojos;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "last_lecture", schema = "elearning", catalog = "elearning")
@Data
public class LastLecture {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "lecture_id", referencedColumnName = "id")
    private Lecture lecture;

    public LastLecture() {
    }

    public LastLecture(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LastLecture))
            return false;
        LastLecture other = (LastLecture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dxhh.elearning.pojos.LastLecture[ id=" + id + " ]";
    }
}
