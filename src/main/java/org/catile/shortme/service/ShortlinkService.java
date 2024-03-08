package org.catile.shortme.service;

import java.util.Date;
import java.util.Optional;

public interface ShortlinkService {
    Optional<String> findById(String id);

    String create(String url);

    void clearExpiredLinks(Date expirationDate);
}
