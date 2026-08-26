# Graph Report - terminal  (2026-08-16)

## Corpus Check
- 20 files · ~4,876 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 105 nodes · 167 edges · 17 communities (14 shown, 3 thin omitted)
- Extraction: 95% EXTRACTED · 5% INFERRED · 0% AMBIGUOUS · INFERRED: 8 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `65eccf9a`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- ReceiverDB
- TerminalAdapter
- TerminalView
- MainActivity
- BaseView
- JSONObject
- User instructions — AUTHORITATIVE. These override default behavior and must be followed exactly.
- ExampleInstrumentedTest.java
- gradlew
- ExampleUnitTest.java
- install.sh
- pre-commit

## God Nodes (most connected - your core abstractions)
1. `ReceiverDB` - 19 edges
2. `TerminalAdapter` - 13 edges
3. `TerminalView` - 12 edges
4. `BaseView` - 10 edges
5. `MainActivity` - 8 edges
6. `LogsView` - 7 edges
7. `User instructions — AUTHORITATIVE. These override default behavior and must be followed exactly.` - 4 edges
8. `MinimaNotifyReceiver` - 4 edges
9. `ExampleInstrumentedTest` - 3 edges
10. `ExampleUnitTest` - 2 edges

## Surprising Connections (you probably didn't know these)
- `LogsView` --inherits--> `BaseView`  [EXTRACTED]
  app/src/main/java/org/minimarex/terminal/LogsView.java → app/src/main/java/org/minimarex/terminal/BaseView.java
- `TerminalAdapter` --references--> `BaseView`  [EXTRACTED]
  app/src/main/java/org/minimarex/terminal/TerminalAdapter.java → app/src/main/java/org/minimarex/terminal/BaseView.java
- `TerminalView` --inherits--> `BaseView`  [EXTRACTED]
  app/src/main/java/org/minimarex/terminal/TerminalView.java → app/src/main/java/org/minimarex/terminal/BaseView.java
- `LogsView` --references--> `ReceiverDB`  [EXTRACTED]
  app/src/main/java/org/minimarex/terminal/LogsView.java → app/src/main/java/org/minimarex/terminal/receiver/ReceiverDB.java
- `MainActivity` --references--> `TerminalAdapter`  [EXTRACTED]
  app/src/main/java/org/minimarex/terminal/MainActivity.java → app/src/main/java/org/minimarex/terminal/TerminalAdapter.java

## Import Cycles
- None detected.

## Communities (17 total, 3 thin omitted)

### Community 0 - "ReceiverDB"
Cohesion: 0.22
Nodes (8): Context, Override, MinimaNotifyReceiver, Override, ReceiverDB, BroadcastReceiver, Intent, SQLiteDatabase

### Community 1 - "TerminalAdapter"
Cohesion: 0.24
Nodes (5): Override, View, TerminalAdapter, PagerAdapter, ViewGroup

### Community 2 - "TerminalView"
Cohesion: 0.33
Nodes (6): Activity, MinimaAPI, TextView, TerminalView, EditText, ScrollView

### Community 3 - "MainActivity"
Cohesion: 0.33
Nodes (6): MinimaAPI, Override, MainActivity, AppCompatActivity, Bundle, ViewPager

### Community 4 - "BaseView"
Cohesion: 0.43
Nodes (3): BaseView, Activity, View

### Community 5 - "JSONObject"
Cohesion: 0.18
Nodes (9): Activity, Override, TextView, LogsView, Context, Cursor, JSONArray, JSONObject (+1 more)

### Community 6 - "User instructions — AUTHORITATIVE. These override default behavior and must be followed exactly."
Cohesion: 0.40
Nodes (4): Remotes — `origin` is the fork, `upstream` is Spartacus, RULE 0 (highest priority) — Follow the user's explicit instructions. They are BLOCKING, not suggestions., User instructions — AUTHORITATIVE. These override default behavior and must be followed exactly., Versioning guardrail — every code change ships with a version bump

### Community 7 - "ExampleInstrumentedTest.java"
Cohesion: 0.60
Nodes (3): ExampleInstrumentedTest, Test, RunWith

### Community 8 - "gradlew"
Cohesion: 0.60
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **4 isolated node(s):** `install.sh script`, `RULE 0 (highest priority) — Follow the user's explicit instructions. They are BLOCKING, not suggestions.`, `Remotes — `origin` is the fork, `upstream` is Spartacus`, `Versioning guardrail — every code change ships with a version bump`
  These have ≤1 connection - possible missing edges or undocumented components.
- **3 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `BaseView` connect `BaseView` to `TerminalAdapter`, `TerminalView`, `JSONObject`?**
  _High betweenness centrality (0.183) - this node is a cross-community bridge._
- **Why does `ReceiverDB` connect `ReceiverDB` to `JSONObject`?**
  _High betweenness centrality (0.167) - this node is a cross-community bridge._
- **Why does `TerminalAdapter` connect `TerminalAdapter` to `MainActivity`, `BaseView`?**
  _High betweenness centrality (0.166) - this node is a cross-community bridge._
- **What connects `install.sh script`, `RULE 0 (highest priority) — Follow the user's explicit instructions. They are BLOCKING, not suggestions.`, `Remotes — `origin` is the fork, `upstream` is Spartacus` to the rest of the system?**
  _4 weakly-connected nodes found - possible documentation gaps or missing edges._