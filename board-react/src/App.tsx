import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';

function App() {
  //화면에 뿌려주는 변수를 지정할 때는 state가 필요하다.
  const [connection, setConnection] = useState<string>('');

  const connectionTest = () => {
    //request를 보낼 url=localhost:4000, endpoint지정x
    axios.get('http://localhost:4000/').then((response) => {
      //에러가 발생하지 않고 데이터를 받아왔을 시,
      //받은 reponse의 data로 connection의 상태를 변경시켜준다.
      setConnection(response.data); 
    }).catch((error) => { //에러발생 시 아래 코드 실행
      setConnection(error.message);
    })
  }

  //useEffect: 특정 상태가 변경되었을 때 useEffect 내의 코드블럭이 실행된다
  //페이지 로드 시 connectionTest함수를 실행시켜줄 함수
  useEffect(() => {
    connectionTest();
  }, []);
  //[]:어떤 상태값이 바뀌었을 때 useEffect가 실행될지 명시해줌.
  //빈 배열의 경우 초기 페이지 로드 시 한번만 실행

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>{connection}</p>
      </header>
    </div>
  );
}

export default App;
