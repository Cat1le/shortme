package org.catile.shortme.worker;

import org.catile.shortme.service.ShortlinkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Date;

@Component
public class RemoveOldLinksWorker {
    ShortlinkService service;
    TemporalAmount temporalAmount;

    public RemoveOldLinksWorker(
            ShortlinkService service,
            @Value("${worker.old-links.ttl}") String ttl
    ) {
        this.service = service;
        var delim = ttl.indexOf(' ');
        var amount = Long.parseLong(ttl.substring(0, delim));
        var unitString = ttl.substring(delim + 1);
        var unit = ChronoUnit.valueOf((unitString.endsWith("s") ? unitString : unitString + "s").toUpperCase());
        temporalAmount = Duration.of(amount, unit);
    }

    @Scheduled(cron = "${worker.old-links.cron}")
    void removeOldLinks() {
        var expirationDate = Date.from(Instant.now().minus(temporalAmount));
        service.clearExpiredLinks(expirationDate);
    }
}
