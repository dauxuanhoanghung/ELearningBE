package com.dxhh.elearning.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "blog_comment", catalog = "elearning", schema = "elearning")
@Data
public class BlogComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "content")
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @ManyToOne(optional = false)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public BlogComment() {
    }

    public BlogComment(Integer id) {
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
        if (!(object instanceof BlogComment)) {
            return false;
        }
        BlogComment other = (BlogComment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dxhh.elearning.pojos.BlogComment[ id=" + id + " ]";
    }

}
