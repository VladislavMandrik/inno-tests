# InnoTests — Автоматизированный фреймворк тестирования

Проект автоматизированного тестирования сайта [innowise.com](https://innowise.com).  
Покрывает **UI**, **API** и **Unit** уровни тестирования с параллельным выполнением и отчётностью через Allure.

---

## 📋 Технологический стек

| Категория            | Технология / Библиотека                        |
|----------------------|------------------------------------------------|
| Язык                 | Java 17                                        |
| Сборка               | Apache Maven                                   |
| UI-тестирование      | Selenium WebDriver 4.28                        |
| Управление драйвером | WebDriverManager 5.9                           |
| API-тестирование     | REST Assured 5.5                               |
| Тестовый фреймворк  | JUnit 5 (Jupiter) 5.11                         |
| Отчётность           | Allure 2.24                                    |
| Моки / Стабы        | WireMock 3.13, Mockito 5.14                    |
| Сетевой прокси       | BrowserMob Proxy 2.1                           |
| Логирование          | SLF4J 2.0                                      |
| Переменные окружения | dotenv-java 3.0                                |
| Контейнеризация      | Docker, Docker Compose                         |
| Анализ кода          | Checkstyle, PMD                                |

---

## 🏗 Архитектура проекта

```
src/
├── main/java/org/example/
│   ├── config/              # Константы, конфиги браузера/драйвера/окружения
│   │   ├── Constants.java   # Все строковые константы проекта
│   │   ├── BrowserConfig.java
│   │   ├── DriverConfig.java
│   │   └── EnvConfig.java
│   ├── pages/               # Page Object Model
│   │   ├── BasePage.java
│   │   ├── MainPage.java
│   │   └── SearchPage.java
│   └── utils/
│       ├── driver/          # Управление WebDriver (ThreadLocal)
│       │   ├── DriverManager.java
│       │   └── BrowserFactory.java
│       ├── helpers/         # Вспомогательные утилиты
│       │   ├── PageActionsHelper.java
│       │   ├── ApiTestHelper.java
│       │   └── AllureHelper.java
│       └── proxy/
│           └── ProxyManager.java
│
└── test/java/org/example/
    ├── api/                 # API тесты (REST Assured)
    │   ├── BaseApiTest.java
    │   ├── SearchApiTests.java
    │   └── PageAvailabilityApiTest.java
    ├── auth_project_tests/  # Тесты авторизации
    │   └── AuthApiTests.java
    ├── unit/                # Unit-тесты
    │   └── MainPageOperationsTest.java
    └── ui/                  # UI тесты (Selenium)
        ├── base/
        │   └── BaseUITest.java
        ├── operations/      # Слой операций (бизнес-сценарии)
        │   ├── MainPageOperations.java
        │   └── SearchPageOperations.java
        ├── smoke/
        │   └── SmokeTests.java
        ├── e2e/
        │   └── SearchE2ETest.java
        ├── ScrollTest.java
        └── CheckFooterLinksTest.java
```

### Паттерны и подходы

- **Page Object Model (POM)** — каждая страница представлена отдельным классом с локаторами и атомарными действиями.
- **Operations Layer** — классы `MainPageOperations` / `SearchPageOperations` инкапсулируют сложные бизнес-сценарии и цепочки шагов, используя `@Step` аннотации Allure.
- **ThreadLocal WebDriver** — `DriverManager` обеспечивает изоляцию драйверов при параллельном запуске.
- **BaseUITest / BaseApiTest** — базовые классы управляют жизненным циклом тестов (`@BeforeEach` / `@AfterEach`).

---

## 🗂 Виды тестов

### UI тесты (`@Tag("e2e")`, `@Tag("smoke")`)

| Класс                  | Описание                                              |
|------------------------|-------------------------------------------------------|
| `SmokeTests`           | Smoke-проверки: загрузка страницы, поиск, иконка      |
| `SearchE2ETest`        | E2E: успешный поиск и обработка опечатки              |
| `CheckFooterLinksTest` | Параметризованные проверки ссылок и текстов в футере  |
| `ScrollTest`           | Проверка видимости хедера после скролла               |

### API тесты (`@Tag("api")`)

| Класс                    | Описание                                           |
|--------------------------|----------------------------------------------------|
| `SearchApiTests`         | GET-поиск, пустой запрос, AJAX-поиск               |
| `PageAvailabilityApiTest`| Проверка доступности страниц (HTTP-статусы)        |
| `AuthApiTests`           | Тесты авторизации и защищённых эндпоинтов          |

### Unit тесты

| Класс                       | Описание                                      |
|-----------------------------|-----------------------------------------------|
| `MainPageOperationsTest`    | Тестирование операций главной страницы (Mockito) |

---

## ⚙️ Конфигурация

### Переменные окружения (`.env`)

Создайте файл `.env` в корне проекта:

```env
# Браузер: CHROME, FIREFOX, EDGE (по умолчанию CHROME)
BROWSER=CHROME

# Режим запуска: true — headless, false — с UI
HEADLESS=false

# Selenium Grid (опционально)
REMOTE=false
SELENIUM_HUB_URL=http://localhost:4444/wd/hub

# Учётные данные (для тестов авторизации)
ADMIN_USERNAME=admin
ADMIN_PASSWORD=secret
USER_USERNAME=user
USER_PASSWORD=secret
```

### Параллельное выполнение

Настраивается в `pom.xml`:

```xml
<parallel.threads>4</parallel.threads>
<junit.jupiter.execution.parallel.enabled>true</junit.jupiter.execution.parallel.enabled>
<junit.jupiter.execution.parallel.mode.default>concurrent</junit.jupiter.execution.parallel.mode.default>
```

---

## 🚀 Запуск тестов

### Предварительные требования

- Java 17+
- Maven 3.8+
- Google Chrome / Firefox / Edge (или Docker)

### Команды запуска

```shell
# Все тесты
mvn clean test

# Только Smoke-тесты
mvn clean test -Dgroups="smoke"

# Только API-тесты
mvn clean test -Dgroups="api"

# Только E2E-тесты
mvn clean test -Dgroups="e2e"

# Headless режим (без UI браузера)
mvn clean test -DHEADLESS=true

# Запуск через Selenium Grid
mvn clean test -DREMOTE=true -DSELENIUM_HUB_URL=http://localhost:4444/wd/hub

# Конкретный тест
mvn clean test -Dtest=SearchE2ETest
```

---

## 📊 Allure-отчёты

```shell
# Генерация и открытие отчёта
mvn allure:serve

# Только генерация отчёта (без автооткрытия)
mvn allure:report
```

Отчёт будет доступен по адресу: `http://localhost:PORT` (откроется автоматически).  
Результаты сохраняются в: `target/allure-results/`  
Готовый отчёт: `target/allure-report/`

---

## 🐳 Запуск в Docker

### Запуск инфраструктуры (БД + приложение)

```shell
docker-compose up -d
```

### Сборка и запуск тестов в контейнере

```shell
# Сборка образа
docker build -t inno-tests .

# Запуск тестов
docker run --rm inno-tests
```

### Состав docker-compose

| Сервис | Образ              | Порт   | Описание              |
|--------|--------------------|--------|-----------------------|
| `db`   | `postgres:16-alpine` | 5432 | PostgreSQL база данных |
| `app`  | локальная сборка   | 8081   | Тестируемое приложение |

---

## 🔍 Анализ качества кода

Проект использует **Checkstyle** и **PMD** для статического анализа. Они запускаются автоматически при сборке:

```shell
# Запуск только анализа (без тестов)
mvn checkstyle:check
mvn pmd:check

# Полная сборка с анализом
mvn clean verify
```

> Нарушения правил не блокируют сборку (`failOnViolation=false`), но отображаются в отчёте.

---

## 📁 Полезные пути

| Путь                             | Описание                          |
|----------------------------------|-----------------------------------|
| `target/allure-results/`         | Сырые результаты Allure           |
| `target/allure-report/`          | Сгенерированный HTML-отчёт        |
| `target/surefire-reports/`       | Отчёты Maven Surefire             |
| `src/test/resources/`            | Тестовые данные и CSV-файлы       |
| `.env`                           | Переменные окружения              |

---

## 🤝 Соглашения о коде

- Тестовые классы именуются с суффиксом `Test` или `Tests`
- Все строковые константы хранятся в `Constants.java`
- Атомарные действия над страницей — в классах `Page`
- Сложные сценарии и цепочки шагов — в классах `Operations`
- Каждый тест помечается аннотациями `@Epic`, `@Feature`, `@Story`, `@DisplayName`, `@Description`

