package org.catile.shortme.worker;

import org.catile.shortme.repo.ShortlinkRepository;
import org.hibernate.dialect.PostgreSQLDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
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
    private static final Logger log = LoggerFactory.getLogger(RemoveOldLinksWorker.class);

    @Scheduled(cron = "${worker.old-links.cron}")
    void removeOldLinks() {
        var expiredCreatedAt = Date.from(Instant.now().minus(
                ttlValue,
                ChronoUnit.valueOf(ttlUnit.toUpperCase())
        ));
        log.info("Start cleaning all entries that earlier than {}", expiredCreatedAt);
        repo.deleteAll(() -> StreamSupport
                .stream(repo.findAll().spliterator(), false)
                .filter(i -> i.getCreatedAt().before(expiredCreatedAt))
                .peek(i -> log.info("Cleaning {} - {}", i.getId(), i.getUrl()))
                .iterator()
        );
    }
}
