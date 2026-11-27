# RetrySight Integration — deployment

## Stack

From the **repository root**:

```bash
cp .env.example .env
./scripts/build.sh
./scripts/up-with-agent.sh
```

ClickHouse starts automatically (required at this tier). Defaults: `CLICKHOUSE_ENABLED=true`.

## Ports (defaults)

| Service | Port |
|---------|------|
| Frontend | 8084 |
| Manager | 28475 |
| PostgreSQL | 5434 |
| ClickHouse HTTP | 8124 |
| ClickHouse native | 9001 |

## MCP server

Run on the host (from repo root):

```bash
cd mcp
npm ci && npm run build
export MANAGER_URL=http://localhost:28475
export MCP_EMAIL=secadmin@local
export MCP_PASSWORD=changeme
npm run start
```

Configure Cursor/VS Code via [`mcp/ide-config/`](../mcp/ide-config/) examples.

## ClickHouse

See [CLICKHOUSE_ANALYTICS.md](./CLICKHOUSE_ANALYTICS.md). Manager dual-writes when `CLICKHOUSE_ENABLED=true`.

## Fleet agent & release

```bash
./scripts/retrysight-agent install
./scripts/package-agent.sh          # native installers (auto-start hooks included)
./scripts/validation/agent-edge-cases.sh
```

| Package | Auto-start after reboot / login |
|---------|----------------------------------|
| macOS `.pkg` | LaunchAgent `com.retrysight.agent` (`RunAtLoad` + `KeepAlive`) |
| Linux `.deb` | User systemd unit `retrysight-agent.service` (`systemctl --global enable`) |
| Windows `.msi` | Ships `register-autostart.ps1` (run once, or use install-agent-service.ps1) → Scheduled task `RetrySightAgent` (`AtLogOn`) |

Manual / jar installs: `./infra/scripts/install-agent-service.sh` or `infra/windows/install-agent-service.ps1`.

CI / Helm / GHCR: [RELEASE.md](./RELEASE.md) (Helm enables ClickHouse).

## Production

`SPRING_PROFILES_ACTIVE=docker,tier-integration,prod` — rotate secrets, use managed PostgreSQL and ClickHouse clusters. [BYO_RDBMS.md](./BYO_RDBMS.md)
