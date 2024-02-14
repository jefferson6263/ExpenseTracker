import * as React from 'react';
import TextField from '@mui/material/TextField';
import { StaticDatePicker } from '@mui/x-date-pickers';
import { Zoom } from '@mui/material';
import { Box } from '@mui/material';
import axios from 'axios';
import { useState } from 'react';
import { Grid } from '@mui/material';
import { Divider } from '@mui/material';
import ExpenseCard from './ExpenseCard';




const StaticDatePickerLandscape = (props) => {
  const [value, setValue] = React.useState(new Date());
  const {bearer} = props
  // console.log("date picker")
  // console.log(bearer)
  const [expenses, setExpense] = useState([]);
  const [expensesToBeDisplayed, setExpensesToBeDisplayed] = useState([]);

  const handleAccept = (value) => {

  
    const requestOptions = {
        headers:{
            Authorization: "Bearer " + bearer
        }
    }

    axios.get("http://localhost:8088/expensetracker/getuserbytoken",requestOptions)
        .then(response => {
            // const {id} = response.data
            let id = 4
            axios.get("http://localhost:8088/expensetracker/userexpenses/" + id)
                .then(response=>{
                    setExpense(response.data)
                  
        }) 
    })
    let d = new Date(value["$d"])

    // console.log(expenses)
    // console.log(typeof(value))
    // console.log(value["$d"])

    
    console.log(d)
    let e = []
    expenses.forEach((expense, index) => {
       
        let [year, month, day] = expense["startDate"].split("-");
        let dateObjectStart = new Date(parseInt(year), parseInt(month) - 1, parseInt(day))

        let [year2, month2, day2] = expense["endDate"].split("-");
        let dateObjectEnd = new Date(parseInt(year2), parseInt(month2) - 1, parseInt(day2))

        if (d >= dateObjectStart && d <= dateObjectEnd) {
          console.log("BBIBIBIB")
          e.push(expense)
        }
    })
    setExpensesToBeDisplayed(e)


  }
  return (
    <>
  <Zoom in={true}>
    <div style={{ width: 500, height: 400 }}>
      <StaticDatePicker
        orientation="landscape"
        openTo="day"
        format="MM/dd/yyyy"
        onChange={(newValue) => {
          setValue(newValue);
        }}
        sx={{
          width: '100%', height: '100%'
        }}
        onAccept={() => handleAccept(value)}
      />
    </div>
  </Zoom>

  {/* Render the cards below the zoomed area */}
  <Grid container spacing={2}>
    {expensesToBeDisplayed.map((expense, index) => (
      <React.Fragment key={expense.id}>
        <Grid item xs={4}>
          <ExpenseCard
            id={expense.id}
            name={expense.name}
            amount={expense.amount}
            description={expense.description}
            startDate={expense.startDate}
            endDate={expense.endDate}
            categories={expense.categories}
          />
        </Grid>
        {(index + 1) % 3 === 0 && <Grid item xs={12}><Divider style={{ display: 'none' }} /></Grid>}
      </React.Fragment>
    ))}
  </Grid>
</>
  );
}

export default StaticDatePickerLandscape