import React from 'react'
import { useEffect } from 'react'
import axios from 'axios'
import { useState } from 'react'
import { Divider, Grid } from '@mui/material'
import ExpenseCard from './ExpenseCard'


const allExpenses = (props) => {
    const {bearer} = props
    
    const [expenses, setExpense] = useState([]);

    useEffect(()=>{
        
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
                      
                     
            })
            })

    },[]);

 

  return (
    <>
        
    <Grid container spacing={2}>
      {expenses.map((expense, index) => (
        <React.Fragment key={expense.id}>
          <Grid item xs={4} >
            {/* Your ExpenseCard component */}
            <ExpenseCard 
              id={expense.id} 
              name={expense.name} 
              amount={expense.amount} 
              description={expense.description}
              startDate={expense.startDate} 
              endDate={expense.endDate} 
              categories={expense.categories}
              bearer={bearer}
            />
          </Grid>
          {(index + 1) % 3 === 0 && <Grid item xs={12}><Divider style={{ display: 'none' }}/></Grid>} 
        </React.Fragment>
      ))}
    </Grid>

    </>
  )
}

export default allExpenses