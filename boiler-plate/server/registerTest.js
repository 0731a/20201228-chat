const express = require('express');
const models = require("./models");
const app = express(); /* app 제작 */
const port = 3000;
const bodyParser = require('body-parser');
const crypto = require('crypto'); //비밀번호 암호화 위함 
const cookieParser = require('cookie-parser');
app.use(cookieParser());

//application/x-www-form-urlencoded 데이터를 분석해서 가져오게 해줌
app.use(bodyParser.urlencoded({ extended: true }));
// application/json파일을 분석해서 가져올수 있게 함
app.use(bodyParser.json());




console.log("server start");
/*
const sequelize = require('./models').sequelize;
sequelize.sync();
*/






app.listen(port, () => {
    /*포트 3000에서 앱 실행*/
    console.log(`Example app listening at http://localhost:${port}`);
  });
  