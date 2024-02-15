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
import { useEffect } from 'react';
import dayjs, { Dayjs } from 'dayjs';


const EditExpenseForm = (props) => {
   
    const [chips, setChips] = useState([])
    const [expenseNameError, setExpenseNameError] = useState(false);
    const [expenseAmountError, setExpenseAmountError] = useState(false);

    const [id, setId] = useState(0)
    const [name, setName] = useState("")
    const [amount, setAmount] = useState(0)
    const [description, setDescription] = useState("")
    const [categories, setCategories] = useState([])
    const [startDate, setStartDate] = React.useState("")
    const [endDate, setEndDate] = React.useState("")

    useEffect(()=>{
        setId(props["id"])
        setName(props["name"])
        setAmount(props["amount"])
        setDescription(props["description"])
        setCategories(props["categories"])
        setStartDate(props["startDate"])
        setEndDate(props["endDate"])
      },[]);

 

    console.log(description)
    console.log(startDate)
    console.log(typeof(startDate))

    console.log("not works")
    console.log(dayjs(startDate))

    console.log("works")
    console.log(dayjs("2024-01-04"))

  

  const handleChange = (newChips) => {
    setChips(newChips)
  }


  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
  
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

    
    setExpenseAmountError(data.get("expenseAmount").trim() === "")

    // const isValidAmount = /^(\d+(\.\d*)?|\.\d+)?$/.test(data.get('expenseAmount'));
    // console.log(isValidAmount)

    let expenseAmountInt = 0
    let newExpenseAmountError = true;


    

    if (data.get('expenseAmount') !== "" && /^(\d+(\.\d*)?|\.\d+)?$/.test(data.get('expenseAmount'))) {
      expenseAmountInt = parseFloat(data.get('expenseAmount'))
      newExpenseAmountError = expenseAmountInt < 0;
    } else {
      setExpenseAmountError(true)
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
  
            }).catch()
    }

  }

  
  return (
    <Box>
          <Typography component="h1" variant="h5" fontFamily={"Lexend"} color="black" sx={{paddingBottom: '5px'}}>
            Expense Name
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate >
          <form autoComplete="off">
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
              value={name}
            />
            </form>
          <Typography component="h1" variant="h5" fontFamily={"Lexend"} color="black" sx={{paddingTop: '10px', paddingBottom: '5px'}}>
            Expense Amount
          </Typography>
          <form autoComplete="off">
            <TextField
              inputMode='decimal'
              fullWidth
              name="expenseAmount"
              label="Expense Amount"
              type="expenseAmount"
              id="expenseAmount"
              autoComplete="expenseAmount"
              error={expenseAmountError}
              {...expenseAmountError && {helperText:"Expense Amount must be valid"}}
              value={amount}
            />
            </form>
        <Typography component="h1" variant="h5" fontFamily={"Lexend"} color="black" sx={{paddingTop: '10px', paddingBottom: '5px'}}>
            Expense Description
          </Typography>
          <form autoComplete="off">
            <TextField
              fullWidth
              name="expenseDescription"
              label="Expense Description"
              type="expenseDescription"
              id="expenseDescription"
              autoComplete="expenseDescription"
              value={description}
            />
            </form>
            <Typography component="h1" variant="h5" fontFamily={"Lexend"} color="black" sx={{paddingTop: '10px', paddingBottom: '5px'}}>
            Date
          </Typography>
     
            <DatePicker  value={dayjs(startDate)} label="Start Date" sx={{paddingRight: '85px'}} onChange={(newValue) => {setStartDate(newValue)}}/>
            <DatePicker   value={dayjs(endDate)} label="End Date" onChange={(newValue) => {setEndDate(newValue)}} />

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
 
          </Box>
         
        </Box>
  )
}

export default EditExpenseForm