package com.dxhh.elearning.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Min(value = 0)
    @Column(name = "original_amount", nullable = false, precision = 2)
    private BigDecimal originalAmount;
    @Basic
    @Min(value = 0)
    @Column(name = "amount", precision = 2)
    private BigDecimal amount;
    @Basic
    @Column(name = "code")
    private String code;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course course;
    @ManyToOne
    @JoinColumn(name = "payer_id", referencedColumnName = "id")
    private User payer;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    {
        this.createdDate = LocalDateTime.now();
    }

    public Transaction() {
    }

    public Transaction(Integer id) {
        this.id = id;
    }

    public Transaction(Integer id, BigDecimal originalAmount, BigDecimal amount, String code, LocalDateTime createdDate, Course course, User payer, User user) {
        this.id = id;
        this.originalAmount = originalAmount;
        this.amount = amount;
        this.code = code;
        this.createdDate = createdDate;
        this.course = course;
        this.payer = payer;
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dxhh.elearning.pojos.Transaction[ id=" + id + " ]";
    }

}
