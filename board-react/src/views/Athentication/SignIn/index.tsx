import { Card, Box, TextField, Button, Typography } from '@mui/material'
import axios from 'axios';
import React, { useState } from 'react'
import { useCookies } from 'react-cookie';
import { useUserStore } from '../../../stores';

interface Props{
  setAuthView: (setAuthView: boolean) => void,
}

export default function SignIn(props: Props) {
  const [userEmail, setEmail] = useState<string>('');
  const [userPassword, setPassword] = useState<string>('');
  const [cookies, setCookies] = useCookies();

  const { user, setUser } = useUserStore();

  const { setAuthView } = props

  const signInHandler = () => {
    //검증
    if (userEmail.length === 0 || userPassword.length === 0) {
      alert("이메일과 비밀번호를 입력하세요!");
      return;
    }

    const data = {
      userEmail, userPassword,
    };
    axios
      .post("http://localhost:4000/api/auth/signIn", data)
      .then((response) => {
        const reseponseData = response.data;
        console.log(response.data)
        if (!reseponseData.result) {
          alert("로그인에 실패했습니다.");
          return;
        }
        const { token, exprTime, user } = reseponseData.data;
        const expires = new Date();
        expires.setMilliseconds(expires.getMilliseconds + exprTime);
        
        setCookies('token', token, { expires });
        setUser(user);
       })
      .catch((error) => { 
        alert("로그인에 실패했습니다.");
      });
  }
  return (
    <Card sx={{ minWidth: 275, maxWidth: "50vw", padding: 5 }}>
      <Box>
          <Typography variant='h5'>로그인</Typography>
      </Box>
      <Box height={'50vh'}>
        <TextField fullWidth label="이메일" type="email" variant="standard" onChange={(e) => {setEmail(e.target.value)}} />
        <TextField fullWidth label="비밀번호" type="password" variant="standard" onChange={(e) => { setPassword(e.target.value)}} />
      </Box>
      <Box component={"div"}>
        <Button fullWidth onClick={() => signInHandler()} variant='contained'>로그인</Button>
      </Box>
      <Box component={"div"} display={'flex'} mt={2}>
        <Typography>신규 사용자 이신가요?</Typography>
      <Typography fontWeight={800} ml={1} onClick={() => setAuthView(true)}>회원가입</Typography>
      </Box>
    </Card>
  )
}
