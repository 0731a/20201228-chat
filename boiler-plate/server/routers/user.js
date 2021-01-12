const crypto = require('crypto');
let express = require('express');
let router = express.Router();
const models = require('../models');

router.get('/', function(req,res,next){
    res.render('user');
});

router.post('/DuplicateId', function(req,res){
  console.log('DuplicateId');
    models.User.findOne({where:{id:req.body.id}})
    .then( function(data){
        if( (data== null || data == undefined) === false)
          res.json({result:true, message:"이미 사용중인 아이디 입니다."});
        else
          res.json({result:false, message:"사용 가능한 아이디 입니다."});
    })
});

router.post('/DuplicateEmail', function(req,res){
    models.member.findOne({where:{id:req.body.email}})
    .then( function(data){
        if( (data== null || data == undefined) === false)
          res.json({result:true, message:"이미 사용중인 이메일 입니다."});
        else
        res.json({result:false, message:"사용가능한 이메일 입니다."});
    })

});

router.get('/sign_up', async function(req, res, next) {
  console.log("sign in");
  res.render("signup");

});


router.post('/register', function(req,res){
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
});

module.exports = router;