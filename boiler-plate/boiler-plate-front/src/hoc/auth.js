import React, { useEffect } from 'react';
import Axios from 'axios';
import { useDispatch } from 'react-redux';
import { auth } from '../_actions/user_action';

// option: null(아무나 출입 가능), true(로그인한 유저만 출입 가능), false(로그인 유저 출입 불가)
// adminRoute: 관리자 유저만 출입할 수 있는 경우 true
export default function (SpecificComponent, option, adminRoute = null) {
  function AuthenticationCheck(props) {
    // backend에 요청 전송
    const dispatch = useDispatch();
    useEffect(() => {
      dispatch(auth()).then((response) => {
        console.log(response);

        // 로그인 하지 않은 상태
        if (!response.payload.isAuth) {
          if (option) {
            props.history.push('/login');
          }
        } else {
          //로그인 한 상태
          if (adminRoute && !response.payload.isAdmin) {
            //관리자가 아니나 관리자 페이지를 들어가려 할 경우
            props.history.push('/');
          } else {
            //로그인한 유저가 로그인 유저가 출입 불가능한 곳을 들어가려 할 경우
            if (option == false) props.history.push('/');
          }
        }
      });
    }, []);

    return <SpecificComponent />;
  }

  return AuthenticationCheck;
}
