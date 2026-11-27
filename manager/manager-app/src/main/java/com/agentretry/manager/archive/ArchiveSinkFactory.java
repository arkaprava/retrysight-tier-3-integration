/*
 * Copyright (c) 2026 Arka Prava Ghosh
 * SPDX-License-Identifier: Apache-2.0
 */

package com.agentretry.manager.archive;

import com.agentretry.manager.domain.ArchivePolicyEntity;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class ArchiveSinkFactory {

    public ArchiveSink create(ArchivePolicyEntity policy) {
        ArchiveDestinationType type = ArchiveDestinationType.valueOf(policy.getDestinationType());
        return switch (type) {
            case LOCAL_FS -> new FileSystemArchiveSink(
                    ArchiveDestinationType.LOCAL_FS,
                    expandPath(policy.getLocalPath() != null ? policy.getLocalPath() : "~/.agent-retry/archive")
            );
            case NETWORK_FS -> {
                String path = policy.getNetworkPath();
                if (path == null || path.isBlank()) {
                    throw new IllegalStateException("networkPath is required for NETWORK_FS destination");
                }
                yield new FileSystemArchiveSink(ArchiveDestinationType.NETWORK_FS, expandPath(path));
            }
            case S3 -> throw new UnsupportedOperationException(
                    "Object storage archive requires RetrySight Cloud (TIER_4)");
        };
    }

    static Path expandPath(String path) {
        String expanded = path.trim();
        if (expanded.startsWith("~/")) {
            expanded = System.getProperty("user.home") + expanded.substring(1);
        } else if (expanded.equals("~")) {
            expanded = System.getProperty("user.home");
        }
        expanded = expanded.replace("${user.home}", System.getProperty("user.home"));
        return Path.of(expanded);
    }

    private static String env(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }
        return System.getenv(name);
    }
}
