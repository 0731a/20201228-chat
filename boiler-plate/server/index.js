const express = require('express'); /*익스프레스 모듈 가져옴 */
const app = express(); /* app 제작 */
const port = 3000;

const config = require('./config/key');

const mysqlDbConnection = require('./db/db_con')();
const dbConnection = mysqlDbConnection.init();
mysqlDbConnection.test_open(dbConnection);

app.get('/api/hello', (req, res) => {
  res.send('안녕하세요~');
});

app.get('/', (req, res) => {
  res.send('Hello Worldssssssss!');
});

const bodyParser = require('body-parser');

//application/x-www-form-urlencoded 데이터를 분석해서 가져오게 해줌
app.use(bodyParser.urlencoded({ extended: true }));
// application/json파일을 분석해서 가져올수 있게 함
app.use(bodyParser.json());

const { User } = require('./models/User');

/*레지스터 라우터*/
app.post('/api/users/register', (req, res) => {
  //회원 가입 할때 필요한 정보들을 client에서 가져오면
  //그것들을 데이터 베이스에 넣어 준다.
  console.log("register~~");
  /*
  dbConnection.query('select * from mbti.test', function (err, rows, fields) {
    console.log(rows);
  });
  */

  post = req.body;
  console.log(post.id);
  dbConnection.query('INSERT INTO mbti.user ( id, name, email, phone, password) VALUES(?,?,?,?,?)',
                [post.id, post.name, post.email, post.phone, post.password], function (err, result) {
    if (err) {
      res.send("There was a problem adding the information to the database.");
  }
  });



  /*
  const user = new User(req.body);

  user.save((err, userInfo) => {
    if (err) return res.json({ success: false, err });
    return res.status(200).json({
      success: true,
    });
  }); // save = mongoDB 메소드

  */
});

/*login 라우터*/

const cookieParser = require('cookie-parser');
app.use(cookieParser());
app.post('/api/users/login', (req, res) => {
  //1. 요청된 이메일을 데이터 베이스에서 검색
  User.findOne({ email: req.body.email }, (err, userInfo) => {
    if (!userInfo) {
      return res.json({
        loginSuccess: false,
        message: '제공된 이메일에 해당하는 유저가 없습니다.',
      });
    }

    //2. 데이터 베이스에서 요청한 E-mail이 있다면 비밀번호가 같은지 확인 - Bcrypt
    userInfo.comparePassword(req.body.password, (err, isMatch) => {
      //비밀번호 일치 확인 함수 생성
      if (!isMatch)
        return res.json({
          loginSuccess: false,
          message: '비밀번호가 틀렸습니다.',
        });

      //3. 비밀번호까지 같다면 Token 생성 -JSONWEBTOKEN 라이브러리 사용
      userInfo.generateToken((err, userinfo) => {
        if (err) return res.status(400).send(err);

        //토큰을 쿠키에 저장한다. (저장 위치는 쿠키, 로컬 저장소, 세션 등 선택 가능 )
        res
          .cookie('x_auth', userInfo.token) // 쿠키에는 x_auth: ~로 저장됨
          .status(200) //성공
          .json({
            loginSuccess: true,
            userId: userinfo._id,
          });
      });
    });
  });
});

const { auth } = require('./middleware/auth');
app.get('/api/users/auth', auth, (req, res) => {
  //auth= 미들웨어, 'api/users/auth' 리퀘스트를 받은 이후 callback function 실행 전 일을 수행
  // 미들웨어를 통과해 여기까지 왔다는 것은 authentication이 true라는 의미
  res.status(200).json({
    _id: req.user._id,
    //isAdmin: req.user.role === 0 ? false : true,
    isAuth: true,
    email: req.user.email,
    name: req.user.name,
    lastname: req.user.lastname,
    role: req.user.role,
    image: req.user.image,
  });
});

app.get('/api/users/logout', auth, (req, res) => {
  User.findOneAndUpdate({ _id: req.user._id }, { token: '' }, (err, user) => {
    if (err) return res.json({ success: false, err });
    return res.status(200).send({
      success: true,
    });
  });
});

app.listen(port, () => {
  /*포트 3000에서 앱 실행*/
  console.log(`Example app listening at http://localhost:${port}`);
});
