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

   models.mbti_type.findOne({while:{type:req.body.type}})
   .then( function(data){
      res.json({result:data.dataValues.type, message:"이미 사용중인 아이디 입니다."});

})
});


router.post('/getMessage', function(req,res,next){

   models.mbti_type.findOne({while:{type:req.body.type}})
   .then( function(data){
      res.json({result:data.dataValues.type, message:"이미 사용중인 아이디 입니다."});

})
});


module.exports = router;