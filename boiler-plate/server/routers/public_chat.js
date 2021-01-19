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

    let result = models.public_chat.create({
        user_idx: user_idx,
        text: text
      })
    
      res.redirect("/getMessage");

});


router.post('/getMessage', function(req,res,next){

   models.mbti_type.findAll({while:{type:req.body.type}})
   .then( function(data){
      res.json({result:"sucess", data:dataValues});

})
});


module.exports = router;