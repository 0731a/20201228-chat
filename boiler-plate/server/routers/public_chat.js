let express = require('express');
let router = express.Router();
const models = require('../models');

router.get('/', function(req,res,next){
    res.render('public_chat');
});

router.post('/writeMessage', function(req,res,next){
    let body = req.body;
    let user_idx = body.idx;
    let text = body.text;

    console.log(text);

    let result = models.public_chat.create({
        writer: user_idx,
        text: text
      })    
      res.redirect("/publicChat/getMessage");

});


router.post('/getMessage', function(req,res,next){
    console.log("getMessage");

   models.public_chat.findAll({  include: [{
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
/*
    models.public_chat.findAll({  include: [models.mbti_type]})
   .then( function(data){
      console.log(data.dataValues);
      res.json({result:"sucess", data:data.dataValues});

})
*/
});


module.exports = router;