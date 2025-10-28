#!/bin/bash
# Pre-check script for PR submission
# Validates dependencies, code formatting, API compatibility, lint, and stability.

# Exit immediately if any command fails
set -e

echo "Starting pre-check validations..."
echo ""

# Verify dependency changes
echo "🔍 [1/6] Checking dependency guard..."
./gradlew dependencyGuard
echo "✓ Dependency guard check passed"
echo ""

# Verify code formatting
echo "🔍 [2/6] Checking code formatting..."
./gradlew spotlessCheck --init-script gradle/init.gradle.kts
echo "✓ Code formatting check passed"
echo ""

# Verify API compatibility
echo "🔍 [3/6] Checking API compatibility..."
./gradlew metalavaCheckCompatibilityRelease
echo "✓ API compatibility check passed"
echo ""

# Static analysis and lint checks
echo "🔍 [4/6] Running lint checks..."
./gradlew lintDebug
echo "✓ Lint check passed"
echo ""

# Verify compose stability
echo "🔍 [5/6] Checking stability..."
./gradlew stabilityCheck
echo "✓ Stability check passed"
echo ""

# Verify screenshots
echo "🔍 [6/6] Checking screenshots..."
./gradlew verifyRoborazziDebug
echo "✓ Screenshots check passed"
echo ""

echo "✅ All pre-checks passed successfully!"
