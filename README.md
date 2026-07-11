# Obsidian Armor

A Minecraft Fabric mod for **1.20.1** that adds:

- **Obsidian Ingot** — smelt obsidian blocks in a **Blast Furnace**. The ingot uses the same recipe shape as an iron ingot but looks like obsidian.
- **Obsidian Armor Set** — helmet, chestplate, leggings, and boots crafted with obsidian ingots (same crafting shapes as diamond armor, obsidian appearance).
  - Wearing the **full set** grants **Fire Resistance** but also **Slowness** (obsidian is heavy!).
  - Wearing any pieces gives a short burst of Fire Resistance.

---

## How to build the mod for FREE using GitHub (no Java or coding needed)

You do **not** need to install Java or any build tools. GitHub will build the `.jar` for you automatically.

### Steps

1. **Create a GitHub account** at https://github.com (free).
2. Click the **+** in the top-right → **New repository**. Give it any name, keep it **Public**, and click **Create repository**.
3. On the new empty repo page, click the link **"uploading an existing file"**.
4. **Extract the downloaded zip** of this mod on your computer.
5. **⚠️ macOS users — IMPORTANT:** The `.github` folder is **invisible** by default in Finder. You MUST reveal hidden files or the build will never run.
   - Open the extracted folder in Finder.
   - Press **Cmd + Shift + .** (period) to show hidden files. You should now see the `.github` folder appear.
6. Go **INSIDE** the extracted folder. Select **ALL files and folders** inside it — including the hidden **`.github`** folder, `src`, `build.gradle`, `gradle.properties`, `settings.gradle`, etc.
   - **Drag the CONTENTS** into the GitHub upload area — **do NOT drag the outer folder itself**, drag everything from *inside* it.
7. Scroll down and click **Commit changes**.
8. Click the **Actions** tab at the top of your repo.
9. Wait about **2 minutes** for the build to finish (green checkmark).
10. Click the completed workflow run → scroll to **Artifacts** → download **mod-jar**.
11. Unzip it to get the `.jar`, then copy the `.jar` into your `.minecraft/mods/` folder.

> You must have **Fabric Loader** and the **Fabric API** installed for Minecraft 1.20.1.

### ⚠️ Reminder for macOS
If you did not press **Cmd + Shift + .** in Finder before selecting files, the hidden `.github` folder will not be uploaded, the GitHub Actions workflow will not exist, and **the build will never run**. Always reveal hidden files first!