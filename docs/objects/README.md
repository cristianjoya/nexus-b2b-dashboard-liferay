# Nexus Fin Services — Liferay Objects

Dominio B2B sobre **Liferay Objects** (sin Service Builder ni REST Builder custom).

| Fase | Guía | Estado en repo |
|------|------|----------------|
| 1 · Modelo + picklists | [PHASE1-GUIDE.md](PHASE1-GUIDE.md) | Exports en [`exports/`](exports/) |
| 2 · Ledger (saldo) | [PHASE2-GUIDE.md](PHASE2-GUIDE.md) | [`actions/recalculate-account-balance.groovy`](actions/recalculate-account-balance.groovy) |
| 3 · React CE | [PHASE3-GUIDE.md](PHASE3-GUIDE.md) | `client-extensions/fin-services-dashboard` → `/o/c/...` |
| Roles / permisos | [ROLES-PERMISSIONS.md](ROLES-PERMISSIONS.md) | Scripts en `/scripts` |

## Qué hay en Git vs qué vive en el portal

| En el repositorio | En Liferay |
|-------------------|------------|
| JSON de picklists + Objects | Importar / crear en el sitio |
| Groovy del Action | Pegar en las Object Actions |
| Scripts de roles / seed | Server Administration → Script |
| Código React | Deploy del Client Extension |

Orden al recrear un entorno: picklists → Objects → Actions Groovy → `grant-b2b-object-permissions` → deploy CE → asignar roles a usuarios.

## Notas

- Scope **site**; campos `nexusStatus` / `nexusTransactionStatus` / `nexusCreditStatus`.
- Saldo de cuenta: **solo lectura** en UI; lo recalcula el Object Action.
- No hay módulos Service Builder / REST Builder en este proyecto.
