const express = require('express');
const models = require("./models");
const app = express(); /* app 제작 */
const port = 5500;
const bodyParser = require('body-parser');

//application/x-www-form-urlencoded 데이터를 분석해서 가져오게 해줌
app.use(bodyParser.urlencoded({ extended: true }));
// application/json파일을 분석해서 가져올수 있게 함
app.use(bodyParser.json());


console.log("server start");

const sequelize = require('./models').sequelize;
sequelize.sync();


app.get('/sign_up', function(req, res, next) {
  console.log("sign in");
  //res.render("user/signup");
  models.user.create({
    name: "안서영2",
    email: "98_073@anver.com",
    password: "1234"
  })
});

const cookieParser = require('cookie-parser');
app.use(cookieParser());

app.post("/sign_up", function(req,res,next){
  let body = req.body;
  console.log("sign in");

  models.user.create({
    name: body.userName,
    email: body.userEmail,
    password: body.password
  })
  .then( result => {
    res.redirect("/users/sign_up");
  })
  .catch( err => {
    console.log(err)
  })
})

app.listen(port, () => {
    /*포트 3000에서 앱 실행*/
    console.log(`Example app listening at http://localhost:${port}`);
  });
  