# Fase 3 — React CE → Objects

El dashboard React usa solo **Objects headless** para el dominio. Sin Service Builder ni REST Builder custom.

## Arquitectura

| Capacidad | Fuente |
|-----------|--------|
| Cuentas / movimientos / créditos | `/o/c/finaccounts\|fintransactions\|fincreditapplications` + `/scopes/{siteGroupId}` |
| Matriz UI (Manager / User) | Roles de sitio (JSONWS) + fallback actions Objects |
| Usuarios B2B asignables | JSONWS |
| Lookup BIN | Heurística local en el CE |

## Mapeo FE ↔ Objects

| UI / tipo FE | Campo Object |
|--------------|--------------|
| `accountId` | `id` (Fin Account) |
| `status` (cuenta) | `nexusStatus` |
| `transactionId` | `id` (Fin Transaction) |
| `status` (tx) | `nexusTransactionStatus` |
| `accountId` en tx | `r_finAccount_c_finAccountId` |
| `creditAppId` | `id` (Fin Credit Application) |
| `status` (crédito) | `nexusCreditStatus` |
| `accountId` en crédito | `r_finAccounts_c_finAccountId` |

B2B User: el CE fuerza `ownerUserId` del usuario actual. Updates usan **PATCH** para no pisar `balance`.

## Deploy

```bash
./gradlew :client-extensions:fin-services-dashboard:deploy
```

Si el bundle aún tiene JARs antiguos `fin-services*` / `fin-services-rest*`, elimínalos de `osgi/modules` y reinicia. Este repo no los incluye.

Abre el dashboard **desde una página del sitio** (hace falta `scopeGroupId`).

## Roles y seguridad

- [ROLES-PERMISSIONS.md](ROLES-PERMISSIONS.md)
- [../SECURITY.md](../SECURITY.md)

## Checklist

1. Manager: CRUD cuenta / tx / crédito.
2. Depósito Completado o crédito Aprobado → saldo sube (Action).
3. B2B User: solo sus cuentas; puede crear tx/crédito sobre ellas.
4. Typeahead de owners y preview BIN.
5. Sin sesión → acceso restringido; sin rol B2B → sin pestañas.
