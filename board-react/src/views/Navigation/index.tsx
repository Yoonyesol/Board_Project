import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import PersonIcon from '@mui/icons-material/Person';
import { useUserStore } from '../../stores';
import { useCookies } from 'react-cookie';

export default function Navigation() {
  const [cookies, setCookies] = useCookies();
  const { user, removeUser } = useUserStore();  //스토어에서 유저상태 받아오기
  
  //로그아웃 메서드
  const logOutHandler = () => {
    //토큰을 ''(빈 값)으로 바꾸고 인증시간을 현재 시간으로 해서 바로 만료되도록 처리
    setCookies('token', '', { expires: new Date() });
    removeUser(); //가지고 있는 유저 정보 삭제
  }
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="fixed">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            News
          </Typography>
          { /*user가 null일 때와 아닐 때 아이콘 변경*/}
          {user ? (
            <IconButton color="inherit" onClick={()=>logOutHandler()}>
              <PersonIcon />
            </IconButton>
          ) : (
              <Button color="inherit">Login</Button>
          )}
        </Toolbar>
      </AppBar>
    </Box>
  );
}