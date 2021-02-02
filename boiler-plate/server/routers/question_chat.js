let express = require('express');
let router = express.Router();
const models = require('../models');

router.get('/', function(req,res,next){
    res.render('public_chat');
});

router.post('/getQuestion', function(req,res,next){
    models.question_chat.findOne({where:{id:req.body.email}})
    .then( function(data){
        if( (data== null || data == undefined) === false)
          res.json({result:true, message:data.dataValues});
        else
        res.json({result:false, message:"오늘의 질문이 없습니다."});
    })

    res.redirect("/getAnswers");

});


router.post('/writeAnswer', function(req,res,next){
    let body = req.body;
    let user_idx = body.user_idx;
    let quetion_idx = body.question_idx;
    let text = body.text;

    console.log(text);

    let result = models.question_chat_answer.create({
        qustion_idx: question_idx,
        writer: user_idx,
        text: text
      })    
      res.redirect("/getAnswers");

});


router.post('/writeQuestion', function(req,res,next){
    let body = req.body;
    let text = body.text;

    console.log(text);

    let result = models.question_chat_list.create({
        used: false,
        text: text
      })    
      res.redirect("/getAnswers");

});


router.post('/getAnswers', function(req,res,next){
    console.log("getMessage");

   models.question_chat_answer.findAll({  include: [{
       model: models.user,
       attributes: ['nickName', 'mbti_type_idx'],
       include: models.mbti_type
   }]})
   .then( function(data){
      //console.log(data);
      let users = []

      data.forEach( function(public_chat){
          users.push(public_chat.dataValues);
      })
      //console.log(users);
      
      var publicChat=[]
      users.forEach(function (user) {
          publicChat.push({
              idx:user.idx,
              nickName:user.User.nickName,
              mbti:user.User.Mbti_type.type,
              text:user.text,
              createdAt:user.createdAt,
          });
      });

      console.log(publicChat);

      res.json({result:"sucess", data:publicChat});

    })

});


module.exports = router;