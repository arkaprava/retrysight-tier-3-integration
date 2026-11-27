# RetrySight Integration — capabilities

**Installation tier:** `TIER_3_INTEGRATION`  
**Spring profile:** `tier-integration`

## Adds on top of Analytics

| Area | Capabilities |
|------|--------------|
| **MCP** | IDE assistant tools via MCP server |
| **SSO tools API** | Source-tool registry API |
| **Contract cost** | Apply approved pricing units to live registry |
| **Git context** | Query + advanced git context |
| **ClickHouse** | Dual-write analytics, cost aggregate reads |
| **OTel** | Claude Cowork log ingest |
| **Observability** | Traces, patterns, eval experiments |
| **Archive** | Network destination support |
| **Collectors** | + Claude Cowork (OTel) |

## UI routes added

`/experiments`, `/traces`, `/patterns`

## Databases

| Store | Role |
|-------|------|
| PostgreSQL (`agent_retry_integration`) | System of record |
| ClickHouse (`agent_retry_integration`) | Token/event analytics at scale |

Schema init: Liquibase (PG) on manager startup; ClickHouse tables via `ClickHouseSchemaInitializer`.

## Still disabled

Push ingest, object storage archive, cold restore, git enterprise redaction.
