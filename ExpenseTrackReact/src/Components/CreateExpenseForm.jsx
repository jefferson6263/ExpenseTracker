
import * as React from 'react';

import Button from '@mui/material/Button';
import axios from 'axios';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import '../Fonts/Fonts.css';
import { DatePicker } from '@mui/x-date-pickers';
import { useState } from 'react';
import { MuiChipsInput } from 'mui-chips-input'
import { Alert } from '@mui/material';
import { useNavigate } from 'react-router-dom';


const CreateExpenseForm = (props) => {
  const {bearer} = props

  const [isMade, setIsMade] = useState(false)
  const navigate = useNavigate();

  const [createdNewExpense, setCreatedNewExpense] = useState(false)
  const [startDate, setStartDate] = useState(new Date())
  const [endDate, setEndDate] = useState(new Date())

  const [chips, setChips] = useState([])
  const [expenseNameError, setExpenseNameError] = useState(false);
  const [expenseAmountError, setExpenseAmountError] = useState(false);

  const handleChange = (newChips) => {
    setChips(newChips)
  }


  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);

    
    const newExpenseNameError = data.get("expenseName").trim() === "";
    setExpenseNameError(newExpenseNameError)

    
    setExpenseAmountError(data.get("expenseAmount").trim() === "")


    let expenseAmountInt = 0
    let newExpenseAmountError = true;


    

    if (data.get('expenseAmount') !== "" && /^(\d+(\.\d*)?|\.\d+)?$/.test(data.get('expenseAmount'))) {
      expenseAmountInt = parseFloat(data.get('expenseAmount'))
      newExpenseAmountError = expenseAmountInt < 0;
    } else {
      setExpenseAmountError(true)
    }



    if (!newExpenseNameError && !newExpenseAmountError) {

      let id = 0;
      const requestOptions = {
        headers:{
            Authorization: "Bearer " + bearer,
        }
      }
     
      axios.get("http://localhost:8088/expensetracker/getuserbytoken",requestOptions)
            .then(response=>{

                id = response.data["id"]
                user = response.data

            })


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
                setCreatedNewExpense(true)
                setIsMade(true)
              
            })
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
              {...expenseAmountError && {helperText:"Expense Amount must be valid"}}
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