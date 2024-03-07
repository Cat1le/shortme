package org.catile.shortme.service;

import org.catile.shortme.entity.Shortlink;
import org.catile.shortme.repo.ShortlinkRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public record HomeServiceImpl(ShortlinkRepository repo) implements HomeService {
    private static final int MAX_ID_VALUE = 1048576;
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public Optional<String> findById(String id) {
        return repo.findById(id).map(Shortlink::getUrl);
    }

    @Override
    public String create(String url) {
        var link = new Shortlink(createId(), url);
        repo.save(link);
        return link.getId();
    }

    private String doCreateId() {
        var nextInt = RANDOM.nextInt(MAX_ID_VALUE);
        var string = Integer.toHexString(nextInt);
        return "0".repeat(string.length() - 5) + string;
    }

    private String createId() {
        String id;
        do id = doCreateId();
        while (repo.existsById(id));
        return id;
    }
}
