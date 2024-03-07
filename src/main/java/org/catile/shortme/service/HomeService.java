package org.catile.shortme.service;

import java.util.Optional;

public interface HomeService {
    Optional<String> findById(String id);

    String create(String url);
}
