package com.dxhh.elearning.pojos;

import lombok.Data;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "voucher", catalog = "elearning", schema = "")
@Data
public class Voucher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "discount")
    private Double discount;
    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne
    private Course course;

    public Voucher() {
    }

    public Voucher(Integer id) {
        this.id = id;
    }

    public Voucher(Integer id, String code, Double discount) {
        this.id = id;
        this.code = code;
        this.discount = discount;
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
        if (!(object instanceof Voucher)) {
            return false;
        }
        Voucher other = (Voucher) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dxhh.elearning.pojos.Voucher[ id=" + id + " ]";
    }
    
}
