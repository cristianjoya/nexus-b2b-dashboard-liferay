# Fase 1 — Liferay Objects (modelo de datos)

Objetivo: crear el modelo B2B en **Objects** (site-scoped). Completado; ver Fases 2–3.

## Convenciones

| Concepto | Valor |
|----------|--------|
| Carpeta Objects | `Nexus Fin Services` |
| Scope | **Site** (datos por sitio, equivalente a `groupId`) |
| ERC picklists | `NEXUS_*` |
| ERC objects | `FIN_ACCOUNT`, `FIN_TRANSACTION`, `FIN_CREDIT_APPLICATION` |

## Picklists (Control Panel → Picklists)

| ERC | Nombre | Valores (key = label) |
|-----|--------|------------------------|
| `NEXUS_ACCOUNT_TYPE` | Nexus Account Type | `CHECKING` Corriente, `SAVINGS` Ahorros |
| `NEXUS_ACCOUNT_STATUS` | Nexus Account Status | `1` Activa, `0` Inactiva |
| `NEXUS_TRANSACTION_TYPE` | Nexus Transaction Type | `DEPOSIT` Depósito, `WITHDRAWAL` Retiro, `PAYMENT` Pago |
| `NEXUS_TRANSACTION_STATUS` | Nexus Transaction Status | `1` Completada, `0` Pendiente, `2` Fallida |
| `NEXUS_CREDIT_STATUS` | Nexus Credit Status | `0` Pendiente, `1` Aprobada, `2` Rechazada |

## Object: Fin Account (`FIN_ACCOUNT`)

- **Label:** Fin Account · **Plural:** Fin Accounts
- **Scope:** Site
- **Entry title field:** `accountName`

| Campo | Tipo | Notas |
|-------|------|--------|
| `accountNumber` | Text | Required, **Accept Unique Values Only** |
| `accountName` | Text | Required |
| `accountType` | Picklist | `NEXUS_ACCOUNT_TYPE` |
| `balance` | Decimal | Default `0` |
| `nexusStatus` | Picklist | `NEXUS_ACCOUNT_STATUS`, default Activa (`status` reservado por Liferay) |
| `ownerUserId` | Integer | ID usuario B2B dueño |
| `cardScheme` | Text | Metadata BIN |
| `cardBrand` | Text | |
| `cardBankName` | Text | |
| `cardCountryName` | Text | |

## Object: Fin Transaction (`FIN_TRANSACTION`)

- **Entry title field:** `description` (o `transactionDate`)

| Campo | Tipo | Notas |
|-------|------|--------|
| `transactionType` | Picklist | `NEXUS_TRANSACTION_TYPE` |
| `amount` | Decimal | Required |
| `description` | Long text | |
| `transactionDate` | Date | |
| `nexusTransactionStatus` | Picklist | `NEXUS_TRANSACTION_STATUS`, default Completada |

**Relación:** Fin Account **one-to-many** Fin Transaction (parent: Fin Account).

## Object: Fin Credit Application (`FIN_CREDIT_APPLICATION`)

| Campo | Tipo | Notas |
|-------|------|--------|
| `applicantName` | Text | Required |
| `requestedAmount` | Decimal | Required |
| `purpose` | Long text | |
| `nexusCreditStatus` | Picklist | `NEXUS_CREDIT_STATUS`, default Pendiente |
| `reviewNotes` | Long text | |

**Relación:** Fin Account **one-to-many** Fin Credit Application.

## Export (completado)

JSON versionados en [`exports/`](exports/):

| Archivo | Contenido |
|---------|-----------|
| `picklist-NEXUS_*.json` | 5 picklists |
| `object-FinAccount.json` | Site-scoped · ERC `FIN_ACCOUNT` |
| `object-FinTransaction.json` | Site-scoped · ERC `FIN_TRANSACTION` |
| `object-FinCreditApplication.json` | Site-scoped · ERC `FIN_CREDIT_APPLICATION` |

**Validado:** scope `site`, relaciones 1→N con `deletionType: prevent`, campos `nexusStatus` / `nexusTransactionStatus` / `nexusCreditStatus`, `accountNumber` unique.

Orden de import en otro entorno: picklists → FinAccount → FinTransaction → FinCreditApplication.

Siguiente: [PHASE2-GUIDE.md](PHASE2-GUIDE.md).

## Estado

Completada. Siguiente: [PHASE2-GUIDE.md](PHASE2-GUIDE.md) · [PHASE3-GUIDE.md](PHASE3-GUIDE.md).
