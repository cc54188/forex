## 1.DB: MySql

目前是建在本地端，連結設定好後，執行專案即可自動  建出table: day_forex。

## 2.批次取得外匯成交資料

執行時，若table內都沒有資料，會把所有的資料都抓進table；若已有資料，則只會insert最新一筆。

## 3.批次的兩個單元測試

1. 執行專案內的ForexApplicationTests.java檔(src/test/...內)，會直接打open api取得資料，並insert進table。
2. 匯入Postman的[外匯openApi排程測試.json]這支檔案後，打API。兩者都會回傳open api取得的最新一筆資料。

## 4.查詢幣別

可匯入Postman的[外匯查詢.json]打API看查詢結果，目前只能查"USD/NTD"，而參數是代入"usd"，才有辦法查到資料，若無符合幣別，會回傳錯誤代碼"E002"。

## 5.查詢幣別的兩個單元測試

##### 1.執行專案內的ForexApplicationTests.java檔時會隨機產生startDate和endDate查詢

   有限制是2023或2024年，不過目前資料只有2023/12~2024/1，大多都會失敗。

##### 2.匯入Postman的[外匯查詢測試.json]
