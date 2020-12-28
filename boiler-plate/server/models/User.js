const mongoose = require('mongoose');
const bcrypt = require('bcrypt');
const saltRounds = 10;

const userSchema = mongoose.Schema({
  name: {
    type: String,
    maxlength: 50,
  },
  email: {
    type: String,
    trim: true /*빈칸 제거*/,
    unique: 1 /*중복 불가*/,
  },
  password: {
    type: String,
    minlength: 5,
  },
  lastname: {
    type: String,
    maxlength: 50,
  },
  role: {
    type: Number,
    default: 0,
  },
  image: String,
  token: {
    type: String,
  },
  tokenExp: {
    /*token 유효기간*/ type: Number,
  },
});

// save 이전 처리 (몽고스 제공)
userSchema.pre('save', function (next) {
  var user = this;

  // 비밀 번호가 변경될 때
  if (user.isModified('password')) {
    // 비밀번호 암호화

    bcrypt.genSalt(saltRounds, function (err, salt) {
      if (err) return next(err);

      bcrypt.hash(user.password, salt, function (err, hash) {
        // user.password -> 비밀번호 원본 ( plain password)
        //  비밀번호 DB에 해시를 저장합니다.
        if (err) return next(err);
        user.password = hash;
        next();
      });
    });
  } else {
    next();
  }
});

// 비밀번호 일치 확인 용 메소드 생성
userSchema.methods.comparePassword = function (plainPassword, callback) {
  // 암호화 되어있는 비밀번호는 복구 불가능
  // 입력받은 plain 비밀번호를 암호화 하여 비교해야함
  bcrypt.compare(plainPassword, this.password, function (err, isMatch) {
    if (err) return callback(err);
    callback(null, isMatch);
  });
};

const jwt = require('jsonwebtoken');
userSchema.methods.generateToken = function (callback) {
  var user = this;

  //jsonwebtoken을 이용해서 token을 생성하기
  var token = jwt.sign(user._id.toHexString(), 'secretToken'); //secretToken은 임의로 넣은 값, toHexString은 sign 메소드가 첫번째 인자로 plain objec를 요구하기 때문
  // user._id + 'secretToken' = token
  // secretToken으로 user._id 도출 가능

  user.token = token;
  user.save(function (err, user) {
    if (err) return callback(err);
    callback(null, user);
  });
};

userSchema.statics.findByToken = function (token, callback) {
  var user = this;

  // 토큰을 decode
  jwt.verify(token, 'secretToken', function (err, decoded) {
    //유저 아이디를 이용해서 유저를 찾는다.
    // 클라이언트에서 가져온 token과 db에 보관된 토큰이 일치하는 지 확인

    user.findOne({ _id: decoded, token: token }, function (err, user) {
      // find one - mongoDB 제공 함수
      if (err) return callback(err);
      callback(null, user);
    });
  });
};
const User = mongoose.model('User', userSchema);

module.exports = { User }; /*다른 곳에서 사용할 수 있게 함*/
