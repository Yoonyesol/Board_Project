import axios from 'axios'
import React, { useState } from 'react'

export default function SignUp() {
  const [requestResult, setRequestResult] = useState<string>('');

  const signUpHandler = () => {
    const data = {
        userEmail:"abc@gmail.com",
        userPassword:"qwer123",
        userPasswordCheck:"qwer123",
        userNickname:"Opra",
        userPhoneNumber:"010-2222-3333",
        userAddress:"대한민국 서울시",
        userAddressDetail:"강남구"
    }
    axios.post("http://localhost:4000/api/auth/signUp", data)
      .then((response) => {setRequestResult('Success!')})
      .catch((error) => {setRequestResult('Failed!')})
  }
  return (
    <div>
      <h3>{requestResult}</h3>
      <button onClick={() => signUpHandler()}>회원가입</button>
    </div>
  )
}
