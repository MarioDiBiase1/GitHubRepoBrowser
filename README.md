GitHub Repo Browser

Un'app Android che permette di cercare repository GitHub di un utente e visualizzarli in una lista, sviluppata utilizzando Retrofit, Moshi, Coroutines, Hilt e seguendo il pattern MVVM.

Funzionalità principali

Inserire il nome di un utente GitHub e cercare i suoi repository.

Visualizzare i repository in una RecyclerView con:

Nome del repository

Numero di stelle

Data di creazione formattata

Cliccando su un repository si apre il link corrispondente nel browser.

UI reattiva grazie all’uso di LiveData e ViewModel.

Tecnologie e librerie utilizzate

Kotlin

Retrofit + OkHttp → per effettuare le chiamate REST all’API GitHub.

Moshi → per deserializzare JSON in oggetti Kotlin.

Coroutines → gestione asincrona delle chiamate di rete.

RecyclerView → visualizzazione della lista di repository.

Hilt → dependency injection.

MVVM Pattern → separazione chiara di responsabilità tra UI, logica di business e dati.

Architettura: MVVM

Il progetto segue il pattern MVVM (Model-View-ViewModel):

Model

Gestisce i dati e la logica di business.

Componenti principali:

Repo.kt → modello dei repository GitHub.

GitHubRepository.kt → interagisce con l’API GitHub.

GitHubService.kt → definisce le chiamate API tramite Retrofit.

ViewModel

Gestisce lo stato della UI e fornisce dati osservabili.

Componenti principali:

GitHubViewModel.kt → contiene LiveData<List<Repo>> e chiama il repository per ottenere i dati.

View

Gestisce l’interfaccia utente e l’interazione con l’utente.

Componenti principali:

MainActivity.kt → osserva i dati del ViewModel e aggiorna la RecyclerView.

RepoAdapter.kt → adatta i dati dei repository alla RecyclerView.

Flusso MVVM

L’utente inserisce un nome e preme "Cerca".

La View chiama viewModel.loadRepos(user).

Il ViewModel chiama il Repository per ottenere i dati dall’API.

Il Repository effettua la richiesta via Retrofit e ritorna i dati.

Il ViewModel aggiorna il LiveData con i nuovi repository.

La View osserva il LiveData e aggiorna automaticamente la RecyclerView.

Struttura del progetto
com.example.githubrepobrowser
│
├── data
│   ├── Repo.kt
│   ├── GitHubService.kt
│   └── GitHubRepository.kt
│
├── ui
│   └── RepoAdapter.kt
│
├── vm
│   └── GitHubViewModel.kt
│
├── di
│   └── NetworkModule.kt
│
└── MainActivity.kt

Dipendenze principali

Retrofit, OkHttp, Moshi

Coroutines

RecyclerView, ViewModel, LiveData

Hilt per Dependency Injection
