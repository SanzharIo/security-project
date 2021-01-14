package kz.project.demo.model.audit;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AuditModel implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @PrePersist
    private void prePersisty(){
        if (this.createdAt == null){
            createdAt= new Date();
        }
    }

    @PreUpdate
    private void preUpdate(){
        this.updatedAt = new Date();
    }

    @PreRemove
    private void preRemove(){
        if (this.deletedAt == null){
            this.deletedAt = new Date();
        }
    }

}
