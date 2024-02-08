import * as React from 'react';

import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';

import { Link } from 'react-router-dom';

import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
// import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import '../Fonts/Fonts.css';
import { Switch } from '@mui/material';
import { useState } from 'react';
import { useEffect } from 'react'; 
import { Grow } from '@mui/material';




const handleSubmit = (event) => {
  event.preventDefault();
  const data = new FormData(event.currentTarget);

  const signInOptions = {
    auth:{
      username:data.get('email'),
      password:data.get('password')
    }
  }

  console.log({
    email: data.get('email'),
    password: data.get('password'),
  });


}

const box = (
  <Box
    sx={{
      marginTop: 8,
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'center',
      backgroundColor: 'white',
      borderRadius: 8,
      padding: 6,
      boxShadow: '8px 8px 25px rgba(0, 0, 0, 0.2)',
      className: 'box',
    }} 
    
  >

    <Typography component="h1" variant="h5" fontFamily={"Lexend"} color="black">
      Sign in
    </Typography>
    <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
      <TextField
        margin="normal"
        required 
        fullWidth
        id="email"
        label="Email Address"
        name="email"
        autoComplete="email"
        autoFocus
      />
      <TextField
        margin="normal"
        required
        fullWidth
        name="password"
        label="Password"
        type="password"
        id="password"
        autoComplete="current-password"
      />

      <Button
        type="submit"
        fullWidth
        variant="contained"
        sx={{ mt: 3, mb: 2 }}
      >
        Sign In
      </Button>
      <Grid container direction="column">
        <Grid item xs>
          <Link to="/forgotpassword" variant="body2">
            Forgot password?
          </Link>
        </Grid>
        <Grid item>
          <Link to="/signup" variant="body2">
            Don't have an account?
          </Link>
        </Grid>
      </Grid>
    </Box>
  </Box>
);

const defaultTheme = createTheme({
  palette: {
    background: {
      default : "#e3f2fd",
    } 
  },
});

const SignInPage = () => {
// function SignInPage() {

  // const signInOptions = {
  //   auth:{
  //     username:username,
  //     password:password
  //   }
  // }


  // const [checked, setChecked] = useState(true) 

  // useEffect(() => {
  //   setChecked(true);
  // },[]);

 
  console.log(defaultTheme)
  console.log(defaultTheme.palette)
   
  return (

      <Container component="main" maxWidth="xs" > 

        <Grow 
          in={true}
          style={{ transformOrigin: '0 5 0' }}
          {...({ timeout: 1000 })}
        > 
        {box}
        </Grow>

      </Container>
  

  );
}

export default SignInPage;