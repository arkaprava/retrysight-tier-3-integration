GRANT ALL PRIVILEGES ON DATABASE agent_retry_integration TO retrysight_integration;
GRANT ALL PRIVILEGES ON SCHEMA public TO retrysight_integration;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO retrysight_integration;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO retrysight_integration;
CREATE EXTENSION IF NOT EXISTS pg_stat_statements;
