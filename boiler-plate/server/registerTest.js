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

app.use('/user',require('./routers/user'));


console.log("server start");
/*
const sequelize = require('./models').sequelize;
sequelize.sync();
*/

app.get('/sign_up', async function(req, res, next) {
  console.log("sign in");
  res.render("signup");

});
/*
app.post('/user/DuplicateId', function(req,res){
  console.log('DuplicateId');
    models.User.findOne({where:{id:req.body.id}})
    .then( function(data){
        if( (data== null || data == undefined) === false)
          res.json({result:true, message:"이미 사용중인 아이디 입니다."});
        else
          res.json({result:false, message:"이미 사용중인 아이디 입니다."});
    })
});
*/



app.post("/sign_up", function(req,res,next){

  let body = req.body;

  let inputPassword = body.password;
  let salt = Math.round((new Date().valueOf()*Math.random())) + "";
  let hashPassword = crypto.createHash("sha512").update(inputPassword+salt).digest("hex");
  
  let result = models.user.create({
    id: body.id,
    nickName: body.nickName,
    email: body.email,
    password: hashPassword,
    age: body.age,
    salt: salt,
    mbti_idx: 1,
  })

  res.redirect("/sign_up");
})

app.listen(port, () => {
    /*포트 3000에서 앱 실행*/
    console.log(`Example app listening at http://localhost:${port}`);
  });
  