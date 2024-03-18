package com.dxhh.elearning.pojos;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
public class Progress {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "done", nullable = false)
    private boolean done;
    @Basic
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "lecture_id", referencedColumnName = "id")
    private Lecture lecture;

    public Progress() {}
    public Progress(int id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Progress progress = (Progress) o;
        return id == progress.id && done == progress.done && Objects.equals(user.getId(), progress.user.getId())
                && Objects.equals(lecture.getId(), progress.lecture.getId())
                && Objects.equals(createdDate, progress.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user.getId(), lecture.getId(), done, createdDate);
    }

    @Override
    public String toString() {
        return "com.dxhh.elearning.pojos.Progress[ id=" + id + " ]";
    }
}
