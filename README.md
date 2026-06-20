# Volume Routine (ボリュームルーチン)

Volume Routine is a lightweight, background-running Android application that automates your device's volume and Do Not Disturb (DND) settings based on specific triggers such as Time, Wi-Fi connection, or manual execution.

---

## English

### Features

* **Time Trigger**: Automatically change volume settings at a specific time and day(s) of the week.
* **Wi-Fi Trigger**: Automatically change volume settings when connected to a specific Wi-Fi network (SSID).
* **Manual Trigger**: Execute volume changes with a tap of a button.
* **Silent Mode (DND)**: Automatically enable Do Not Disturb mode.
* **Volume Control**: Precisely adjust the Media, Alarm, and Ringtone volumes independently.
* **Revert to Original (New in v1.0.1)**: Set an "End Time" or configure the app to revert volumes back to what they were before the routine was triggered (when disconnecting from Wi-Fi or reaching the end time).
* **Lightweight & Battery Efficient**: Designed to run efficiently in the background.

### How to Use

1. **Grant Permission**

   * When launching the app for the first time, grant **Do Not Disturb (Notification Policy)** access.
   * This permission is required to allow the app to control sound modes.

2. **Create a Routine**

   * Tap the **+** floating action button.
   * Enter a routine name.
   * Select a trigger (`TIME`, `WIFI`, or `MANUAL`).
   * Configure the trigger condition.
   * Optionally configure an End Time or Disconnect condition and enable **Revert to original settings**.
   * Configure the desired volume levels or enable Silent Mode.
   * Save the routine.

3. **Manage Routines**

   * Enable or disable routines.
   * Execute them immediately using **Run Now**.
   * Delete routines from the main screen.

### Installation

A pre-built APK is included in the repository:

```
VolumeRoutine_v1.0.1.apk
```

Transfer it to your Android device and install it directly.

---

# 日本語

## 概要

**Volume Routine** は、Android端末の音量やサイレントモード（おやすみモード / DND）を自動で切り替える軽量なバックグラウンドアプリです。

以下の3種類のトリガーに対応しています。

* 時間
* Wi-Fi接続
* 手動実行

学校・職場・自宅など、利用シーンに合わせて音量設定を自動化できます。

## 主な機能

* **時間トリガー**

  * 指定した曜日・時刻に自動実行

* **Wi-Fiトリガー**

  * 指定したWi-Fi（SSID）へ接続すると自動実行

* **手動トリガー**

  * ボタンを押すだけですぐに実行

* **サイレントモード（DND）**

  * おやすみモードを自動でON

* **個別音量設定**

  * メディア
  * アラーム
  * 着信音

  をそれぞれ独立して設定可能

* **元の状態へ復元（v1.0.1）**

  * 終了時刻
  * Wi-Fi切断

  のどちらかで、実行前の設定へ自動で戻せます。

* **軽量・省電力**

  * バックグラウンドで常時動作してもバッテリー消費を最小限に抑えています。

## 使い方

### 1. 権限を許可

初回起動時に

**「おやすみモード（DND）」へのアクセス**

を許可してください。

この権限がないと音量モードを変更できません。

### 2. ルーチンを作成

* 「＋」ボタンを押す
* 名前を入力
* トリガーを選択

  * TIME
  * WIFI
  * MANUAL
* 条件を設定
* 必要であれば

  * 終了時刻
  * Wi-Fi切断
  * 元の設定へ戻す
* 音量・サイレント設定を指定
* 保存

保存後すぐに有効になります。

### 3. 管理

メイン画面から

* ON / OFF
* Run Now
* 削除

ができます。

## インストール

リポジトリ内にある

```
VolumeRoutine_v1.0.1.apk
```

をAndroid端末へ転送してインストールしてください。

---

# 中文（简体）

## 简介

**Volume Routine** 是一款轻量级 Android 后台自动化应用。

它可以根据以下触发条件自动调整手机音量和勿扰模式（DND）：

* 时间
* Wi-Fi 连接
* 手动执行

非常适合在学校、办公室、家中等不同场景下自动切换音量配置。

## 功能

* **时间触发**

  * 在指定日期和时间自动执行

* **Wi-Fi 触发**

  * 连接指定 Wi-Fi（SSID）后自动执行

* **手动触发**

  * 点击按钮立即执行

* **勿扰模式（DND）**

  * 自动开启勿扰模式

* **独立音量控制**

  * 媒体音量
  * 闹钟音量
  * 铃声音量

  均可分别设置。

* **恢复原始设置（v1.0.1）**

  * 到达结束时间
  * 或断开指定 Wi-Fi

  后自动恢复执行前的音量及系统状态。

* **轻量且省电**

  * 针对后台长期运行进行了优化。

## 使用方法

### 1. 授予权限

首次启动时，请授予：

**勿扰模式（DND）权限**

否则应用无法切换声音模式。

### 2. 创建 Routine

点击 **+**

填写名称

选择触发方式：

* TIME
* WIFI
* MANUAL

配置触发条件。

如有需要，可配置：

* 结束时间
* Wi-Fi 断开
* 恢复原始设置

然后设置音量或勿扰模式并保存。

### 3. 管理

在主界面可以：

* 启用 / 禁用
* Run Now
* 删除 Routine

## 安装

仓库根目录提供：

```
VolumeRoutine_v1.0.1.apk
```

复制到 Android 设备即可安装。

---

## License

This application is distributed under the **GPL-3.0 License**.

See `LICENSE.md` for details.

**Developer:** yuki orita
