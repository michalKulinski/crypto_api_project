# Cryptocurrency cantor

Application uses pro.coinmarketcap.com API.
First of all you should log in to above service and get your API Key.

Api Key should be put to the ApiConfig.class (in the future it will be changed to application.properties)

# How to start application

App can be started via IDE. Just call App.class and then you can go to your browser or Postman(recomended).

Call following URL to display response for your chosen cryptocurrency:

http://localhost:8080/currencies?currency=$(Currency)

You can use any cryptocurrency in following short naming convention:
ex.
BTC, ETH, USDT etc

Response shows you notifications for your chosen cryptocurrency.

