import axios from 'axios'
import React, { useState } from 'react'
import Card from '@mui/material/Card';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

interface Props{
  setAuthView: (setAuthView: boolean) => void,
}

export default function SignUp(props: Props) {
  //사용자에게 정보를 입력받기 위한 변수와 state 생성
  const [userEmail, setUserEmail] = useState<string>('');
  const [userPassword, setUserPassword] = useState<string>('');
  const [userPasswordCheck, setUserPasswordCheck] = useState<string>('');
  const [userNickname, setUserNickname] = useState<string>('');
  const [userPhoneNumber, setUserPhoneNumber] = useState<string>('');
  const [userAddress, setUserAddress] = useState<string>('');
  const [userAddressDetail, setUserAddressDetail] = useState<string>('');

  const { setAuthView } = props
  
  const signUpHandler = () => {
    const data = {
        userEmail,
        userPassword,
        userPasswordCheck,
        userNickname,
        userPhoneNumber,
        userAddress,
        userAddressDetail
    }
    axios.post("http://localhost:4000/api/auth/signUp", data)
      .then((response) => {})
      .catch((error) => {})
  }
  return (
    <Card sx={{ minWidth: 275, maxWidth:"50vw", padding: 5  }}>
      <Box>
          <Typography variant='h5'>회원가입</Typography>
      </Box>
      <Box height={'50vh'}>
        <TextField fullWidth label="이메일" type="email" variant="standard" onChange={(e)=>setUserEmail(e.target.value)}/>
        <TextField fullWidth label="비밀번호" type="password" variant="standard" onChange={(e)=>setUserPassword(e.target.value)}/>
        <TextField fullWidth label="비밀번호 확인" type="password" variant="standard" onChange={(e)=>setUserPasswordCheck(e.target.value)}/>
        <TextField fullWidth label="닉네임" variant="standard" onChange={(e)=>setUserNickname(e.target.value)}/>
        <TextField fullWidth label="휴대폰 번호" variant="standard" onChange={(e)=>setUserPhoneNumber(e.target.value)}/>
        <TextField fullWidth label="주소" variant="standard" onChange={(e)=>setUserAddress(e.target.value)}/>
        <TextField fullWidth label="상세주소" variant="standard" onChange={(e)=>setUserAddressDetail(e.target.value)}/>
      </Box>
      <Box component={"div"} > <Button fullWidth onClick={() => signUpHandler()} variant='contained'>회원가입</Button></Box>
      <Box component={"div"} display={'flex'} mt={2}>
        <Typography>이미 계정이 있으신가요?</Typography>
        <Typography fontWeight={800} ml={1} onClick={() => setAuthView(false)}>로그인</Typography>
      </Box>
    </Card>
    
  )
}
