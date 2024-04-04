package com.dxhh.elearning.pojos;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "refresh_token", schema = "elearning", catalog = "elearning")
@Data
public class RefreshToken {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;
    @Basic
    @Column(name = "token", nullable = false)
    private String token;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public RefreshToken() {}

    public RefreshToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshToken that = (RefreshToken) o;
        return id == that.id && user.getId() == that.user.getId() && Objects.equals(expiryDate, that.expiryDate)
                && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, expiryDate, token, user.getId());
    }

    @Override
    public String toString() {
        return "com.dxhh.elearning.pojos.RefreshToken[ id=" + id + " ]";
    }
}
