#!/bin/bash

# Path to the versions file
VERSIONS_FILE="gradle/libs.versions.toml"
README_FILE="config/main.md"

# Extract Kotlin version from the TOML file
KOTLIN_VERSION=$(grep -E '^kotlin\s*=' "$VERSIONS_FILE" | sed -E 's/.*"([^"]+)".*/\1/')

if [[ -z "$KOTLIN_VERSION" ]]; then
  echo "❌ Kotlin version not found in $VERSIONS_FILE"
  exit 1
fi

echo "✅ Kotlin version found: $KOTLIN_VERSION"

# Escape dots for the regex in the replacement
ESCAPED_VERSION=$(echo "$KOTLIN_VERSION" | sed 's/\./\\./g')

# Update the badge in README.md
sed -i.bak -E "s|(kotlin-)[0-9]+\.[0-9]+\.[0-9]+|\1$KOTLIN_VERSION|g" "$README_FILE"

echo "✅ README.md updated with Kotlin version $KOTLIN_VERSION"
