# Seguridad (OWASP) — Nexus B2B Dashboard

Referencia técnica de controles aplicados en el Client Extension y en la configuración Liferay Objects. No sustituye un pentest ni una revisión formal de cumplimiento PCI.

## Controles en el CE

| OWASP / riesgo | Mitigación |
|----------------|------------|
| **A01 Broken Access Control** | Matriz UI por roles de sitio `B2B Manager` / `B2B User`; B2B User filtrado por `ownerUserId`; Objects site-scoped |
| **A03 Injection / XSS** | React escapa por defecto; sin `dangerouslySetInnerHTML`; mensajes de error saneados (`safeMessage`); inputs de cuenta/monto sanitizados |
| **A04 Insecure Design** | Saldo no editable en UI; ledger vía Object Action; BIN solo heurística local (sin enviar PAN a terceros) |
| **A05 Security Misconfiguration** | Guest sin acceso a Objects; CSRF (`x-csrf-token` / `p_auth`) en mutaciones y JSONWS |
| **A07 Auth failures** | UI bloqueada si no hay sesión (`ThemeDisplay.isSignedIn`); 401/403 no se reintentan en React Query |
| **A09 Logging / info leakage** | Errores 5xx genéricos en UI; sin stacks ni detalles internos del servidor |
| **SSRF / open redirect** | `liferayFetch` y JSONWS solo aceptan rutas relativas del portal |

## Controles en el portal

1. Roles de sitio **B2B Manager** / **B2B User** (nombres exactos).
2. Permisos Objects para esos roles: ver [ROLES-PERMISSIONS.md](objects/ROLES-PERMISSIONS.md) y `scripts/grant-b2b-object-permissions.groovy`.
3. Guest / usuario sin rol B2B: sin pestañas ni datos.
4. Activar Groovy solo en entornos self-hosted donde se usen Object Actions (script del ledger).

## Datos sensibles

- Números de cuenta/tarjeta de **ejemplo** (Luhn + máscara en listados).
- No hay vault PCI ni tokenización: no usar PAN reales en producción.
- Credenciales y `portal-ext` locales no deben publicarse (`.gitignore` cubre `.env`).

## Checklist rápido antes de publicar

- [ ] Sin IDs de usuario/grupo ni emails reales en scripts/docs
- [ ] Permisos Objects aplicados a roles B2B (no a Guest)
- [ ] Probar Manager, B2B User y usuario sin rol
- [ ] Confirmar que no quedan JARs Service Builder en el bundle
