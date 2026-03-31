#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

echo "=== Cleaning generated output ==="
npm run clean

echo "=== Generating proto code (Java + TypeScript) ==="
npm run generate

echo "=== Building Java library ==="
./gradlew build

echo ""
echo "=== Generation complete ==="
echo "  Java:       gen/java/"
echo "  TypeScript:  gen/ts/"
echo ""
echo "All consumers (backend, minecraft, panel) will pick up changes on next build."
