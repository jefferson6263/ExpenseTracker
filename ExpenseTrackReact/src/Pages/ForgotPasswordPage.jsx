import * as React from 'react';

import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Alert from '@mui/material/Alert';
import { Link } from 'react-router-dom';

import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import axios from 'axios'
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import '../Fonts/Fonts.css';
import { Switch } from '@mui/material';
import { useState } from 'react';
import { useEffect } from 'react'; 
import { Grow } from '@mui/material';









const ForgotPassword = () => {
    const [emailExist, setemailExist] = useState(true);
    const [user, setUser]  = useState([])
 
    const handleSubmit = (event) => {
    
        event.preventDefault();
        
        const data = new FormData(event.currentTarget);
       
        console.log({
            email: data.get('email'),
            password: data.get('password'),
        });

        const endpoint1 = "http://localhost:8088/expensetracker/getuserbyemail/" + data.get('email')
        const result = axios.get(endpoint1)

        result.catch(function (error) {
            console.clear()
            setemailExist(false)
        })

        if (emailExist) {
            console.log("exist")
            
          
            result.then(function (response) {
              // Handle success
              const user = response.data
              console.log(user);
              console.log(user["password"]);
              user["password"] = data.get('password')
              console.log(user["password"]);
            })
            
            const endpoint2 = "http://localhost:8088/expensetracker/users" 
            axios.put(endpoint2, user).then(console.log("hi"))
            
           
            // NOTE TO FUTURE JEFFERY WRITE METHOD THAT UPDATERS ONLY PASSWORD
            // ITS NAP TIME FOR CURRENT JEFF
        }
    
    }
  return (

        <Container component="main" maxWidth="xs" > 

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
        Reset Password
    </Typography>
    <Box component="form" onSubmit={handleSubmit} onChange={()=>setemailExist(true)} noValidate sx={{ mt: 1 }}>
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

      {!emailExist && 
      <Grow in={!emailExist} style={{ transformOrigin: '0 5 0' }} {...({ timeout: 1000 })}> 
        <Alert severity="warning" sx={{padding: 1, display: 'flex', justifyContent: 'center' }}>This email doesn't exist.</Alert>
      </Grow>
      }

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
        Reset Password
      </Button>
        <Grid item>
            <Link to="/" variant="body2" fontFamily={"Lexend"}>
                Back
            </Link>
        </Grid>

    </Box>
  </Box>
        </Grow>

      </Container>
  

  );
}

export default ForgotPassword;