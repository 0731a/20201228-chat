let express = require('express');
let router = express.Router();
const models = require('../models');

router.get('/', function(req,res,next){
    res.render('public_chat');
});

router.post('/makeBoard', function(req,res,next){
    let body = req.body;
    let user_idx = body.idx;
    let text = body.text;

   models.mbti_type.findOne({while:{type:req.body.type}})
   .then( function(data){
      res.json({result:data.dataValues.type, message:"이미 사용중인 아이디 입니다."});

})
});

router.post('/deleteBoard', function(req,res,next){
    let body = req.body;
    let user_idx = body.idx;
    let text = body.text;

   models.mbti_type.findOne({while:{type:req.body.type}})
   .then( function(data){
      res.json({result:data.dataValues.type, message:"이미 사용중인 아이디 입니다."});

})
});


router.post('/getBoardList', function(req,res,next){

   models.mbti_type.findOne({while:{type:req.body.type}})
   .then( function(data){
      res.json({result:data.dataValues.type, message:"이미 사용중인 아이디 입니다."});

})
});

router.post('/postInBoard', function(req,res,next){

    models.mbti_type.findOne({while:{type:req.body.type}})
    .then( function(data){
       res.json({result:data.dataValues.type, message:"이미 사용중인 아이디 입니다."});
 
 })
 });

router.post('/deleteInBoard', function(req,res,next){

    models.mbti_type.findOne({while:{type:req.body.type}})
    .then( function(data){
       res.json({result:data.dataValues.type, message:"이미 사용중인 아이디 입니다."});
 
})
});

router.post('/postInPost', function(req,res,next){

    models.mbti_type.findOne({while:{type:req.body.type}})
    .then( function(data){
       res.json({result:data.dataValues.type, message:"이미 사용중인 아이디 입니다."});
 
})
});

router.post('/deleteInPost', function(req,res,next){

    models.mbti_type.findOne({while:{type:req.body.type}})
    .then( function(data){
       res.json({result:data.dataValues.type, message:"이미 사용중인 아이디 입니다."});
 
})
});
 


module.exports = router;