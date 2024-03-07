package org.catile.shortme.worker;

import org.catile.shortme.repo.ShortlinkRepository;
import org.hibernate.dialect.PostgreSQLDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.StreamSupport;

@Component
public record RemoveOldLinksWorker(
        ShortlinkRepository repo,
        @Value("${worker.old-links.ttl-value}") long ttlValue,
        @Value("${worker.old-links.ttl-unit}") String ttlUnit
) {
    @Scheduled(cron = "${worker.old-links.cron}")
    void removeOldLinks() {
        var expiredCreatedAt = Date.from(Instant.now().minus(
                ttlValue,
                ChronoUnit.valueOf(ttlUnit.toUpperCase())
        ));
        repo.deleteAll(() -> StreamSupport
                .stream(repo.findAll().spliterator(), false)
                .filter(i -> i.getCreatedAt().before(expiredCreatedAt))
                .iterator()
        );
    }
}
