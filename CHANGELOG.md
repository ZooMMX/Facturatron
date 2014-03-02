## 1.7.0.9 (2014-03-02)
- Mejoras de usabilidad en confección de factura:
   - Navegación mediante `<tab>` (tecla tabular) entre columnas de los conceptos de una factura. Gracias a Jose Julian Abarca
   - Navegación mediante `<shift>+<tab>` entre columnas (hacia atrás)
   - Vista previa de comprobantes
   - Botón para eliminar renglones (partidas) de comprobante
   - Diálogo para cerrar factura timbrada. Se lanza al confirmar timbrado y pregunta si se quiere cerrar la pestaña donde se confeccionó el CFDI. 

## 1.7.0.8 (2014-02-08)
- Soporte para IEPS
 - Nuevos campos de IEPS en el formulario de facturación
 - El XML ahora incluye, agrupados por tasa, todos los impuestos IEPS
 - El PDF, al igual que el XML, agrupa por tasa y muestra cada tasa de IEPS e IVA
- Optimización de la GUI de facturación, reduje todo el espacio desperdiciado.
- Optimización de la plantilla genérica de facturación, reduje mucho el desperdicio de espacio para generar facturas más compactas sin sacrificar una fuente grande

## 1.7.0 RC1. Integración CFDI

- [FEATURED] Migrado de CFD v2.2 a CFDI 3.2
- Arquitectura de PACs modular
- Implementado el PAC Finkok
- \#28 Ventana de configuración de PAC
- \#29 Ventana de status de CFDIs y cancelaciones
- \#22 y #13 Mejorada la información que despliegan los errores,
validación de formulario que da info sobre alrededor de 20 tipos de
errores en llenado y validación de timbrado y cancelación que describe
alrededor de otros 20 tipos de problemas. Además sembré descripciones
de errores en varias partes del programa.
- Mejorado diálogo de errores: Ahora es modal, centrado, con mejor
apariencia y bloquea correctamente el JFrame
- Corrección del error que aparecía frecuentemente al abrir el listado
de comprobantes emitidos
- Implementado el weblaf para actualizar la apariencia de la aplicación
