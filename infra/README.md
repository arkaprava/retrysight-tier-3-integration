# Infrastructure (Docker)

Use the **repository root** — [`docker-compose.yml`](../docker-compose.yml), [`docs/DEPLOYMENT.md`](../docs/DEPLOYMENT.md), [`docs/RELEASE.md`](../docs/RELEASE.md).

## Images (tier-integration)

| Service | Default tag |
|---------|-------------|
| postgres | `retrysight/tier-integration-postgres:local` |
| manager | `retrysight/tier-integration-manager:local` |
| frontend | `retrysight/tier-integration-frontend:local` |
| agent | `retrysight/tier-integration-agent:local` |

Also runs **ClickHouse** (`clickhouse/clickhouse-server:24.8-alpine`) — not built from this repo.

Default ports: dashboard **8084**, manager **28475**, Postgres **5434**, ClickHouse HTTP **8124**.

Prefer root [`scripts/`](../scripts/) over legacy `infra/scripts/`.
