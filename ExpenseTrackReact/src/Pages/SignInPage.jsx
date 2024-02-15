import * as React from 'react';

import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';

import { Link } from 'react-router-dom';

import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { useNavigate } from 'react-router-dom';
import '../Fonts/Fonts.css';

import { useState } from 'react';
import axios from 'axios';
import { Grow } from '@mui/material';
import { Alert } from '@mui/material';
import { Paper } from '@mui/material';



const SignInPage = (props) => {
  const [bearer, setBearer] = props.bearer
  const [loginCred, setloginCred] = useState(false)
  const navigate = useNavigate();


  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
  
    const requestOptions = {
      auth:{
          username:data.get('email'),
          password:data.get('password')
      }
  }

  
    console.log({
      username: data.get('email'),
      password: data.get('password'),
    })

 

    axios.post("http://localhost:8088/expensetracker/auth/login",{},requestOptions)
            .then(response=>{
                setBearer(response.data)
                console.log(response)
                console.log(response["id"])
         
                navigate("/homepage")
            }).catch(function () {setloginCred(true)})

    

  
  
  }
  
   
  return (
      
      <Container component="main" maxWidth="xs" > 
        <h1>ExpensEase</h1>
        <Grow 
          in={true}
          style={{ transformOrigin: '0 5 0' }}
          {...({ timeout: 1000 })}
        > 
          
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
        label="Email"
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
      {loginCred && <Alert severity="warning">Your username or password was incorrect. Please try again.</Alert>}
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
        </Grow>

      </Container>
  

  );
}

export default SignInPage;