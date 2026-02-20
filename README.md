# 共有写真アプリ

## 概要

このプロジェクトは、個人の端末で所持している写真を共有し、  
コミュニケーションをとるためにWebアプリを作成しました。

## 使用技術

+ フロントエンド HTML / CSS / Bootstrap v5.3.3  
+ バックエンド Java v21 / Springboot v3.3.1 / MyBatis v3.0.3  
+ データベース MySQL v8.0.42  
+ ビルドツール Maven

## 機能紹介

同じグループ内のメンバーと写真の共有やダウンロードが可能で、  
掲示板を使ってコミュニケーションがとれます。  

グループ管理者はメールアドレスを指定したメンバーの追加ができます。  
写真を共有するためのアルバムをグループ管理者が作成します。  
このようにグループやアルバムを管理し、削除することができます。  

作成したアルバムは同じグループ内のメンバーが写真の追加や削除が可能で、  
管理者であればメンバーが追加した写真も削除もできます。  

## 実行方法や実行手順

+ **このWebアプリはブラウザでの使用かつMySQLがインストール済であることを想定しています。**
+ テスト環境 OS: Windows11 25H2で確認しました。  
 
  1.本リポジトリを任意のディレクトリにクローンします。
    ```
    git clone https://github.com/myghjp/photo-sharing-app.git
    ```

  2.画像の保存先として任意のディレクトリを用意し、そのパスを  
    application.properties の app.media.directory に設定します。

    デフォルトではWebアプリ起動時に投稿画像保存先が下記のディレクトリに生成されます。

    ```
    [application.properties]
    app.media.directory=C:/Users/user/PhotoSharingApp/photo/
    ```
    環境に合わせて任意のディレクトリに変更し、写真保存用のフォルダを作成してください。   
    #投稿画像保存先ディレクトリ(末尾に'/'を入れる)
    ```
    app.media.directory=C:/`任意のディレクトリ`/
    ```

  3.Spring Bootアプリを実行するとMySQLにデータベースが作成されます。
  
    + データベース名: photo_app  
    + 同じデータベース名が既に登録されている場合や状況に合わせて  
      application.properties の spring.datasource 以降を書き換えてください。  

  4.クローンしたリポジトリ内のプロジェクトを Eclipse 等の IDE で開き、Spring Boot アプリとして実行  
    またはプロジェクトのルートディレクトリでコマンドラインから下記のように maven で実行します。
  
    ```
    cd C:/Users/user/photo-sharing-app/PhotoSharingApp  
    ```
    ```
    ./mvnw spring-boot:run
    ```
     
  5.ブラウザから http://localhost:8080/ を開く

## Webアプリの使用方法  
  
  ```
  ログインに使用するメールアドレスとパスワードです。(仮)

  アカウント名：ユーザ１
  メールアドレス：user1@example.com
  パスワード：00000000  
  
  アカウント名：ユーザ４
  メールアドレス：user4@example.com
  パスワード：00000000
  ```
 

 


