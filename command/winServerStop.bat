@echo off
setlocal enabledelayedexpansion
for /f "tokens=1-5" %%a in ('netstat -ano ^| find ":4000"') do (
    if "%%e%" == "" (
        set pid=%%d
    ) else (
        set pid=%%e
    )
    echo !pid!
    taskkill /f /pid !pid!
)
pause