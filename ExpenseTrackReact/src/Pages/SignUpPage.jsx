import * as React from 'react';

import Button from '@mui/material/Button';

import TextField from '@mui/material/TextField';

import Alert from '@mui/material/Alert';
import { Link } from 'react-router-dom';

import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';

import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';

import { Grow } from '@mui/material';
import '../Fonts/Fonts.css';
import axios from 'axios'
import { useNavigate } from "react-router-dom"
import { useState } from 'react';



export default function SignUp() {

  const navigate = useNavigate();
  const [emailExist, setemailExist] = useState(false);
  const [validEmailForm, setvalidEmailForm] = useState(true);

  const handleSubmit = (event) => {
    event.preventDefault();
    setemailExist(false)
    const data = new FormData(event.currentTarget);

    const newUser = {
      firstName: data.get('firstName'),
      lastName: data.get('lastName'),
      email: data.get('email'),
      username: data.get('username'),
      password: data.get('password'),
    }

    console.log({
      
      firstName: data.get('firstName'),
      lastName: data.get('lastName'),
      email: data.get('email'),
      username: data.get('username'),
      password: data.get('password'),
    })

    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (emailRegex.test(data.get('email'))) {
      console.log("valid")
      const endpoint1 = "http://localhost:8088/expensetracker/getuserbyemail/" + data.get('email')

      axios.get(endpoint1).catch(function (error) {
        console.clear()
        const endpoint2 = "http://localhost:8088/expensetracker/users"
        axios.post(endpoint2, newUser)
        navigate("/")
      })

      setemailExist(true)
    } else {
    
      setvalidEmailForm(false)
    }

    


  };

  return (
  
      <Container component="main" maxWidth="xs">
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
          }}
        >
          <Typography component="h1" variant="h5" fontFamily={"Lexend"}>
            Sign up
          </Typography>
          <Box component="form" noValidate onSubmit={handleSubmit} onChange={()=>setemailExist(true)} sx={{ mt: 3}} >
            <Grid container spacing={2} >
                
              <Grid item xs={12} sm={6}>
                <TextField
                  autoComplete="given-name"
                  name="firstName" 
                  required
                  fullWidth
                  id="firstName"
                  label="First Name"
                  autoFocus
                
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  fullWidth
                  id="lastName"
                  label="Last Name"
                  name="lastName"
                  autoComplete="family-name"
                />
              </Grid>
              <Grid item xs={12} sx={{padding: 1,}} >
                <TextField
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  autoComplete="email"
                  
                />
                {emailExist && <Alert severity="warning" sx={{padding: 1, display: 'flex', justifyContent: 'center' }}>This email is already in use.</Alert>}
                {!validEmailForm && <Alert severity="warning" sx={{padding: 1, display: 'flex', justifyContent: 'center' }}>Not a valid email.</Alert>}
              </Grid>
              <Grid item xs={12} >
                <TextField
                  required
                  fullWidth
                  id="userName"
                  label="Username"
                  name="username"
                  autoComplete="username"
                  
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="new-password"
                />
              </Grid>
              <Grid item xs={12}>
                {/* <FormControlLabel
                  control={<Checkbox value="allowExtraEmails" color="primary" />}
                  label="I want to receive inspiration, marketing promotions and updates via email."
                /> */}
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              
            >
              Sign Up
            </Button>
            <Grid container justifyContent="flex-end" direction="column">
              <Grid item>
                <Link to="/" variant="body2" fontFamily={"Lexend"}>
                  Already have an account? Sign in
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
        </Grow>
        
      </Container>
  
  );
}