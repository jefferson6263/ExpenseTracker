

import React from 'react'
import DashBoard from '../Components/VerticalDashBoard'
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Alert from '@mui/material/Alert';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import '../Fonts/Fonts.css';
import axios from 'axios'
import { useNavigate } from "react-router-dom"
import { useState } from 'react';
import { useEffect } from 'react';
import { Grow } from '@mui/material';
import { Fade } from '@mui/material';



const UpdateDetailsPage = (props) => {

    const {bearer} = props
    const navigate = useNavigate();

    const [validEmailForm, setvalidEmailForm] = useState(true);
    const [firstNameError, setFirstNameError] = useState(false);
    const [lastNameError, setLastNameError] = useState(false);
    const [usernameError, setUsernameError] = useState(false);
    const [passwordError, setPasswordError] = useState(false);
    const [id, setId] = useState(0)
    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [email, setEmail] = useState("")
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [updatedDetails, setUpdatedDetails] = useState(false)

    useEffect(()=>{
      
        const requestOptions = {
            headers:{
                Authorization: "Bearer " + bearer
            }
        }
        
        axios.get("http://localhost:8088/expensetracker/getuserbytoken",requestOptions)
            .then((response => {
                const user = response.data
              
                setId(user["id"])
                setFirstName(user["firstName"])
                setLastName(user["lastName"])
                setEmail(user["email"])
                setUsername(user["username"])
                setPassword(user["password"])

                // setNewEmail(user["email"])
            }))
      },[]);
  
    const handleFirstNameChange = (event) => {
        setFirstName(event.target.value);
        setUpdatedDetails(false)
    };

    const handleLastNameChange = (event) => {
        setLastName(event.target.value);
        setUpdatedDetails(false)
    };


    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
        setUpdatedDetails(false)
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
        setUpdatedDetails(false)
    };
    
  
  
    const handleSubmit = (event) => {
      event.preventDefault();
     
     
      const updatedUser = {
        id: id,
        firstName: firstName,
        lastName: lastName,
        email: email,
        username: username,
        password: password
      }
  
      const newFirstNameError = firstName.trim() === "";
      const newLastNameError = lastName.trim() === "";
      const newUsernameError = username.trim() === "";
      const newPasswordError = password.trim() === "";
  
  
      setFirstNameError(newFirstNameError);
      setLastNameError(newLastNameError);
      setUsernameError(newUsernameError);
      setPasswordError(newPasswordError);
      setvalidEmailForm(validEmailForm);


        
        if (!newFirstNameError && !newLastNameError && !newUsernameError && !newPasswordError) {

        const endpoint2 = "http://localhost:8088/expensetracker/users"
        axios.put(endpoint2, updatedUser)
        setUpdatedDetails(true)

        } 

  
    };

    return (
        <>
            <h1>Update Details</h1>
            <DashBoard bearer={bearer}/>
            
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
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3}} >
            <Grid container spacing={2} >
                
              <Grid item xs={12} sm={6} onChange={()=>setFirstNameError(false)}>
              <form autoComplete="off">
                <TextField
                  autoComplete="given-name"
                  name="firstName" 
                  required
                  fullWidth
                  id="firstName"
                  label="First Name"
                  autoFocus
                  error={firstNameError}
                  {...firstNameError && {helperText:"First name required"}}
                  value={firstName}
                  onChange={handleFirstNameChange}
                />
                 </form>
              </Grid>
              <Grid item xs={12} sm={6} onChange={()=>setLastNameError(false)}>
              <form autoComplete="off">
                <TextField
                  required
                  fullWidth
                  id="lastName"
                  label="Last Name"
                  name="lastName"
                  autoComplete="family-name"
                  error={lastNameError}
                  {...lastNameError && {helperText:"Last name required"}}
                  value={lastName}
                  onChange={handleLastNameChange}
                />
                 </form>
              </Grid>
              <Grid item xs={12} onChange={()=>setUsernameError(false)}>
              <form autoComplete="off">
                <TextField
                  required
                  fullWidth
                  id="userName"
                  label="Username"
                  name="username"
                  autoComplete="username"
                  error={usernameError}
                  {...usernameError && {helperText:"Username required"}}
                  value={username}
                  onChange={handleUsernameChange}
                />
                 </form>
              </Grid>
              <Grid item xs={12} onChange={()=>setPasswordError(false)}>
              <form autoComplete="off">
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="new-password"
                  error={passwordError}
                  {...passwordError && {helperText:"Password required"}}
                 
                  onChange={handlePasswordChange}
                  
                />
                 </form>
              </Grid>
              <Grid item xs={12}>

              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
             
            >
              Update Details
            </Button>
            <Fade in={updatedDetails}>
              <Alert severity="success">User Details have been updated</Alert>
            </Fade>
            
          </Box>
          
          
        
          
        </Box>
        </Grow>
        
        
      </Container>
        </>
    )
}

export default UpdateDetailsPage