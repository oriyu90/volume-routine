# Volume Routine (ボリュームルーチン)

Volume Routine is a lightweight, background-running Android application that automates your device's volume and Do Not Disturb (DND) settings based on specific triggers such as Time, Wi-Fi connection, or manual execution.

---

## English

### What's New in v1.1.0

* Fixed a crash that could occur on Android 7.0/7.1 devices when the background service started.
* Fixed a crash that could occur when changing the ringtone volume without Do Not Disturb access granted.
* Routines can no longer be saved without a usable trigger (missing time, days, or Wi-Fi SSID) that would otherwise silently never run.
* Do Not Disturb permission status now refreshes automatically when you return to the app.
* Deleting a routine now asks for confirmation first.
* Internal stability and data-safety improvements (background service cleanup, safer database upgrades).

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

Download the pre-built APK from the [Releases page](https://github.com/oriyu90/volume-routine/releases/latest):

```
Volume.routine.v1.1.0.apk
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

## v1.1.0の新機能・修正内容

* Android 7.0/7.1端末でバックグラウンドサービス起動時にクラッシュすることがあった不具合を修正
* おやすみモード（DND）の権限を許可していない状態で着信音量を変更するとクラッシュすることがあった不具合を修正
* 時間・曜日・Wi-Fi SSIDが未設定のまま保存でき、無言で一切発火しないルーチンが作成できてしまう不具合を修正
* 設定画面からアプリに戻った際、おやすみモードの許可状態を自動で再確認するように変更
* ルーチン削除時に確認ダイアログを表示するように変更
* 内部的な安定性・データ保護の改善（バックグラウンドサービスのクリーンアップ、より安全なデータベース更新処理）

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

[Releasesページ](https://github.com/oriyu90/volume-routine/releases/latest)からビルド済みAPKをダウンロードしてください。

```
Volume.routine.v1.1.0.apk
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

## v1.1.0 新增功能与修复

* 修复了在 Android 7.0/7.1 设备上后台服务启动时可能崩溃的问题
* 修复了在未授予勿扰模式权限的情况下更改铃声音量可能导致崩溃的问题
* 修复了未设置时间、星期或 Wi-Fi SSID 也能保存日常、导致其永远不会触发却没有任何提示的问题
* 从设置页面返回应用时，现在会自动重新检查勿扰模式的授权状态
* 删除日常前现在会弹出确认对话框
* 内部稳定性与数据安全性改进（后台服务清理、更安全的数据库升级流程）

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

请从 [Releases 页面](https://github.com/oriyu90/volume-routine/releases/latest) 下载编译好的 APK：

```
Volume.routine.v1.1.0.apk
```

复制到 Android 设备即可安装。

---

## License

This application is distributed under the **GPL-3.0 License**.

See `LICENSE.md` for details.

**Developer:** yuki orita
