
import * as React from 'react';

import Button from '@mui/material/Button';

import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
// import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import '../Fonts/Fonts.css';
import { DateRangePicker } from '@mui/x-date-pickers-pro/DateRangePicker';


  

const CreateExpenseForm = () => {

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
  
    // const signInOptions = {
    //   auth:{
    //     username:data.get('email'),
    //     password:data.get('password')
    //   }
    // }
  
    console.log({
      email: data.get('email'),
      password: data.get('password'),
    });
  
  
  }

  
  return (
    <Box

          
        >
      
         
          <Typography component="h1" variant="h5" fontFamily={"Lexend"} color="black">
            Expense Name
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required 
              fullWidth
              id="expenseName"
              label="Expense Name"
              name="expenseName"
              autoComplete="expenseName"
              autoFocus
            />

          <Typography component="h1" variant="h5" fontFamily={"Lexend"} color="black">
            Expense Amount
          </Typography>
            <TextField
              margin="normal"
              required
              fullWidth
              name="expenseAmount"
              label="Expense Amount"
              type="expenseAmount"
              id="expenseAmount"
              autoComplete="expenseAmount"
            />

        <Typography component="h1" variant="h5" fontFamily={"Lexend"} color="black">
            Expense Description
          </Typography>
            <TextField
              margin="normal"
              required
              fullWidth
              name="expenseDescription"
              label="Expense Description"
              type="expenseDescription"
              id="expenseDescription"
              autoComplete="expenseDescription"
            />
            <DateRangePicker/>
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
                <Link href="#" variant="body2">
                  Forgot password?
                </Link>
              </Grid>
              <Grid item>
                <Link href="/signup" variant="body2">
                  Don't have an account?
                </Link>
              </Grid>
            </Grid>
          </Box>
         
        </Box>
  )
}

export default CreateExpenseForm