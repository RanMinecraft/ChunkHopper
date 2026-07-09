# ChunkHopper — 区块漏斗

> 🏗️ 高性能区块漏斗插件  
> ✨ 减少漏斗实体，用区块级收集提升服务器性能

## 📖 描述

服务器因漏斗过多而卡顿？**ChunkHopper** 允许玩家将漏斗改名为"区块漏斗"后放置，即可收集**整个区块**的掉落物，大幅减少漏斗实体数量，从根源上解决卡顿问题。

支持 **Folia** 线程安全服务端，兼容最新 Paper API。

## ✨ 功能特色

- 🧲 **区块收集** — 一个漏斗覆盖整个区块，自动收集掉落物
- ⛏️ **多触发方式** — 支持活塞推出、生物死亡、方块破坏、作物生长、爆炸等多种事件触发收集
- 📦 **物品过滤** — 通过 `chunk.yml` 自定义每种漏斗的收集列表
- 🖼️ **展示架过滤（1.21.9+）** — 漏斗旁放置展示架，仅允许收集展示架上的物品
- 🔢 **区块限制** — 可分别限制每个区块的漏斗数量和红石数量，防止滥用
- 🚫 **禁用世界** — 指定世界禁止放置区块漏斗
- 🎯 **ActionBar 反馈** — 放置/破坏时实时显示当前区块数量
- 🔄 **热重载** — `/ch reload` 一键重载配置
- 🧵 **Folia 兼容** — 完美支持 Folia 线程安全服务端

## 📥 安装

1. 将插件 `.jar` 放入服务端 `plugins/` 目录
2. 重启服务器或使用 `/reload`
3. 配置文件会自动生成

## 🎮 使用方法

1. 按 `F3+G` 显示区块边界
2. 将漏斗放入 **铁砧** 并改名为 **区块漏斗**（名称可在 `chunk.yml` 中自定义）
3. 将漏斗放置在目标区块中即可自动收集全区块掉落物

> ⚠️ **注意**：村民农场及仙人掌机暂未适配

## 📋 指令

| 指令 | 权限 | 描述 |
|------|------|------|
| `/ch reload` | `ch.admin` | 🔄 重载配置文件 |
| `/chunkhopper reload` | `ch.admin` | 🔄 同上 |

## ⚙️ 配置文件

### config.yml

```yaml
# 启用插件
enable: true

# 插件前缀
prefix: "&b[区块漏斗]"

# 收集触发开关
piston: true     # 活塞推出物品后收集
entity: true     # 生物死亡掉落物品后收集
block: true      # 方块被破坏后收集
grow: true       # 作物生长后收集
explode: true    # 爆炸破坏方块后收集

# 禁用世界
disable-world:
  - world_nether
  - world_the_end

# 漏斗数量限制
chunk: true      # 启用限制
limit: 32        # 每区块最大漏斗数
delay: 30        # 方块破坏后等待多久检测（tick）

# 红石数量限制
redstone: true         # 启用限制
redstone-limit: 128    # 每区块最大红石及红石火把数

# 物品过滤 (1.21.9+)
# 在漏斗周围放置展示架可让该漏斗只收集展示架上的物品
hopper-filter: false
```

### chunk.yml

自定义区块漏斗名称及其收集的物品列表：

```yaml
'区块漏斗':
  - POTATO
  - CARROT
  - WHEAT
  - WHEAT_SEEDS
  - BEETROOT
  - BEETROOT_SEEDS
  - NETHER_WART
  - MELON_SLICE
  - PUMPKIN
  - BAMBOO
  - SUGAR_CANE

'区块漏斗2':
  - BEDROCK
```

## 📊 数据统计

[![bStats](https://bstats.org/signatures/bukkit/ChunkHopper.svg)](https://bstats.org/plugin/bukkit/ChunkHopper/28105)

## 📄 许可证

本项目基于 [GNU Affero General Public License v3.0](LICENSE) 开源。
