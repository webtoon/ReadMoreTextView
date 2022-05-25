#!/bin/bash
set -e

# Generate API docs
./gradlew dokkaHtmlMultiModule

# Copy *.md files into docs directory
cp README.md docs/index.md

sed -i.bak 's/docs\/\([_a-zA-Z-]*\).png/\1.png/' docs/index.md

# Finally delete all of the backup files
find . -name '*.bak' -delete
