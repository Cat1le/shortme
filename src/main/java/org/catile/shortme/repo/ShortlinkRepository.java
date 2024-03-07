package org.catile.shortme.repo;

import org.catile.shortme.entity.Shortlink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortlinkRepository extends CrudRepository<Shortlink, String> {
}
