import Navigation from '../../Navigation'
import Athentication from '../../Athentication'
import BoardMain from '../../BoardMain'
import { useEffect, useState } from 'react'
import { useUserStore } from '../../../stores'
import { useCookies } from 'react-cookie'
import axios from 'axios'

export default function MainLayout() {
  const [boardResponse, setBoardResponse] = useState<string>('');
  const [ cookies ] = useCookies();
  const { user } = useUserStore();  //스토어의 유저 가지고 오기

  const getBoard = async (token: string) => {
    const requestOption = {
      headers: {  //받아온 토큰을 사용
        Authorization: `Bearer ${token}`
      }
    }
    await axios.get('http://localhost:4000/api/board/', requestOption).then((response) => {
      setBoardResponse(response.data);
    }).catch((error) => '');
  }

  useEffect(() => {
    const token = cookies.token;
    if (token) getBoard(token);
    else setBoardResponse('');  //토큰이 없다면 빈값으로 돌아온다
  }, [cookies.token]); //토큰의 상태가 바뀔 때마다 실행

  return (
    <>
      <Navigation />
      {/* 빈값이 아니면 BoardMain 실행 */}
      {user ? (<BoardMain/>):(<Athentication />)}  
    </>
  )
}
