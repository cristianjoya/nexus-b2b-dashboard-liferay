# Roles y permisos B2B (Objects)

El dashboard abre pestañas si el usuario tiene en **este sitio**:

| Rol de sitio | Perfil UI |
|--------------|-----------|
| **B2B Manager** | CRUD completo |
| **B2B User** | Ver cuentas propias; crear txs/créditos |
| Administrator / Site Admin / Site Owner | Tratado como Manager |

## 1. Roles de sitio

Usuarios → editar usuario → **Roles** → **Roles de sitio web**:

- Asigna **B2B Manager** o **B2B User** al sitio donde está la página del dashboard.
- El usuario debe ser **miembro** de ese sitio.
- Nombres exactos: `B2B Manager` y `B2B User`.

Script genérico: `scripts/assign-b2b-user-role.groovy` (edita email / rol / friendly URL).

## 2. Permisos de Objects

Headless Objects exige permisos en cada definición (además del rol de sitio).

**Script:** `scripts/grant-b2b-object-permissions.groovy`  
(Server Administration → Script → Execute)

Otorga permisos en **COMPANY**, **GROUP_TEMPLATE** y **SCOPE_GROUP** del sitio `/nexus-b2b`.

- `VIEW` / `UPDATE` / `DELETE` → `objectDefinition.className`
- `ADD_OBJECT_ENTRY` → `objectDefinition.resourceName` (obligatorio para crear vía Headless)

El resultado debe incluir `VERIFY OK (rol)`. Si falla el VERIFY, el grant no aplicó.  
Si VERIFY OK pero la API sigue en 403: el usuario no tiene el rol B2B **en ese sitio** → `assign-b2b-user-role.groovy`.

| Acción Objects | B2B Manager | B2B User |
|----------------|-------------|----------|
| Cuentas View | ✓ | ✓ |
| Cuentas Add/Update/Delete | ✓ | — |
| Transacciones / Crédito View | ✓ | ✓ |
| Transacciones / Crédito Add | ✓ | ✓ |
| Transacciones / Crédito Delete | ✓ | — |

El resultado debe incluir `VERIFY OK (matriz Manager/User separada)`.  
Si VERIFY OK pero la API sigue en 403: el usuario no tiene el rol B2B **en ese sitio** → `assign-b2b-user-role.groovy`.

Sin el grant correcto verás 403 o (si User tiene ADD en cuentas) la UI de Manager.

## 3. Comprobar

1. Ventana privada / otra sesión.
2. Entra con B2B User en la página del dashboard del sitio.
3. Deben aparecer las pestañas (no el mensaje de sin permisos).
4. Manager: CRUD + typeahead de owners.
5. Usuario **sin** rol B2B: mensaje de acceso denegado (esperado).

## 4. Scripts

| Script | Uso |
|--------|-----|
| `scripts/assign-b2b-user-role.groovy` | Membresía + rol B2B |
| `scripts/grant-b2b-object-permissions.groovy` | Permisos Objects a los roles |
| `scripts/diagnose-b2b-user-roles.groovy` | Ver membresía/roles de un usuario |
| `scripts/seed-demo-data.groovy` | Datos de ejemplo |
