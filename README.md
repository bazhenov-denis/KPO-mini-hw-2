## Обзор

Этот проект представляет собой веб-приложение для автоматизации бизнес-процессов зоопарка:
- **Управление животными** (добавление/удаление животных)
- **Управление вольерами** (добавление/удаление вольеров)
- **Перемещение животных** между вольерами
- **Расписание кормления** (просмотр и обновление)
- **Статистика зоопарка** (общее число животных, свободные вольеры и т.д.)

Приложение реализовано с использованием концепций Domain-Driven Design (DDD) и принципов Clean Architecture. Бэкенд разработан на Spring Boot и предоставляет REST API

## Архитектура
Проект разбит на четыре слоя по Clean Architecture:

- **Domain** (`kpo-backend/src/main/java/com/example/kpo_backend/domain`)
  - Доменные модели (сущности, value objects, доменные события)
  - Интерфейсы репозиториев

- **Application** (`kpo-backend/src/main/java/com/example/kpo_backend/application`)
  - Сервисы бизнес-логики (use-case реализации)
  - DTO уровня приложения (при необходимости)

- **Infrastructure** (`kpo-backend/src/main/java/com/example/kpo_backend/infrastructure/repository`)
  - Реализации репозиториев в памяти (in-memory)
  - Интеграции с внешними системами (если есть)

- **Presentation** (`kpo-backend/src/main/java/com/example/kpo_backend/presentation`)
  - REST-контроллеры для CRUD и процессов
  - Взаимодействие через интерфейсы сервисов

Конфигурация Swagger/OpenAPI находится в пакете **config**.

## Реализованный функционал

| Use Case                                                  | Слой & Классы / Модули                                                          |
|-----------------------------------------------------------|----------------------------------------------------------------------------------|
| a. Добавить/удалить животное                              | `AnimalController` ⇄ `AnimalService` ⇄ `AnimalRepository`                         |
| b. Добавить/удалить вольер                                | `EnclosureController` ⇄ `EnclosureService` ⇄ `EnclosureRepository`               |
| c. Переместить животное между вольерами                   | `AnimalTransferService` (Application) + `AnimalMovedEvent` (Domain)              |
| d. Просмотреть расписание кормления                       | `FeedingScheduleController` ⇄ `FeedingScheduleService`                           |
| e. Добавить запись в расписание кормлений                 | `FeedingOrganizationService` + `FeedingTimeEvent`                                |
| f. Просмотреть статистику зоопарка (животные, вольеры)    | `ZooStatisticsService`                                                           |

## DDD-концепции

- **Сущности**:
  - `Animal` (вид, имя, дата рождения, пол, любимая еда, статус здоровья)
  - `Enclosure` (тип, размер, текущее и максимальное число животных)
  - `FeedingSchedule` (животное, время кормления, тип корма)

- **Value Objects**:
  - `AnimalId`, `EnclosureId`, `FeedingScheduleId`
  - `Gender`, `FoodType`, `EnclosureType`, `HealthStatus`

- **Агреграты и инварианты**:
  - В `Enclosure` контролируется совместимость видов (хищник/травоядное) и вместимость
  - В `Animal` реализованы методы `feed()`, `treat()`, `moveTo()` с бизнес-правилами

- **Доменные события**:
  - `AnimalMovedEvent` при перемещении животного
  - `FeedingTimeEvent` при наступлении времени кормления

- **Репозитории**:
  - Интерфейсы в Domain: `AnimalRepository`, `EnclosureRepository`, `FeedingScheduleRepository`
  - In-memory реализации в Infrastructure

## Принципы Clean Architecture

1. **Зависимости направлены внутрь**:
   - Presentation → Application → Domain
   - Infrastructure предоставляет реализации интерфейсов, но Domain не зависит ни от чего

2. **Инверсия зависимостей** через интерфейсы:
   - Слои Presentation и Application обращаются к Domain-репозиториям через интерфейсы

3. **Изоляция бизнес-логики**:
   - Вся логика содержится в Domain и Application, без кода инфраструктуры и фреймворков

## Тестирование через OpenAPI (Swagger)

- Swagger UI доступен по адресу `http://localhost:8080/swagger-ui.html`
- В интерактивной документации можно:
  1. Добавлять/просматривать/удалять сущности (животные, вольеры, расписания)
  2. Выполнять операции кормления и перемещения

Примеры эндпоинтов:
```
GET    /api/animals
POST   /api/animals
DELETE /api/animals/{id}
POST   /api/animals/{id}/move?targetEnclosureId={eid}
GET    /api/feedings
POST   /api/feedings
GET    /api/statistics
```

## Запуск приложения

**Требования**: JDK 11+, Maven, Node.js

1. **Бэкенд**:
   ```bash
   cd kpo-backend
   ./mvnw clean package
   ./mvnw spring-boot:run
   ```

Приложение будет доступно на `http://localhost:8080`

