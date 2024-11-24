# Protocollo di Comunicazione

Questo documento descrive il protocollo di comunicazione utilizzato per il collegamento e l'interazione tra client e server.

## AVVIENE IL COLLEGAMENTO DEL SOCKET AL SERVER

### Inserimento UserName

- **Client:** `CONNECT <nome>`
- **Server:** 
  - `JOIN <username>`  (se username disponibile)
  - `KO user-not-available` (se username non disponibile)
  - `KO session-timeout` (dopo timer di 1 minuto)

### Utente Vuole Cambiare Nickname

- **Client:** `CHANGE <new name>`
- **Server:** 
  - `JOIN <username>`  (se username disponibile)
  - `KO user-not-available` (se username non disponibile)

### Chat Privata

- **Client:** `PRIVATE <destinatario> <messaggio>`
- **Server al mittente:** `OK`
- **Server destinatario:** `PRIVATE <mittente> <messaggio>`
- **Server:** `KO user-not-found` (se username non esiste)

### Lista Utenti Attivi

- **Client:** `USERS` (richiede la lista di utenti online)
- **Server:** `USERS <...> <...> <...> <...> <...>` (lista utenti divisi da uno spazio)

### Chat Globale

- **Client:** `GLOBAL <messaggio>`
- **Server manda a tutti:** `GLOBAL <mittente> <messaggio>`

### Disconnessione

- **Client:** `ESC`
- **Server:** `BYE <utente>` (mandato globalmente)
