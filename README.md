# 共有写真アプリ

## 概要

このプロジェクトは、個人の端末で所持している写真を共有するために  
Webアプリを使用してコミュニケーションをとるために作成しました。

## 使用技術

+ フロントエンド HTML / CSS / Bootstrap v5.3.3  
+ バックエンド Java v21 / Springboot v3.3.1 / MyBatis v3.0.3  
+ データベース MySQL v8.0.42  
+ ビルドツール Maven
  
## 機能紹介

このWebアプリでグループを作成し、指定した人物とだけ写真の共有が可能です。  
またコミュニケーションをとるためにグループ内で掲示板の機能が使用できます。

+ グループ内でできること

    + メンバーの確認
    + 掲示板の閲覧、投稿や削除
    + 写真の閲覧、追加や削除
  
+ グループ内管理者だけが使える機能を分けています。

    + グループの削除
    + メールアドレスを使用したメンバーの追加や削除
    + メンバーが追加した管理者以外の写真を削除
    
## Webアプリ使用方法と注意事項

+ **このWebアプリはブラウザでの使用かつMySQLがインストール済であることを想定しています。**
+ テスト環境 OS: Windows11 25H2で確認しました。  
 
  1.本リポジトリを任意のディレクトリにクローンします。
    ```
    git clone https://github.com/myghjp/photo-sharing-app.git
    ```  
  
  2.画像の保存先として任意のディレクトリを用意し、そのパスを  
    application.properties の app.media.directory に設定します。
    
    [application.properties]
    
    任意のディレクトリに変更し、写真保存用のフォルダを作成してください。   
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


