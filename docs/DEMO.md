# Guía operativa — Nexus B2B

Capturas de referencia, preparación del entorno y checklist de verificación funcional.

---

## Capturas incluidas

| Archivo | Descripción |
|---------|-------------|
| `dashboard-hero.png` | Lista de cuentas con red de pago, owner B2B y saldo |
| `access-restricted.png` | Usuario sin sesión → acceso restringido |
| `permissions-loading.png` | Estado “Verificando permisos…” |
| `accounts-tab.png` | Pestaña Cuentas (sin registros) |
| `account-form.png` | Modal “Nueva cuenta” |
| `account-bin-preview.png` | Preview BIN + typeahead de usuario |
| `transactions-tab.png` | Pestaña Transacciones |
| `credit-tab.png` | Pestaña Crédito |

### Galería

#### Acceso

![Sin sesión](images/access-restricted.png)

#### Cuentas con datos

![Lista de cuentas](images/dashboard-hero.png)

#### Detección BIN + asignación B2B

![Preview BIN](images/account-bin-preview.png)

#### Transacciones y crédito

![Transacciones](images/transactions-tab.png)

![Crédito](images/credit-tab.png)

---

## Preparación del entorno

1. Roles de sitio: **B2B Manager** y **B2B User**.
2. Usuario Manager y usuario B2B (emails alineados con los scripts).
3. Página con friendly URL `/nexus-b2b` y el Client Extension publicado.
4. En **Server Administration → Script** (Groovy), según corresponda:
   - `scripts/assign-b2b-user-role.groovy`
   - `scripts/seed-demo-data.groovy` (ajustar emails)
   - `scripts/assign-b2b-account-owners.groovy`

---

## Checklist de verificación

### Acceso

- [ ] Sin sesión: mensaje de acceso restringido y enlace de login
- [ ] Con sesión sin rol B2B: sin pestañas o sin permisos
- [ ] Manager: ve Cuentas, Transacciones y Crédito

### Ownership

- [ ] Manager asigna un B2B User como owner de una cuenta
- [ ] B2B User solo ve esa(s) cuenta(s)
- [ ] B2B User no ve cuentas de otros

### Cuentas / BIN

- [ ] Número con ≥6 dígitos muestra preview de red
- [ ] Número Luhn inválido (≥13 dígitos) no permite guardar
- [ ] El listado enmascara el número

### Transacciones

- [ ] Retiro/pago con saldo insuficiente se rechaza
- [ ] Alta de movimiento actualiza el saldo en Cuentas
- [ ] Borrar movimiento revierte el saldo
- [ ] Borrar cuenta elimina también sus transacciones y créditos

### Crédito

- [ ] B2B User crea solicitud sobre su cuenta
- [ ] Manager aprueba → el saldo sube una vez
- [ ] Rechazar y volver a aprobar no duplica el abono de forma incorrecta

### UX

- [ ] El modal no se cierra con Escape mientras guarda
- [ ] La confirmación de borrado bloquea el scroll de fondo
- [ ] No aparece el error `closest is not a function` al interactuar con el dashboard

---

## Flujo de verificación end-to-end

1. Abrir la página sin sesión → acceso restringido.
2. Iniciar sesión como Manager → crear cuenta con BIN y asignar owner B2B.
3. Iniciar sesión como B2B User → comprobar visibilidad solo de su cuenta → crear transacción y solicitud de crédito.
4. Como Manager → filtrar por usuario → aprobar crédito → comprobar saldo.
5. Probar borrado de transacción/crédito/cuenta sin errores 500.

---

## Notas

- Para probar roles, usa dos sesiones (por ejemplo dos perfiles de navegador).
- Número de prueba con Luhn válido: `4111111111111111`.
