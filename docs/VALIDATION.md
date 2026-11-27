# RetrySight validation guide

Executable validation for RetrySight Integration (`TIER_3_INTEGRATION`): smoke tests, agent hardening, ClickHouse paths, MCP, and capacity probes.

## Quick start

```bash
./scripts/validation/smoke.sh
./scripts/validation/agent-edge-cases.sh
./scripts/validation/run-all.sh
```

| Variable | Default | Notes |
|----------|---------|-------|
| `MANAGER_URL` | `http://localhost:28475` | |
| `UI_URL` | `http://localhost:8084` | Docker dashboard |
| `APM_EMAIL` / `APM_PASSWORD` | `secadmin@local` / `changeme` | |
| `AGENT_API_KEY` | `dev-agent-key` | |

## Agent hardening

```bash
./scripts/validation/agent-edge-cases.sh
```

## MCP

```bash
cd mcp && npm test
```

## CI

**Release** workflow on every PR — [RELEASE.md](./RELEASE.md) (Helm includes ClickHouse).

```bash
mvn -pl shared/agent-contract,manager/manager-app,agent/agent-app -am test
cd ui/manager-dashboard && npm run build
```

## Manual smoke

1. http://localhost:8084 — login  
2. Cost, reports, MCP-gated features per [OVERVIEW.md](./OVERVIEW.md)  
3. ClickHouse dual-write: see [CLICKHOUSE_ANALYTICS.md](./CLICKHOUSE_ANALYTICS.md)  
4. `./scripts/validation/smoke.sh` with `MANAGER_URL=http://localhost:28475`

## Related docs

- [SECURITY.md](./SECURITY.md)
- [CAPACITY.md](./CAPACITY.md)
