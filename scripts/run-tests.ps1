$projectRoot = Split-Path $PSScriptRoot -Parent
$composeFile = Join-Path $projectRoot "docker\docker-compose.yml"

$logsDir = Join-Path $projectRoot "logs"
$mavenLog = Join-Path $logsDir "test-run.log"
$dockerUpLog = Join-Path $logsDir "docker-up.log"
$dockerDownLog = Join-Path $logsDir "docker-down.log"

New-Item -ItemType Directory -Path $logsDir -Force | Out-Null

foreach ($file in @($mavenLog, $dockerUpLog, $dockerDownLog)) {
    if (Test-Path $file) {
        Remove-Item $file
    }
}

Set-Location $projectRoot

Write-Host "========================================"
Write-Host "Practice Software Testing"
Write-Host "UI Test Runner"
Write-Host "========================================"
Write-Host

$exitCode = 0
$dockerStarted = $false

try {

    Write-Host "[1/3] Starting Docker environment..."
    Write-Host

    docker compose -f $composeFile up -d --wait *> $dockerUpLog

    if ($LASTEXITCODE -ne 0) {
        Write-Host "Failed to start Docker environment."
        Write-Host
        Write-Host "Detailed Docker output:"
        Write-Host "logs/docker-up.log"

        $exitCode = $LASTEXITCODE
        return
    }

    $dockerStarted = $true

    Write-Host "Docker environment is ready."
    Write-Host

    Write-Host "[2/3] Running UI tests..."
    Write-Host

    $startTime = Get-Date

    mvn clean test --batch-mode --no-transfer-progress *> $mavenLog

    $exitCode = $LASTEXITCODE

    $duration = (Get-Date) - $startTime

    if ($exitCode -eq 0) {
        Write-Host "UI tests completed successfully."
    }
    else {
        Write-Host "UI tests failed."
        Write-Host
        Write-Host "Detailed Maven output:"
        Write-Host "logs/test-run.log"
    }

    Write-Host ("Execution time: {0:mm\:ss}" -f $duration)
    Write-Host

}
finally {

    if ($dockerStarted) {

        Write-Host "[3/3] Stopping Docker environment..."
        Write-Host

        docker compose -f $composeFile down --remove-orphans *> $dockerDownLog

        Write-Host "Docker environment stopped."
        Write-Host
    }

    Write-Host "========================================"

    if ($exitCode -eq 0) {
        Write-Host "Finished successfully."
    }
    else {
        Write-Host "Finished with errors."
    }

    Write-Host
    Write-Host "To view the Allure report run:"
    Write-Host
    Write-Host "allure serve allure-results"
    Write-Host
    Write-Host "or"
    Write-Host
    Write-Host "allure generate allure-results --clean"
    Write-Host "allure open allure-report"
    Write-Host
    Write-Host "========================================"
}

exit $exitCode