# gtlite_extended 本地依赖清单

`libs/*.jar` 不进版本库（`.gitignore` 已忽略），但编译与 `runClient` 必需。
版本严格对齐整合包 GregTechLite/GregTech-Lite-Modpack（MC 1.12.2）。

| 文件名 | 版本 | 来源（相对 `mods/`） | 大小（字节） | SHA-256 |
|---|---|---|---|---|
| `gregtech-1.12.2-master-2901.jar` | GTCEu master #2901（mcmod.info 2.8.7-beta） | `mods/gregtech-1.12.2-master-%232901.jar` | 14,054,011 | `b9fea8ac4599f04348c12611e515d43dc15819a9b11df9bfbc1495e7e123b317` |
| `gtlitecore-1.0.0.jar` | GregTech Lite Core 1.0.0 | `mods/gtlitecore-1.0.0.jar` | 15,132,751 | `a41618aa2397c1a268e59a619b58faba610ff0e0d485f289a2ffeb763c604337` |
| `morphismlib-1.12.2-1.0.0.jar` | Morphism Lib 1.0.0（GTLite Core 渲染依赖） | `mods/morphismlib-1.12.2-1.0.0.jar` | 139,150 | `39e6271853929d13a73e62dea72bb13392bf3c35734b37d518fc5d98bba3b233` |

下载基址：`https://raw.githubusercontent.com/GregTechLite/GregTech-Lite-Modpack/main/mods/`

```bash
BASE="https://raw.githubusercontent.com/GregTechLite/GregTech-Lite-Modpack/main/mods"
curl -fsSL -o "libs/gregtech-1.12.2-master-2901.jar" "$BASE/gregtech-1.12.2-master-%232901.jar"
curl -fsSL -o "libs/gtlitecore-1.0.0.jar"               "$BASE/gtlitecore-1.0.0.jar"
curl -fsSL -o "libs/morphismlib-1.12.2-1.0.0.jar"       "$BASE/morphismlib-1.12.2-1.0.0.jar"
```

注意：GTCEu 必须用整合包随包的自编译 #2901（CurseForge 5121638 过旧，缺 GTLite Core 依赖的
`MTEManager$MTERegistryEvent` 等类）；下载后文件名不能带 `#`，`dependencies.gradle` 用的是重命名后的名字。
