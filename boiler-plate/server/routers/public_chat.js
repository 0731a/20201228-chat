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
      res.redirect("/getMessage");

});


router.post('/getMessage', function(req,res,next){
    console.log("getMessage");

   models.public_chat.findAll({  include: [models.user]})
   .then( function(data){
      console.log(data);
      res.json({result:"sucess", data:data.dataValues});

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