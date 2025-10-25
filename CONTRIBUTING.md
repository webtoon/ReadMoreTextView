# How to contribute

## Pre-check before PR

### Run all checks at once:

```bash
./pre_check.sh
```

### Run checks individually

- dependencyGuard
```bash
./gradlew dependencyGuard
```

- spotless
```bash
./gradlew spotlessCheck --init-script gradle/init.gradle.kts
```

- metalava
```bash
./gradlew metalavaCheckCompatibilityRelease
```

- lint
```bash
./gradlew lintDebug
```

## Update baselines

- dependencyGuard
```bash
./gradlew dependencyGuardBaseline
```

- spotless
```bash
./gradlew spotlessApply --init-script gradle/init.gradle.kts
```

- metalava
```bash
./gradlew metalavaGenerateSignatureRelease
```
