# Coolblue assessment

Hey Coolblue team 👋

Quick read on what you asked for vs what I shipped, and how the project hangs together.

---

## What you asked for

| Requirement | How it went                                                                                                                                                                                                             |
|-------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Kotlin** | Yep                                                                                                                                                                                                                     |
| **Compose** | Yep                                                                                                                                                                                                                     |
| **Online repo** | If you’re reading this, it’s online 🙂                                                                                                                                                                                  |
| **Design patterns** | I’d have *loved* full CLEAN + modules but that felt like a lot for this assessment, so I went **MVVM**, **Repository + interface**, and a **singleton-style** `Retrofit` holder. Mappers for DTO to domain, usual stuff |
| **Tests** | **Unit tests** for the mappers and for the RepositoryImpl. **UI tests:** honestly I skipped them; wiring up a proper Compose/robot thing would’ve eaten way to much of my time                                          |
| **Open source** | **Retrofit** (API). I also looked at Ktor but stuck with Retrofit because it’s what I use at work. **Gson**, **Coil** for images, **AndroidX / Compose / Material 3**                                                   |
| **Images** | Yes, via **Coil**                                                                                                                                                                                                       |
| **AI tools** | See below. I still get what’s in the repo and I’m happy to talk through it                                                                                                                                              |

---

## Architecture (the short version)

- **MVVM:** Compose + `ViewModel` + `StateFlow`
- **Repository:** interface + `ProductRepositoryImpl` in front of the API
- **Mappers:** API models to domain models
- **Not** a big CLEAN / multi-module split this round. That would be the “if I had another week” version

---

## Tech stack

Kotlin · Compose · Material 3 · Retrofit · Gson · Coil · AndroidX

---

## AI usage (being straight about it)

- **Android Studio autocomplete:** sure, I use it; quality is… hit or miss imho
- **Cursor:** helped fix typos / package-Gradle mess from early setup, and nudged the `libs.versions.toml` + `build.gradle.kts` dependency stuff. I still read and own the code; ask me anything in the interview

---

## If I had more time

- **Koin** for DI so I don’t hand-roll factories
- **UI tests** with the **robot pattern**: a colleague pitched that recently and I actually want to try it properly
- The UI is almost entirely Material and it looks cheap. I could lose myself in finetuning UI so i chose to focus on different tasks and left the Ui for what it is. Would upgrade the UI to look like CoolBlue if i had more time.

---

## Build & test

You’ll need a normal Android dev setup (SDK / Android Studio).

```bash
./gradlew assembleDebug
```

```bash
./gradlew testDebugUnitTest
```
