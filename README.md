# inno-tests
mvn allure:serve

# Smoke тесты
mvn test -Dtest=SmokeTests

# E2E тесты
mvn test -Dtest="*E2ETest"

# API тесты
mvn test -Dtest="*ApiTest"

mvn clean test