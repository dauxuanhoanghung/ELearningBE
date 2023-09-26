package com.dxhh.elearning.pojos;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "user", catalog = "elearning", schema = "")
@Data
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    private Set<Course> courses;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<LecturerRegistration> lecturerRegistrationSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<CourseComment> courseCommentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<Blog> blogSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<BlogComment> blogCommentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<LectureComment> lectureCommentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<FavoriteCourse> favoriteCourseSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserNote> userNoteSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Transaction> transactionSet;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dxhh.elearning.pojos.User[ id=" + id + " ]";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
