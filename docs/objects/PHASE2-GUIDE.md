# Fase 2 — Ledger y crédito en Objects

El saldo **no** se edita a mano. Un Object Action Groovy recalcula `FinAccount.balance`:

`suma(DEPÓSITOS completados) − retiros/pagos completados + créditos aprobados`

Script versionado: [`actions/recalculate-account-balance.groovy`](actions/recalculate-account-balance.groovy).

Groovy está **deshabilitado por defecto** en DXP 2024.Q3+; actívalo en self-hosted si usas estas Actions.

## Acciones a crear

| Objeto | Nombre de acción | Trigger |
|--------|------------------|---------|
| Fin Transaction | `Recalc balance after add` | On After Add |
| Fin Transaction | `Recalc balance after update` | On After Update |
| Fin Transaction | `Recalc balance after delete` | On After Delete |
| Fin Credit Application | `Recalc balance after add` | On After Add |
| Fin Credit Application | `Recalc balance after update` | On After Update |
| Fin Credit Application | `Recalc balance after delete` | On After Delete |

Tipo: **Groovy Script**. Mismo archivo en las 6.

> **DXP 2026:** `updateObjectEntry` usa `(userId, objectEntryId, objectEntryFolderId, values, serviceContext)`. El script del repo ya lo contempla.

## Crédito

- Alta: `nexusCreditStatus = 0` (Pendiente) → no suma saldo
- Aprobada (`1`) → On After Update suma `requestedAmount`
- Rechazo (`2`) o volver a pendiente → el recálculo quita el abono

## Permisos

Roles y permisos Objects: [ROLES-PERMISSIONS.md](ROLES-PERMISSIONS.md).

## Fuera de alcance (mejoras)

- Workflow Kaleo / Action Client Extension (alternativa a Groovy a largo plazo)
