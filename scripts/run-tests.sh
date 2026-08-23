#!/usr/bin/env bash

set -uo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(dirname "$SCRIPT_DIR")"

COMPOSE_FILE="$PROJECT_ROOT/docker/docker-compose.yml"

LOGS_DIR="$PROJECT_ROOT/logs"
MAVEN_LOG="$LOGS_DIR/test-run.log"
DOCKER_UP_LOG="$LOGS_DIR/docker-up.log"
DOCKER_DOWN_LOG="$LOGS_DIR/docker-down.log"

mkdir -p "$LOGS_DIR"

rm -f \
    "$MAVEN_LOG" \
    "$DOCKER_UP_LOG" \
    "$DOCKER_DOWN_LOG"

cd "$PROJECT_ROOT"

EXIT_CODE=0
DOCKER_STARTED=false

cleanup() {

    if [ "$DOCKER_STARTED" = true ]; then

        echo
        echo "[3/3] Stopping Docker environment..."
        echo

        docker compose -f "$COMPOSE_FILE" down --remove-orphans \
            >"$DOCKER_DOWN_LOG" 2>&1

        echo "Docker environment stopped."
        echo
    fi

    echo "========================================"

    if [ "$EXIT_CODE" -eq 0 ]; then
        echo "Finished successfully."
    else
        echo "Finished with errors."
    fi

    echo
    if [ "$DOCKER_STARTED" = true ] && command -v allure >/dev/null 2>&1
    then
        echo "To view the Allure report run:"
        echo
        echo "allure serve allure-results"
        echo
        echo "or"
        echo
        echo "allure generate allure-results --clean"
        echo "allure open allure-report"

    elif [ "$DOCKER_STARTED" = true ]
    then
        echo "Allure CLI is not installed."
        echo "Install Allure CLI to view the generated report."
    fi

    echo
    echo "========================================"

    exit "$EXIT_CODE"
}

trap cleanup EXIT

echo "========================================"
echo "Practice Software Testing"
echo "UI Test Runner"
echo "========================================"
echo

echo "Checking Docker..."
echo

if ! docker info >/dev/null 2>&1; then
    echo "Docker is not running."
    echo "Please start Docker Desktop before running the tests."
    echo

    exit 1
fi

echo "Docker is running."
echo

echo "[1/3] Starting Docker environment..."
echo

docker compose -f "$COMPOSE_FILE" up -d --wait \
    >"$DOCKER_UP_LOG" 2>&1

if [ $? -ne 0 ]; then
    echo "Failed to start Docker environment."
    echo
    echo "Detailed Docker output:"
    echo "logs/docker-up.log"
    EXIT_CODE=1
    exit
fi

DOCKER_STARTED=true

echo "Docker environment is ready."
echo

echo "[2/3] Running UI tests..."
echo

START_TIME=$(date +%s)

mvn clean test --batch-mode --no-transfer-progress "$@" \
    >"$MAVEN_LOG" 2>&1

EXIT_CODE=$?

END_TIME=$(date +%s)
ELAPSED=$((END_TIME - START_TIME))

if [ "$EXIT_CODE" -eq 0 ]; then
    echo "UI tests completed successfully."
else
    echo "UI tests failed."
    echo
    echo "Detailed Maven output:"
    echo "logs/test-run.log"
fi

printf "Execution time: %02d:%02d\n" \
    $((ELAPSED / 60)) \
    $((ELAPSED % 60))

echo