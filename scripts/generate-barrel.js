import { readdirSync, writeFileSync, existsSync } from "node:fs";
import { join, basename, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = dirname(fileURLToPath(import.meta.url));
const tsDir = join(__dirname, "..", "gen", "ts", "modl", "v1");

if (!existsSync(tsDir)) process.exit(0);

const barrel = readdirSync(tsDir)
  .filter((f) => f.endsWith(".ts") && f !== "index.ts")
  .map((f) => `export * from "./modl/v1/${basename(f, ".ts")}";`)
  .join("\n");

writeFileSync(join(__dirname, "..", "gen", "ts", "index.ts"), barrel + "\n");
