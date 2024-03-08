package org.catile.shortme.repo;

import org.catile.shortme.entity.Shortlink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ShortlinkRepository extends CrudRepository<Shortlink, String> {
    @Query("from Shortlink s where s.createdAt < :date")
    Iterable<Shortlink> findAllWhereCreatedAtLowerThanDate(Date date);
}
