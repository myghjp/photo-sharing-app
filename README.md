# 共有写真アプリ

## 概要

このプロジェクトは、個人の端末で所持している写真を共有するために  
Webアプリを使用してコミュニケーションをとるために作成しました。

## 使用技術

+ フロントエンド html / css / bootstrap v5.3.3  
+ バックエンド Java v21 / Springboot v3.3.1 / MyBatis v3.0.3  
+ データベース MySQL v8.0.42  
+ ビルドツール Maven
  
## 機能

+ ユーザー認証とアカウント管理
+ グループの作成や選択

  + グループ内メンバーが使用できる機能
  
    + ダッシュボードでグループ内のメンバーとアルバムを一覧表示
    + メンバーの閲覧
    + 掲示板の投稿内容の閲覧、投稿や削除
    + アルバム選択後にアルバム内の写真の閲覧、追加や削除
  
  + グループ内管理者だけがその他に使用できる機能

    + グループの削除
    + メールアドレスを使用したメンバーの追加 メンバーの削除
    + アルバム管理  
    + アルバム内にメンバーが追加した写真を削除
    
## 注意事項

+ このWebアプリはブラウザでの使用かつMySQLがインストール済であることを想定しています  

  + Webアプリ起動時  
  
    + MySQLにデータベースが作成されます DB名: photo_app  
    + 写真保存用のフォルダが作成されます C:/Users/user/PhotoSharingApp/photo/[←任意のディレクトリに変更]  


ーーーーーーーーーパターンの確認javac/dirーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーーー  
    1.本リポジトリを任意のディレクトリにクローンする  
    git clone https://github.com/myghjp/photo-sharing-app.git  
    フォルダがフォルダの中にファイルやフォルダがあるとcloneできない。←確認  

    2.写真をした画像の保存先として任意のディレクトリを用意し、  
    そのパスを application.properties (内) の app.media.directory に設定する  
    [application.properties]  
    # 投稿画像保存先ディレクトリ(末尾に'/'を入れる
    app.media.directory=C:/`任意のディレクトリ`/←書き換え可

    3.クローンしたリポジトリ内のプロジェクトを Eclipse 等の IDE で開き、Spring Boot アプリとして実行する。
    またはプロジェクトのルートディレクトリでコマンドラインから下記のように maven で実行する。

    cd C:/Users/user/photo-sharing-app/PhotoSharingApp  

    ./mvnw spring-boot:run
    mvnw.cmd spring-boot:run[Windowsでの起動]  

    Windows11での確認を記入  

    4.ブラウザから http://localhost:8080/ を開く
    






