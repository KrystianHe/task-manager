# Task Manager

Mini projekt typu **task manager** stworzony w celu zarządzania zadaniami. Aplikacja umożliwia dodawanie, edytowanie, usuwanie oraz przeglądanie zadań, a także generowanie nowych zadań za pomocą sztucznej inteligencji.

## Technologie

- **Java** – język programowania
- **Spring Boot** – framework do budowy aplikacji webowych
- **Spring Data JPA** – obsługa bazy danych
- **Git** – system kontroli wersji
- **AI ChatClient** – do generowania i szacowania zadań

## Funkcjonalności

1. **Zarządzanie zadaniami**:
   - Dodawanie nowych zadań
   - Edytowanie istniejących
   - Usuwanie zadań
   - Przeglądanie wszystkich zadań
   
2. **Integracja z AI**:
   - Generowanie opisu zadania na podstawie wiadomości tekstowej
   - Szacowanie czasu potrzebnego do wykonania zadania
   - Propozycja alternatywnych rozwiązań dla zadania

## Instalacja

1. **Klona repozytorium**:
    ```bash
    git clone https://github.com/KrystianHe/task-manager.git
    ```

2. **Wejdź do katalogu projektu**:
    ```bash
    cd task-manager
    ```

3. **Zbuduj projekt**:
    Używając [Maven](https://maven.apache.org/):
    ```bash
    mvn clean install
    ```

4. **Uruchom aplikację**:
    Aplikację uruchomisz za pomocą poniższego polecenia:
    ```bash
    mvn spring-boot:run
    ```

## Endpointy API

### GET /api/ai/tasks/generate-task
Generuje zadanie na podstawie wiadomości tekstowej.  
Przykład:
```bash
GET /api/ai/tasks/generate-task?message="Write a description for a new task"


....


## Work GitLab Contributions

Here is a snapshot of my contributions from GitLab:

![GitLab Contributions](https://github.com/user-attachments/assets/06ba8c4d-57ad-4a8d-8983-cea1eb50ca9b)

