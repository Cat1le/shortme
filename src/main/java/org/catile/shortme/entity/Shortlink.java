package org.catile.shortme.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "shortlink")
@Data
@NoArgsConstructor
public class Shortlink {
    @Id
    private String id;

    private String url;

    private Date createdAt;

    @PrePersist
    void prePersist() {
        createdAt = new Date();
    }
}
