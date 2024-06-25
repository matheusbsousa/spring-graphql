.DEFAULT_GOAL := help

.PHONY: build
build:
	./gradlew build

test:
	./gradlew test

check_docker: ## Check if Docker is installed
	@if command -v docker > /dev/null; then \
		echo "Docker is installed"; \
	else \
		echo "Docker is not installed. Exiting."; \
		false; \
	fi

# Run the application
.PHONY: run-dev
run-dev: check_docker build
	@echo "Building"
	@docker build -t boat-rental-service . && docker compose up

.PHONY: clean
clean:
	@docker compose down -v
	@./gradlew clean

.PHONY: local-db
local-db:
	@docker compose -f docker-compose-local.yml up