import * as React from 'react';

import Button from '@mui/material/Button';

import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import '../Fonts/Fonts.css';
import { DatePicker } from '@mui/x-date-pickers';
import { useState } from 'react';
import { MuiChipsInput } from 'mui-chips-input'
import axios from 'axios';
import { Alert } from '@mui/material';
import { useEffect } from 'react';
import dayjs, { Dayjs } from 'dayjs';
import { Fade } from '@mui/material';


const EditExpenseForm = (props) => {
   
    const [chips, setChips] = useState([])
    const [expenseNameError, setExpenseNameError] = useState(false);
    const [expenseAmountError, setExpenseAmountError] = useState(false);

    const [expenseId, setId] = useState(0)
    const [name, setName] = useState("")
    const [amount, setAmount] = useState("")
    const [description, setDescription] = useState("")
    const [categories, setCategories] = useState([])
    const [startDate, setStartDate] = React.useState("")
    const [endDate, setEndDate] = React.useState("")
    const [bearer, setBearer] = useState("")
    const [updatedExpense, setUpdatedExpense] = useState(false)

    useEffect(()=>{
        setId(props["id"])
        setName(props["name"])
        setAmount(""+props["amount"])
        setDescription(props["description"])
        setCategories(props["categories"].map(item => item.name))
        setStartDate(props["startDate"])
        setEndDate(props["endDate"])
        setBearer(props["bearer"])
      },[]);


  const handleNameChange = (event) => {
    setName(event.target.value);
  };

  const handleAmountChange = (event) => {
    setAmount(event.target.value);
  };

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const handleCategoriesChange = (newChips) => {
    setCategories(newChips)
  }




  const handleSubmit = (event) => {
    event.preventDefault();


    const newExpenseNameError = name.trim() === "";
    setExpenseNameError(newExpenseNameError)

    setExpenseAmountError(amount.trim() === "")



    let expenseAmountInt = 0
    let newExpenseAmountError = true;


    if (amount !== "" && /^(\d+(\.\d*)?|\.\d+)?$/.test(amount)) {
      expenseAmountInt = parseFloat(amount)
      newExpenseAmountError = expenseAmountInt < 0;
    } else {
      setExpenseAmountError(true)
    }



    if (!newExpenseNameError && !newExpenseAmountError) {

      const requestOptions = {
        headers:{
            Authorization: "Bearer " + bearer
          }
    }

    axios.get("http://localhost:8088/expensetracker/getuserbytoken",requestOptions)
        .then(response => {
            const {id} = response.data
           

            const updatedExpense = {
              newExpense : {
                  id: expenseId,
                  name: name,
                  amount: amount,
                  description: description,
                  startDate: startDate,
                  endDate: endDate,},
        
              categories:categories
            }

            axios.put("http://localhost:8088/expensetracker/expenses", updatedExpense,requestOptions).then(
              setUpdatedExpense(true)
            )
   
        })

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
              onChange={handleNameChange}
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
              onChange={handleAmountChange}
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
              onChange={handleDescriptionChange}
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
            <MuiChipsInput fullWidth={true} value={categories} onChange={handleCategoriesChange}/ >
            
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Update Expense
            </Button>
            <Fade in={updatedExpense}>
              <Alert severity="success">Expense Details have been updated</Alert>
            </Fade>
          </Box>
         
        </Box>
  )
}

export default EditExpenseForm