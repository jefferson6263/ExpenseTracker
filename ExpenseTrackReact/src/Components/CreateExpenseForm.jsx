
import * as React from 'react';

import Button from '@mui/material/Button';

import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
// import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import '../Fonts/Fonts.css';
import { DatePicker } from '@mui/x-date-pickers';
import { useState } from 'react';
import { MuiChipsInput } from 'mui-chips-input'
import axios from 'axios';
import { Alert } from '@mui/material';

const CreateExpenseForm = (props) => {
  const {bearer} = props
  console.log("form bearer")
  console.log(bearer)
  const [createdNewExpense, setCreatedNewExpense] = React.useState(false)
  const [startDate, setStartDate] = React.useState(new Date())
  const [endDate, setEndDate] = React.useState(new Date())

  const [chips, setChips] = useState([])
  const [expenseNameError, setExpenseNameError] = useState(false);
  const [expenseAmountError, setExpenseAmountError] = useState(false);

  const handleChange = (newChips) => {
    setChips(newChips)
  }


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
      name: data.get('expenseName'),
      amount: data.get('expenseAmount'),
      description: data.get('expenseDescription'),
      startDate: startDate,
      endDate: endDate,
      categories: chips
    });
    
    const newExpenseNameError = data.get("expenseName").trim() === "";
    setExpenseNameError(newExpenseNameError)

    // const isValidAmount = /^(\d+(\.\d*)?|\.\d+)?$/.test(data.get('expenseAmount'));
    // console.log(isValidAmount)

    let expenseAmountInt = 0
    let newExpenseAmountError = true;


    

    if (data.get('expenseAmount') !== "" && /^(\d+(\.\d*)?|\.\d+)?$/.test(data.get('expenseAmount'))) {
      expenseAmountInt = parseFloat(data.get('expenseAmount'))
      newExpenseAmountError = expenseAmountInt < 0;
    }



    if (!newExpenseNameError && !newExpenseAmountError) {
      console.log("all good to send")

      let id = 0;
      const requestOptions = {
        headers:{
            Authorization: "Bearer " + bearer,
        }
      }
     
      let user = {}
      axios.get("http://localhost:8088/expensetracker/getuserbytoken",requestOptions)
            .then(response=>{console.log(response.data)
                id = response.data["id"]
                console.log("beanie")
                console.log(id)
                user = response.data

            })
      console.log(chips)

      const newExpense = {
        newExpense : {name: data.get('expenseName'),
                  amount: data.get('expenseAmount'),
                  description: data.get('expenseDescription'),
                  startDate: startDate,
                  endDate: endDate,
                },
        categories: chips
      }
      
      axios.post("http://localhost:8088/expensetracker/user/addexpense", newExpense, requestOptions)
            .then(response=>{
           
                console.log(response)
                setCreatedNewExpense(true)
            }).catch()
    }

  }

  
  return (
    <Box>
          <Typography component="h1" variant="h5" fontFamily={"Lexend"} color="black" sx={{paddingBottom: '5px'}}>
            Expense Name
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate >
            <TextField
              required 
              fullWidth
              id="expenseName"
              label="Expense Name"
              name="expenseName"
              autoComplete="expenseName"
              autoFocus
              error={expenseNameError}
              {...expenseNameError && {helperText:"Expense Name required"}}
            />

          <Typography component="h1" variant="h5" fontFamily={"Lexend"} color="black" sx={{paddingTop: '10px', paddingBottom: '5px'}}>
            Expense Amount
          </Typography>
            <TextField
              inputMode='decimal'
            
              fullWidth
              name="expenseAmount"
              label="Expense Amount"
              type="expenseAmount"
              id="expenseAmount"
              autoComplete="expenseAmount"
              error={expenseAmountError}
              {...expenseAmountError && {helperText:"Expense Amount must be Non-negative"}}
            />

        <Typography component="h1" variant="h5" fontFamily={"Lexend"} color="black" sx={{paddingTop: '10px', paddingBottom: '5px'}}>
            Expense Description
          </Typography>
            <TextField
        
             
              fullWidth
              name="expenseDescription"
              label="Expense Description"
              type="expenseDescription"
              id="expenseDescription"
              autoComplete="expenseDescription"
            />
            {/* <DateRangePicker/> */}
            <Typography component="h1" variant="h5" fontFamily={"Lexend"} color="black" sx={{paddingTop: '10px', paddingBottom: '5px'}}>
            Date
          </Typography>
            <DatePicker label="Start Date" sx={{paddingRight: '85px'}} onChange={(newValue) => {setStartDate(newValue)}}/>
            <DatePicker label="End Date" onChange={(newValue) => {setEndDate(newValue)}} />

            <Typography component="h1" variant="h5" fontFamily={"Lexend"} color="black" sx={{paddingTop: '10px', paddingBottom: '5px'}}>
            Categories
          </Typography>

            <MuiChipsInput fullWidth={true} value={chips} onChange={handleChange}/ >
            
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Create Expense
            </Button>
            {createdNewExpense && <Alert severity="success">Expense has been created</Alert>}
          </Box>
         
        </Box>
  )
}

export default CreateExpenseForm