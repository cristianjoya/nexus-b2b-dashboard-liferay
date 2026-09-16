# Objects exports

Definiciones exportadas desde Liferay DXP 2026.Q2.6 (schema + Object Actions; sin filas de datos).

## Import order

1. Picklists (`picklist-NEXUS_*.json`)
2. Objects: `FinAccount` → `FinTransaction` → `FinCreditApplication`

Tras importar Objects, verificar en UI que las 6 acciones Groovy usan el script de [`../actions/recalculate-account-balance.groovy`](../actions/recalculate-account-balance.groovy) y que el delete de crédito es **On After Delete** (no Update).

## Snapshot

| Artifact | ERC | Scope | REST |
|----------|-----|-------|------|
| Fin Account | `FIN_ACCOUNT` | site | `/o/c/finaccounts` |
| Fin Transaction | `FIN_TRANSACTION` | site | `/o/c/fintransactions` |
| Fin Credit Application | `FIN_CREDIT_APPLICATION` | site | `/o/c/fincreditapplications` |

Relationships (parent `FinAccount`): `finAccount` → Transaction, `finAccounts` → Credit · deletion `prevent`.

Business status fields: `nexusStatus`, `nexusTransactionStatus`, `nexusCreditStatus`.

Guides: [PHASE1](../PHASE1-GUIDE.md) · [PHASE2](../PHASE2-GUIDE.md) · [PHASE3](../PHASE3-GUIDE.md).
