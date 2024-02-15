import * as React from 'react';
import TextField from '@mui/material/TextField';
import { StaticDatePicker } from '@mui/x-date-pickers';
import { Fade, Zoom } from '@mui/material';
import { Box } from '@mui/material';
import axios from 'axios';
import { useState } from 'react';
import { Grid } from '@mui/material';
import { Divider } from '@mui/material';
import ExpenseCard from './ExpenseCard';
import { Stack } from '@mui/material';
import { Slide } from '@mui/material';
import { Grow } from '@mui/material';



const StaticDatePickerLandscape = (props) => {
  const [value, setValue] = React.useState(new Date());
  const {bearer} = props
  // console.log("date picker")
  // console.log(bearer)
  
  const [expensesToBeDisplayed, setExpensesToBeDisplayed] = useState([]);
  const [expenses, setExpense] = useState([]);

  const handleAccept = (value) => {
 
    console.log("pressed ok")
    const requestOptions = {
        headers:{
            Authorization: "Bearer " + bearer
        }
    }
    
    axios.get("http://localhost:8088/expensetracker/getuserbytoken",requestOptions)
        .then(response => {
            const {id} = response.data
            
            axios.get("http://localhost:8088/expensetracker/userexpenses/" + id)
                .then(response=>{
                    setExpense(response.data)

                    let t = response.data
                    let d = new Date(value["$d"])
                    
                    let e = []
                    t.forEach((expense, index) => {
                      
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
                  
                  
        })
    })

    // let d = new Date(value["$d"])

    // console.log(d)
    // console.log("wt")
    // console.log(huh.data)
    // let e = []
    // expenses.forEach((expense, index) => {
       
    //     let [year, month, day] = expense["startDate"].split("-");
    //     let dateObjectStart = new Date(parseInt(year), parseInt(month) - 1, parseInt(day))

    //     let [year2, month2, day2] = expense["endDate"].split("-");
    //     let dateObjectEnd = new Date(parseInt(year2), parseInt(month2) - 1, parseInt(day2))

    //     if (d >= dateObjectStart && d <= dateObjectEnd) {
    //       console.log("BBIBIBIB")
    //       e.push(expense)
    //     }
    // })
    // setExpensesToBeDisplayed(e)
    

  }
  return (
    <Stack spacing={10}>
  <div >
    <Zoom in={true}>
      <div style={{ width: 500, height: 400,  }}>
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
  </div>
  

  <div>
    <Grid container spacing={2}>
      {expensesToBeDisplayed.map((expense, index) => (
        
          <Grid item xs={4}>

          {/* <Grow 
          in={true}
          style={{ transformOrigin: '0 5 0' }}
          {...({ timeout: 1000 })} */}
          <Slide direction="up" in={true} mountOnEnter unmountOnExit>
        
        <div style={{ paddingBottom: '40px' }}>
            <ExpenseCard
              id={expense.id}
              name={expense.name}
              amount={expense.amount}
              description={expense.description}
              startDate={expense.startDate}
              endDate={expense.endDate}
              categories={expense.categories}
            />
            </div>
            </Slide>
   
          {(index + 1) % 3 === 0 && <Grid item xs={12}><Divider style={{ display: 'none' }} /></Grid>}
          </Grid>
          
      
      ))}
    </Grid>
  </div>
</Stack>
  );
}

export default StaticDatePickerLandscape