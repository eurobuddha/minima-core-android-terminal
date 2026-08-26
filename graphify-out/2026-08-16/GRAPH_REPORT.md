# Graph Report - terminal  (2026-08-09)

## Corpus Check
- 18 files · ~4,505 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 100 nodes · 164 edges · 15 communities (14 shown, 1 thin omitted)
- Extraction: 95% EXTRACTED · 5% INFERRED · 0% AMBIGUOUS · INFERRED: 8 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `df8b7c37`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- ReceiverDB
- TerminalAdapter
- TerminalView
- MainActivity
- BaseView
- LogsView
- User instructions — AUTHORITATIVE. These override default behavior and must be followed exactly.
- ExampleInstrumentedTest.java
- gradlew
- ExampleUnitTest.java

## God Nodes (most connected - your core abstractions)
1. `ReceiverDB` - 19 edges
2. `TerminalAdapter` - 13 edges
3. `TerminalView` - 12 edges
4. `BaseView` - 10 edges
5. `MainActivity` - 8 edges
6. `LogsView` - 7 edges
7. `MinimaNotifyReceiver` - 4 edges
8. `User instructions — AUTHORITATIVE. These override default behavior and must be followed exactly.` - 3 edges
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

## Communities (15 total, 1 thin omitted)

### Community 0 - "ReceiverDB"
Cohesion: 0.16
Nodes (12): Context, Override, MinimaNotifyReceiver, Context, Override, ReceiverDB, BroadcastReceiver, Cursor (+4 more)

### Community 1 - "TerminalAdapter"
Cohesion: 0.22
Nodes (5): Override, View, TerminalAdapter, PagerAdapter, ViewGroup

### Community 2 - "TerminalView"
Cohesion: 0.33
Nodes (6): Activity, MinimaAPI, TextView, TerminalView, EditText, ScrollView

### Community 3 - "MainActivity"
Cohesion: 0.39
Nodes (6): MinimaAPI, Override, MainActivity, AppCompatActivity, Bundle, ViewPager

### Community 4 - "BaseView"
Cohesion: 0.43
Nodes (3): BaseView, Activity, View

### Community 5 - "LogsView"
Cohesion: 0.29
Nodes (5): Activity, Override, TextView, LogsView, JSONArray

### Community 6 - "User instructions — AUTHORITATIVE. These override default behavior and must be followed exactly."
Cohesion: 0.50
Nodes (3): Remotes — `origin` is the fork, `upstream` is Spartacus, RULE 0 (highest priority) — Follow the user's explicit instructions. They are BLOCKING, not suggestions., User instructions — AUTHORITATIVE. These override default behavior and must be followed exactly.

### Community 7 - "ExampleInstrumentedTest.java"
Cohesion: 0.60
Nodes (3): ExampleInstrumentedTest, Test, RunWith

### Community 8 - "gradlew"
Cohesion: 0.60
Nodes (3): gradlew script, die(), warn()

## Knowledge Gaps
- **2 isolated node(s):** `RULE 0 (highest priority) — Follow the user's explicit instructions. They are BLOCKING, not suggestions.`, `Remotes — `origin` is the fork, `upstream` is Spartacus`
  These have ≤1 connection - possible missing edges or undocumented components.
- **1 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `BaseView` connect `BaseView` to `TerminalAdapter`, `TerminalView`, `LogsView`?**
  _High betweenness centrality (0.202) - this node is a cross-community bridge._
- **Why does `ReceiverDB` connect `ReceiverDB` to `LogsView`?**
  _High betweenness centrality (0.185) - this node is a cross-community bridge._
- **Why does `TerminalAdapter` connect `TerminalAdapter` to `MainActivity`, `BaseView`?**
  _High betweenness centrality (0.183) - this node is a cross-community bridge._
- **What connects `RULE 0 (highest priority) — Follow the user's explicit instructions. They are BLOCKING, not suggestions.`, `Remotes — `origin` is the fork, `upstream` is Spartacus` to the rest of the system?**
  _2 weakly-connected nodes found - possible documentation gaps or missing edges._