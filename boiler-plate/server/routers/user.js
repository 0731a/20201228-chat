const crypto = require('crypto');
let express = require('express');
let router = express.Router();
const models = require('../models');

router.get('/', function(req,res,next){
    res.render('user');
});

router.post('DuplicateId', function(req,res){
  console.log('DuplicateId');
    models.member.findOne({where:{id:req.body.id}})
    .then( function(data){
        if( (data== null || data == undefined) === false)
          res.json({result:true, message:"이미 사용중인 아이디 입니다."});
        else
          res.json({result:false, message:"이미 사용중인 아이디 입니다."});
    })
});

router.post('DuplicateEmail', function(req,res){
    models.member.findOne({where:{id:req.body.email}})
    .then( function(data){
        if( (data== null || data == undefined) === false)
          res.json({result:true, message:"이미 사용중인 이메일 입니다."});
        else
        res.json({result:false, message:"이미 사용중인 이메일 입니다."});
    })

});

router.post('register', function(req,res){

});

module.exports = router;